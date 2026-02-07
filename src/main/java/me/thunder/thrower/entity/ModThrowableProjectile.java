package me.thunder.thrower.entity;

import me.thunder.thrower.enchantment.ModEnchantments;
import me.thunder.thrower.util.ModUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.*;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public abstract class ModThrowableProjectile extends ThrowableItemProjectile {
    protected ItemStack gloves;

    public static final ModUtil.EntityDataContainer<Boolean> CanPickUp =
            new ModUtil.EntityDataContainer<>(ModThrowableProjectile.class, EntityDataSerializers.BOOLEAN,
                    "CanPickUp",
                    CompoundTag::putBoolean,
                    CompoundTag::getBoolean);
    public static final ModUtil.EntityDataContainer<Boolean> CanReturn =
            new ModUtil.EntityDataContainer<>(ModThrowableProjectile.class, EntityDataSerializers.BOOLEAN,
                    "CanReturn",
                    CompoundTag::putBoolean,
                    CompoundTag::getBoolean);
    public static final ModUtil.EntityDataContainer<Integer> LowGravityLevel =
            new ModUtil.EntityDataContainer<>(ModThrowableProjectile.class, EntityDataSerializers.INT,
                    "LowGravityLevel",
                    CompoundTag::putInt,
                    CompoundTag::getInt);
    public static final ModUtil.EntityDataContainer<Integer> BoomerangLevel =
            new ModUtil.EntityDataContainer<>(ModThrowableProjectile.class, EntityDataSerializers.INT,
                    "BoomerangLevel",
                    CompoundTag::putInt,
                    CompoundTag::getInt);
    public static final ModUtil.EntityDataContainer<Integer> ReturnTimer =
            new ModUtil.EntityDataContainer<>(ModThrowableProjectile.class, EntityDataSerializers.INT,
                    "ReturnTimer",
                    CompoundTag::putInt,
                    CompoundTag::getInt);
    public static final ModUtil.EntityDataContainer<Integer> ThrowColdDown =
            new ModUtil.EntityDataContainer<>(ModThrowableProjectile.class, EntityDataSerializers.INT,
                    "ThrowColdDown",
                    CompoundTag::putInt,
                    CompoundTag::getInt);

    public ModThrowableProjectile(EntityType<? extends ModThrowableProjectile> p_37442_, Level p_37443_) {
        super(p_37442_, p_37443_);
    }

    public ModThrowableProjectile(EntityType<? extends ModThrowableProjectile> entityType, LivingEntity owner, Level level, ItemStack item, ItemStack gloves) {
        super(entityType, owner, level);
        this.setItem(item);
        this.gloves = gloves;

        // get enchantment
        if(!level.isClientSide){
            var lookup = owner.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
            LowGravityLevel.set(this,this.gloves.getEnchantmentLevel(lookup.getOrThrow(ModEnchantments.LOWGRAVITY)));
            BoomerangLevel.set(this,this.gloves.getEnchantmentLevel(lookup.getOrThrow(ModEnchantments.BOOMERANG)));
            CanReturn.set(this,BoomerangLevel.get(this) > 0);
        }
        else{
            System.out.println("Clinet");
        }
    }

    @Override
    protected Item getDefaultItem() {
        return Items.PAPER;
    }

    @Override
    public void tick() {
        if(CanPickUp.get(this)){
            if(CanReturn.get(this)){
                Entity owner = this.getOwner();

                // if owner die, drop as item
                if (owner == null || !owner.isAlive()) {
                    this.spawnAtLocation(this.getItem());
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

                this.move(MoverType.SELF, this.getDeltaMovement());
            }
            else{
                this.setDeltaMovement(0,0,0);
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
        else{
            super.tick();
        }
    }

    @Override
    protected void onHit(HitResult result) {
        if(!CanPickUp.get(this)){
            super.onHit(result);
            CanPickUp.set(this,true);
            this.setDeltaMovement(0,0,0);
        }
    }

    @Override
    protected double getDefaultGravity() {
        return (5-LowGravityLevel.get(this))*0.01;
    }

    @Override
    public boolean isInWater() {
        return false;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
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
}
