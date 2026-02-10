package me.thunder.thrower.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class FlyingBucket extends GlovesCanReturnProjectile {
    public FlyingBucket(EntityType<? extends FlyingBucket> p_37442_, Level p_37443_) {
        super(p_37442_, p_37443_);
    }

    public FlyingBucket(LivingEntity owner, Level level, ItemStack item, ItemStack gloves) {
        super(ModEntities.FLYING_BUCKET.get(), owner, level, item, gloves);
    }

    @Override
    public void tick(){
        // empty bucket collect liquid
        // check whether hit liquid
        if (!this.level().isClientSide && !CanPickUp.get(this) &&this.getItem().is(Items.BUCKET)) {
            BlockPos pos = this.blockPosition();
            BlockState state = this.level().getBlockState(pos);
            //in liquid
            if (!state.getFluidState().isEmpty()) {
                Player player = this.getOwner() instanceof Player p ? p : null;
                if (state.getBlock() instanceof BucketPickup pickup) {
                    ItemStack filledBucket = pickup.pickupBlock(player, this.level(), pos, state);
                    // after success
                    if (!filledBucket.isEmpty()) {
                        this.level().playSound(null, pos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                        this.setItem(filledBucket);
                        CanPickUp.set(this,true);
                        if(canReturn()){
                            this.noPhysics = true;
                        }
                        return;
                    }
                }
            }
        }

        super.tick();
    }

    @Override
    protected void onHit(HitResult result) {
        if (!CanPickUp.get(this) && this.getItem().getItem() instanceof DispensibleContainerItem bucketItem) {
            Player player = this.getOwner() instanceof Player p ? p : null;
            if(result instanceof BlockHitResult blockHit){
                putWater(bucketItem, player, blockHit.getBlockPos().relative(blockHit.getDirection()),blockHit);
            }
            else if(result instanceof EntityHitResult entityHitResult){
                putWater(bucketItem, player, entityHitResult.getEntity().blockPosition());
            }
            this.setItem(new ItemStack(Items.BUCKET));
        }
        super.onHit(result);
    }
    private void putWater(DispensibleContainerItem bucketItem, Player player, BlockPos targetPos, BlockHitResult blockHit){
        bucketItem.emptyContents(player, this.level(), targetPos, blockHit);
        if(bucketItem instanceof MobBucketItem){
            bucketItem.checkExtraContent(player,this.level(),this.getItem(),targetPos);
        }
    }
    private void putWater(DispensibleContainerItem bucketItem, Player player, BlockPos targetPos){
        this.putWater(bucketItem,player,targetPos,null);
    }
}
