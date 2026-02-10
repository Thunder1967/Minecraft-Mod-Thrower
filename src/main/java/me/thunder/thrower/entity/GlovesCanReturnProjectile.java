package me.thunder.thrower.entity;

import me.thunder.thrower.enchantment.ModEnchantments;
import me.thunder.thrower.util.ModUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

import java.util.List;

public abstract class GlovesCanReturnProjectile extends GlovesThrowableProjectile{
    protected int returnTimer = 0;

    public static final ModUtil.SynchedEntityDataContainer<Integer> BoomerangLevel =
            new ModUtil.SynchedEntityDataContainer<>(GlovesCanReturnProjectile.class, EntityDataSerializers.INT,
                    "BoomerangLevel",
                    CompoundTag::putInt,
                    CompoundTag::getInt);

    public GlovesCanReturnProjectile(EntityType<? extends GlovesCanReturnProjectile> p_37442_, Level p_37443_) {
        super(p_37442_, p_37443_);
    }

    public GlovesCanReturnProjectile(EntityType<? extends GlovesCanReturnProjectile> entityType, LivingEntity owner, Level level, ItemStack item, ItemStack gloves) {
        super(entityType, owner, level, item, gloves);
        var lookup = owner.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);

        // get enchantment
        BoomerangLevel.set(this,gloves.getEnchantmentLevel(lookup.getOrThrow(ModEnchantments.BOOMERANG)));
        returnTimer = 0;
    }

    @Override
    public void tick() {
        super.tick();
        if(this.canReturn() && CanPickUp.get(this)){
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
            double speed = Math.min(-1+(BoomerangLevel.get(this)*2),(returnTimer * 0.1));
            this.setDeltaMovement(direction.scale(speed*0.6).add(this.getDeltaMovement().scale(0.4)));
            returnTimer++;
            simpleMove();
        }
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if(!CanPickUp.get(this)){
            CanPickUp.set(this,true);
            if(canReturn()){
                this.noPhysics = true;
            }
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(BoomerangLevel.getAccessor(), 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);

        BoomerangLevel.saveNBT(this, nbt);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);

        BoomerangLevel.loadNBT(this, nbt);
    }

    @Override
    public boolean canUsePortal(boolean allowPassengers) {
        return BoomerangLevel.get(this)==0;
    }

    public boolean canReturn(){
        return BoomerangLevel.get(this)>0;
    }
}
