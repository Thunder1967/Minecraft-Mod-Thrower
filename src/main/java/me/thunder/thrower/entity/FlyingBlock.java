package me.thunder.thrower.entity;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class FlyingBlock extends GlovesThrowableProjectile {
    private static final EntityDataAccessor<BlockState> DataBlockState =
            SynchedEntityData.defineId(FlyingBlock.class, EntityDataSerializers.BLOCK_STATE);

    public FlyingBlock(EntityType<? extends FlyingBlock> type, Level level) {
        super(type, level);
    }
    public FlyingBlock(LivingEntity owner, Level level, ItemStack item, ItemStack gloves) {
        super(ModEntities.FLYING_BLOCK.get(), owner,level, item, gloves);
        this.setBlockState(((BlockItem)item.getItem()).getBlock().defaultBlockState());
    }
    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DataBlockState, Blocks.AIR.defaultBlockState());
    }

    public void setBlockState(BlockState state) {
        this.entityData.set(DataBlockState, state);
    }

    public BlockState getBlockState() {
        return this.entityData.get(DataBlockState);
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        if (!this.level().isClientSide) {
            Player player = this.getOwner() instanceof Player p ? p : null;
            ItemStack item = this.getItem();
            UseOnContext context = new UseOnContext(player, InteractionHand.MAIN_HAND, result);
            InteractionResult res = item.getItem().useOn(context);
            if(res == InteractionResult.PASS || res == InteractionResult.FAIL){
                this.spawnAtLocation();
            }
        }
        this.discard();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
//        this.spawnAtLocation();
        if (!this.level().isClientSide) {
            Entity target = result.getEntity();
            Entity owner = this.getOwner();
            DamageSource source = this.damageSources().generic();
            double baseDamage = 1;

            // play sound and run damage
            if(owner!=null){
                source = this.damageSources().fallingBlock(owner);
                if(this.getItem().is(ItemTags.ANVIL)){
                    source = this.damageSources().anvil(owner);
                    baseDamage = 10;

                    this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                            SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 1.0F, 1.0F);
                }
                else{
                    SoundType soundType = this.getBlockState().getSoundType();
                    this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                            soundType.getPlaceSound(),
                            SoundSource.BLOCKS,
                            (soundType.getVolume() + 1.0F) / 2.0F,
                            soundType.getPitch() * 0.8F);
                }
            }

            // handle damage
            double damageMultipler = this.getDeltaMovement().lengthSqr();
            target.hurt(source, (float) (baseDamage*damageMultipler+1));

            // knockback
            if (target instanceof LivingEntity livingTarget) {
                livingTarget.knockback(0.5D, this.getDeltaMovement().x(), this.getDeltaMovement().z());
            }
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        CompoundTag blockStateNbt = NbtUtils.writeBlockState(getBlockState());
        nbt.put("DataBlockState", blockStateNbt);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        if (nbt.contains("DataBlockState", Tag.TAG_COMPOUND)) {
            HolderGetter<Block> blockGetter = this.level().holderLookup(Registries.BLOCK);
            BlockState savedState = NbtUtils.readBlockState(blockGetter, nbt.getCompound("DataBlockState"));
            setBlockState(savedState);
        }
    }
}
