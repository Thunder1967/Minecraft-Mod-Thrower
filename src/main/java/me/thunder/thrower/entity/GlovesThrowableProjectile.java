package me.thunder.thrower.entity;

import me.thunder.thrower.enchantment.ModEnchantments;
import me.thunder.thrower.util.ModDataContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.*;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.*;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.event.EventHooks;

import java.util.List;
import java.util.Optional;

public abstract class GlovesThrowableProjectile extends Projectile implements ItemSupplier {
    private static final EntityDataAccessor<ItemStack> DATA_ITEM_STACK =
            SynchedEntityData.defineId(GlovesThrowableProjectile.class, EntityDataSerializers.ITEM_STACK);

    public static final ModDataContainer.SynchedEntityDataContainer<Boolean> CanPickUp =
            new ModDataContainer.SynchedEntityDataContainer<>(GlovesThrowableProjectile.class, EntityDataSerializers.BOOLEAN,
                    "CanPickUp",
                    CompoundTag::putBoolean,
                    CompoundTag::getBoolean);
    public static final ModDataContainer.SynchedEntityDataContainer<Boolean> InGround =
            new ModDataContainer.SynchedEntityDataContainer<>(GlovesThrowableProjectile.class, EntityDataSerializers.BOOLEAN,
                    "InGround",
                    CompoundTag::putBoolean,
                    CompoundTag::getBoolean);
    public static final ModDataContainer.SynchedEntityDataContainer<Integer> LowGravityLevel =
            new ModDataContainer.SynchedEntityDataContainer<>(GlovesThrowableProjectile.class, EntityDataSerializers.INT,
                    "LowGravityLevel",
                    CompoundTag::putInt,
                    CompoundTag::getInt);
    public static final ModDataContainer.SynchedEntityDataContainer<Integer> ThrowColdDown =
            new ModDataContainer.SynchedEntityDataContainer<>(GlovesThrowableProjectile.class, EntityDataSerializers.INT,
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
    }

    protected Item getDefaultItem() {
        return Items.PAPER;
    }

    @Override
    public void tick() {
        super.tick();
        HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        if (hitresult.getType() != HitResult.Type.MISS && !EventHooks.onProjectileImpact(this, hitresult)) {
            this.hitTargetOrDeflectSelf(hitresult);
        }

        this.checkInsideBlocks();
        this.updateRotation();
        this.checkInGround();

        if(CanPickUp.get(this)){
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
                        SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS,
                        0.5F, 0.4F / (this.level().getRandom().nextFloat() * 0.4F + 0.8F));
                this.discard();
            }
        }

        if(!InGround.get(this)){
            simpleMove();
            this.applyDrag(0.99);
            this.applyGravity();
            Vec3 motion = this.getDeltaMovement();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Vec3 curMotion = this.getDeltaMovement();
        Vec3 reflect = this.position().subtract(result.getEntity().position()).normalize()
                .scale(curMotion.lengthSqr());
        this.setDeltaMovement(curMotion.add(reflect).scale(0.2));
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        if(!InGround.get(this)){
            InGround.set(this,true);
            this.applyDrag(0.5);
        }
    }


    @Override
    protected double getDefaultGravity() {
        return (5-LowGravityLevel.get(this))*0.01;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(DATA_ITEM_STACK, new ItemStack(this.getDefaultItem()));

        builder.define(CanPickUp.getAccessor(), false);
        builder.define(LowGravityLevel.getAccessor(), 0);
        builder.define(ThrowColdDown.getAccessor(), 0);
        builder.define(InGround.getAccessor(), false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);

        nbt.put("Item", this.getItem().save(this.registryAccess()));

        CanPickUp.saveNBT(this, nbt);
        InGround.saveNBT(this, nbt);
        LowGravityLevel.saveNBT(this, nbt);
        ThrowColdDown.saveNBT(this, nbt);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);

        if (nbt.contains("Item", 10)) {
            this.setItem(ItemStack.parse(this.registryAccess(), nbt.getCompound("Item")).orElseGet(() -> new ItemStack(this.getDefaultItem())));
        }

        CanPickUp.loadNBT(this, nbt);
        InGround.loadNBT(this, nbt);
        LowGravityLevel.loadNBT(this, nbt);
        ThrowColdDown.loadNBT(this, nbt);
    }

    @Override
    public boolean shouldRenderAtSqrDistance(double distance) {
        double d0 = this.getBoundingBox().getSize() * (double)4.0F;
        if (Double.isNaN(d0)) {
            d0 = 4.0F;
        }

        d0 *= 64.0F;
        return distance < d0 * d0;
    }

    protected void simpleMove(){
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

    protected boolean isNoPhysics(){
        return this.level().isClientSide || this.noPhysics;
    }

    private void checkInGround(){
        if(!isNoPhysics()){
            BlockPos blockpos = this.blockPosition();
            BlockState blockstate = this.level().getBlockState(blockpos);
            if (InGround.get(this) && !blockstate.isAir()) {
                VoxelShape voxelshape = blockstate.getCollisionShape(this.level(), blockpos);
                if (!voxelshape.isEmpty()) {
                    Vec3 vec31 = this.position();

                    for(AABB aabb : voxelshape.toAabbs()) {
                        if (aabb.move(blockpos).contains(vec31)) {
                            return;
                        }
                    }
                }
            }
            InGround.set(this, false);
        }
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

    protected void applyDrag(double x){
        if(!this.noPhysics) this.setDeltaMovement(this.getDeltaMovement().scale(x));
    }

    public ItemEntity spawnAtLocation() {
        return super.spawnAtLocation(this.getItem());
    }
}
