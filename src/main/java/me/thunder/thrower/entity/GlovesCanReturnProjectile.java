package me.thunder.thrower.entity;

import me.thunder.thrower.util.ModUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class GlovesCanReturnProjectile extends GlovesThrowableProjectile{
    public static final ModUtil.EntityDataContainer<Boolean> CanReturn =
            new ModUtil.EntityDataContainer<>(GlovesThrowableProjectile.class, EntityDataSerializers.BOOLEAN,
                    "CanReturn",
                    CompoundTag::putBoolean,
                    CompoundTag::getBoolean);
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

    public GlovesCanReturnProjectile(EntityType<? extends GlovesCanReturnProjectile> p_37442_, Level p_37443_) {
        super(p_37442_, p_37443_);
    }

    public GlovesCanReturnProjectile(EntityType<? extends GlovesCanReturnProjectile> entityType, LivingEntity owner, Level level, ItemStack item, ItemStack gloves) {
        super(entityType, owner, level, item, gloves);
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(CanReturn.getAccessor(), false);
        builder.define(BoomerangLevel.getAccessor(), 0);
        builder.define(ReturnTimer.getAccessor(), 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);

        CanPickUp.saveNBT(this, nbt);
        BoomerangLevel.saveNBT(this, nbt);
        ReturnTimer.saveNBT(this, nbt);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);

        CanReturn.loadNBT(this, nbt);
        BoomerangLevel.loadNBT(this, nbt);
        ReturnTimer.loadNBT(this, nbt);
    }
}
