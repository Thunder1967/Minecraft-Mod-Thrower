package me.thunder.thrower.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class FlyingBlock extends ModThrowableProjectile {
    private static final EntityDataAccessor<BlockState> DATA_BLOCK_STATE =
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
        builder.define(DATA_BLOCK_STATE, Blocks.AIR.defaultBlockState());
    }

    public void setBlockState(BlockState state) {
        this.entityData.set(DATA_BLOCK_STATE, state);
    }

    public BlockState getBlockState() {
        return this.entityData.get(DATA_BLOCK_STATE);
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        this.discard();
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
                this.spawnAtLocation(new ItemStack(this.getItem().getItem()));
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        this.spawnAtLocation(new ItemStack(getBlockState().getBlock()));
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
}
