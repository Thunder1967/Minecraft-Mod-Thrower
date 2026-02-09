package me.thunder.thrower.entity;

import me.thunder.thrower.enchantment.ModEnchantments;
import me.thunder.thrower.util.ModUtil;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.*;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;

import java.util.Optional;

public abstract class GlovesThrowableProjectile extends Projectile implements ItemSupplier {
    private static final EntityDataAccessor<ItemStack> DATA_ITEM_STACK =
            SynchedEntityData.defineId(GlovesThrowableProjectile.class, EntityDataSerializers.ITEM_STACK);

    public static final ModUtil.EntityDataContainer<Boolean> CanPickUp =
            new ModUtil.EntityDataContainer<>(GlovesThrowableProjectile.class, EntityDataSerializers.BOOLEAN,
                    "CanPickUp",
                    CompoundTag::putBoolean,
                    CompoundTag::getBoolean);
    public static final ModUtil.EntityDataContainer<Boolean> CanReturn =
            new ModUtil.EntityDataContainer<>(GlovesThrowableProjectile.class, EntityDataSerializers.BOOLEAN,
                    "CanReturn",
                    CompoundTag::putBoolean,
                    CompoundTag::getBoolean);
    public static final ModUtil.EntityDataContainer<Integer> LowGravityLevel =
            new ModUtil.EntityDataContainer<>(GlovesThrowableProjectile.class, EntityDataSerializers.INT,
                    "LowGravityLevel",
                    CompoundTag::putInt,
                    CompoundTag::getInt);
    public static final ModUtil.EntityDataContainer<Integer> BoomerangLevel =
            new ModUtil.EntityDataContainer<>(GlovesThrowableProjectile.class, EntityDataSerializers.INT,
                    "BoomerangLevel",
                    CompoundTag::putInt,
                    CompoundTag::getInt);
    public static final ModUtil.EntityDataContainer<Integer> ReturnTimer =
            new ModUtil.EntityDataContainer<>(GlovesThrowableProjectile.class, EntityDataSerializers.INT,
                    "ReturnTimer",
                    CompoundTag::putInt,
                    CompoundTag::getInt);
    public static final ModUtil.EntityDataContainer<Integer> ThrowColdDown =
            new ModUtil.EntityDataContainer<>(GlovesThrowableProjectile.class, EntityDataSerializers.INT,
                    "ThrowColdDown",
                    CompoundTag::putInt,
                    CompoundTag::getInt);

    public GlovesThrowableProjectile(EntityType<? extends GlovesThrowableProjectile> p_37442_, Level p_37443_) {
        super(p_37442_, p_37443_);
    }

    public GlovesThrowableProjectile(EntityType<? extends GlovesThrowableProjectile> entityType, LivingEntity owner, Level level, ItemStack item, ItemStack gloves) {
        super(entityType, level);
        this.setPos(owner.getX(), owner.getEyeY()-0.1, owner.getZ());
        this.setItem(item);
        this.setOwner(owner);

        // get enchantment
        var lookup = owner.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        LowGravityLevel.set(this,gloves.getEnchantmentLevel(lookup.getOrThrow(ModEnchantments.LOWGRAVITY)));
        BoomerangLevel.set(this,gloves.getEnchantmentLevel(lookup.getOrThrow(ModEnchantments.BOOMERANG)));
        CanReturn.set(this,BoomerangLevel.get(this) > 0);
    }

    protected Item getDefaultItem() {
        return Items.PAPER;
    }

    @Override
    public void tick() {
        super.tick();
        boolean canMove = true;
        HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        if (hitresult.getType() != HitResult.Type.MISS && !EventHooks.onProjectileImpact(this, hitresult)) {
            this.hitTargetOrDeflectSelf(hitresult);
            canMove = false;
        }
        this.checkInsideBlocks();
        this.updateRotation();

        if(CanPickUp.get(this)){
            if(CanReturn.get(this)){
                Entity owner = this.getOwner();

                // if owner die, drop as item
                if (owner == null || !owner.isAlive()) {
                    this.spawnAtLocation();
                    this.discard();
                    return;
                }

                Vec3 ownerPos = owner.getEyePosition();
                Vec3 thisPos = this.position();
                Vec3 direction = ownerPos.subtract(thisPos).normalize();

                // go back with acceleration
                double speed = Math.min(-1+(BoomerangLevel.get(this)*2),(ReturnTimer.get(this) * 0.1));
                this.setDeltaMovement(direction.scale(speed*0.6).add(this.getDeltaMovement().scale(0.4)));
                ReturnTimer.set(this,ReturnTimer.get(this)+1);

                canMove = true;
            }
            // detect collisions and retrieve item
            if (willHitPlayer()) {
                ItemStack stack = this.getItem();
                Player player = (Player) this.getOwner();

                player.getCooldowns().addCooldown(
                        stack.getItem(), Math.max(0, ThrowColdDown.get(this)-this.tickCount));
                if (!player.getInventory().add(stack)) {
                    player.drop(stack, false);
                }
                this.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.TRIDENT_RETURN, SoundSource.PLAYERS, 1.0F, 1.0F);
                this.discard();
            }
        }

        if(canMove){
            simpleMove();
            this.applyDrag(0.99);
            this.applyGravity();
        }
    }

    @Override
    protected void onHit(HitResult result) {
        if(!CanPickUp.get(this)){
            super.onHit(result);
            CanPickUp.set(this,true);
            applyDrag(0.5);
            this.noPhysics = true;
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        this.setDeltaMovement(result.getLocation().subtract(this.position()));
    }

    @Override
    protected double getDefaultGravity() {
        return (5-LowGravityLevel.get(this))*0.01;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(DATA_ITEM_STACK, new ItemStack(this.getDefaultItem()));

        builder.define(CanPickUp.getAccessor(), false);
        builder.define(CanReturn.getAccessor(), false);
        builder.define(LowGravityLevel.getAccessor(), 0);
        builder.define(BoomerangLevel.getAccessor(), 0);
        builder.define(ReturnTimer.getAccessor(), 0);
        builder.define(ThrowColdDown.getAccessor(), 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);

        nbt.put("Item", this.getItem().save(this.registryAccess()));

        CanPickUp.saveNBT(this, nbt);
        CanReturn.saveNBT(this, nbt);
        LowGravityLevel.saveNBT(this, nbt);
        BoomerangLevel.saveNBT(this, nbt);
        ReturnTimer.saveNBT(this, nbt);
        ThrowColdDown.saveNBT(this, nbt);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);

        if (nbt.contains("Item", 10)) {
            this.setItem(ItemStack.parse(this.registryAccess(), nbt.getCompound("Item")).orElseGet(() -> new ItemStack(this.getDefaultItem())));
        }

        CanPickUp.loadNBT(this, nbt);
        CanReturn.loadNBT(this, nbt);
        LowGravityLevel.loadNBT(this, nbt);
        BoomerangLevel.loadNBT(this, nbt);
        ReturnTimer.loadNBT(this, nbt);
        ThrowColdDown.loadNBT(this, nbt);
    }

    @Override
    public boolean canUsePortal(boolean allowPassengers) {
        return CanReturn.get(this);
    }

    public boolean shouldRenderAtSqrDistance(double distance) {
        double d0 = this.getBoundingBox().getSize() * (double)4.0F;
        if (Double.isNaN(d0)) {
            d0 = 4.0F;
        }

        d0 *= 64.0F;
        return distance < d0 * d0;
    }

    private void simpleMove(){
        Vec3 vec3 = this.getDeltaMovement();

        double d0 = this.getX() + vec3.x;
        double d1 = this.getY() + vec3.y;
        double d2 = this.getZ() + vec3.z;
        if (this.isInWater()) {
            for (int i = 0; i < 4; ++i) {
                double f1 = 0.25;
                this.level().addParticle(ParticleTypes.BUBBLE, d0 - vec3.x * f1, d1 - vec3.y * f1, d2 - vec3.z * f1, vec3.x, vec3.y, vec3.z);
            }
        }

        this.setPos(d0, d1, d2);
    }

    private boolean willHitPlayer() {
        if(this.distanceToSqr(this.getOwner())<9) return true;
        Vec3 startPos = this.position();
        Vec3 velocity = this.getDeltaMovement();
        Vec3 endPos = startPos.add(velocity);
        if(this.getOwner() instanceof Player player){
            // get player's hitbox and inflate it
            AABB playerBox = player.getBoundingBox().inflate(1D);

            // check collision happened or not
            Optional<Vec3> hitPoint = playerBox.clip(startPos, endPos);

            return hitPoint.isPresent();
        }
        return false;
    }

    public void setItem(ItemStack stack) {
        this.getEntityData().set(DATA_ITEM_STACK, stack.copyWithCount(1));
    }

    public ItemStack getItem() {
        return this.getEntityData().get(DATA_ITEM_STACK);
    }

    private void applyDrag(double x){
        if(!this.noPhysics) this.setDeltaMovement(this.getDeltaMovement().scale(x));
    }

    public ItemEntity spawnAtLocation() {
        return super.spawnAtLocation(this.getItem());
    }
}
