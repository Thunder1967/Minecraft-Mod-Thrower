package me.thunder.thrower.entity;

import me.thunder.thrower.ModDataComponents;
import me.thunder.thrower.item.MobNetItem;
import me.thunder.thrower.item.ModItems;
import me.thunder.thrower.util.ModUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class MobNetEntity extends GlovesThrowableProjectile {
    public static final ModUtil.SynchedEntityDataContainer<Boolean> IsEmptyNet =
            new ModUtil.SynchedEntityDataContainer<>(MobNetEntity.class, EntityDataSerializers.BOOLEAN,
                "IsEmptyNet",
                CompoundTag::putBoolean,
                CompoundTag::getBoolean
            );

    public MobNetEntity(EntityType<? extends MobNetEntity> p_37442_, Level p_37443_) {
        super(p_37442_, p_37443_);
    }

    public MobNetEntity(LivingEntity entity, Level level, ItemStack item, ItemStack gloves) {
        super(ModEntities.MOB_NET_ENTITY.get(), entity, level, item, gloves);
        IsEmptyNet.set(this,MobNetItem.isEmptyNet(item));
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        this.discard();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (this.level().isClientSide) return;
        if(!IsEmptyNet.get(this)){
            // release mob
            releaseMob(result.getLocation());
        }
        else{
            // capture mob
            Entity target = result.getEntity();
            if (target instanceof LivingEntity living && !(target instanceof Player)) {
                // store mob data
                CompoundTag entityData = new CompoundTag();
                entityData.putString("id", BuiltInRegistries.ENTITY_TYPE.getKey(living.getType()).toString());
                living.saveWithoutId(entityData);

                // build drop
                ItemStack droppedNet = new ItemStack(ModItems.MOB_NET.get());
                droppedNet.set(ModDataComponents.CAPTURED_ENTITY.get(), entityData);

                // set the display name of filled net
                droppedNet.set(DataComponents.CUSTOM_NAME,
                        Component.translatable("mob_net.thrower.fill")
                                .append(" ")
                                .append(living.getDisplayName()));

                // gnerater drop
                this.spawnAtLocation(droppedNet);
                living.discard();
            }
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        if (this.level().isClientSide) return;
        if(!IsEmptyNet.get(this)){
            // release mob
            BlockPos blockPos = result.getBlockPos();
            Vec3 pos = new Vec3(blockPos.getX()+0.5,blockPos.getY()+1,blockPos.getZ()+0.5);
            releaseMob(pos);
        }
        else{
            if(this.getOwner() instanceof Player player &&
                    !player.getAbilities().instabuild) this.spawnAtLocation(new ItemStack(ModItems.MOB_NET.get()));
        }
    }

    private void releaseMob(Vec3 pos){
        Level level = this.level();
        ItemStack item = this.getItem();
        double spread = 1;
        pos = pos.add(
                (this.random.nextDouble()-0.5)*spread,
                0,
                (this.random.nextDouble()-0.5)*spread); // apply random
        MobNetItem.releaseEntityAndClear(level,pos,item);
        if(this.getOwner() instanceof Player player &&
                !player.getAbilities().instabuild) this.spawnAtLocation(new ItemStack(ModItems.MOB_NET.get()));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(IsEmptyNet.getAccessor(), true);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        IsEmptyNet.saveNBT(this,nbt);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        IsEmptyNet.loadNBT(this, nbt);
    }
}
