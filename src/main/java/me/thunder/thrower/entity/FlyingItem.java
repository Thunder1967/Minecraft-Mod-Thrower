package me.thunder.thrower.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class FlyingItem extends ThrowableItemProjectile {
    public enum Action {
        SPAWN_FROM_SPAWN_EGG,
        PUT_LIQUID,
        BUCKET_COLLECT_LIQUID,
        DEFAULT
    }
    private Action whatToDo = Action.DEFAULT;
    public FlyingItem(EntityType<? extends FlyingItem> type, Level level) {
        super(type, level);
    }

    public FlyingItem(LivingEntity owner, Level level, ItemStack item, Action whatToDo) {
        super(ModEntities.FLYING_ITEM.get(), owner, level);
        this.setItem(item);
        this.whatToDo = whatToDo;
    }
    @Override
    protected Item getDefaultItem() {
        return Items.PAPER;
    }

    @Override
    public void tick(){
        super.tick();
        // empty bucket collect liquid
        // check whether hit liquid
        if (whatToDo== Action.BUCKET_COLLECT_LIQUID && !this.level().isClientSide && this.tickCount > 1) {
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
                        if(!player.getAbilities().instabuild) this.spawnAtLocation(filledBucket);
                        this.discard();
                    }
                }
            }
        }
    }
    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide) {
            switch (whatToDo){
                case SPAWN_FROM_SPAWN_EGG -> runSpawnFromEgg(result);
                case PUT_LIQUID -> runPutLiquid(result);
                case BUCKET_COLLECT_LIQUID -> runCollectLiquid(result);
                default -> runSpawnItemEntity();
            }
        }
        this.discard();
    }

    private void runSpawnFromEgg(HitResult result){
        ItemStack stack = this.getItem();
        if(stack.getItem() instanceof SpawnEggItem eggItem){
            EntityType<?> type = eggItem.getType(stack);
            Entity entity =type.create(this.level());
            if (entity != null) {
                Vec3 hitVec = result.getLocation();
                if (result instanceof BlockHitResult blockHit) {
                    Direction direction = blockHit.getDirection();
                    hitVec = hitVec.add(new Vec3(direction.getStepX(), direction.getStepY(), direction.getStepZ()).scale(0.1));
                }

                entity.moveTo(hitVec.x, hitVec.y, hitVec.z, this.getYRot(), 0.0F);

                if (entity instanceof Mob mob) {
                    mob.finalizeSpawn((ServerLevelAccessor) this.level(),
                            this.level().getCurrentDifficultyAt(entity.blockPosition()),
                            MobSpawnType.SPAWN_EGG, null);
                }

                this.level().addFreshEntity(entity);
            }
        }
        else{
            runSpawnItemEntity();
        }
    }

    private void runPutLiquid(HitResult result){
        ItemStack stack = this.getItem();
        Player player = this.getOwner() instanceof Player p ? p : null;
        if(stack.getItem() instanceof BucketItem bucketItem){
            if(result instanceof BlockHitResult blockHit){
                BlockPos targetPos = blockHit.getBlockPos().relative(blockHit.getDirection());
                bucketItem.emptyContents(player, this.level(), targetPos, blockHit);
            }
            else if(result instanceof EntityHitResult entityHitResult){
                BlockPos targetPos = entityHitResult.getEntity().blockPosition();
                bucketItem.emptyContents(player, this.level(), targetPos, null);
            }
        }
        else{
            runSpawnItemEntity();
        }
    }

    private void runCollectLiquid(HitResult result){
        Player player = this.getOwner() instanceof Player p ? p : null;
        // get center of hitpoint
        BlockPos centerPos = BlockPos.containing(result.getLocation());
        // search liquid
        for (BlockPos targetPos : BlockPos.betweenClosed(centerPos.offset(-1, -1, -1), centerPos.offset(1, 1, 1))) {
            BlockState state = this.level().getBlockState(targetPos);

            // check the block is liquid or not
            if (state.getBlock() instanceof BucketPickup pickup) {
                ItemStack filledBucket = pickup.pickupBlock(player, this.level(), targetPos, state);
                // after success
                if (!filledBucket.isEmpty()) {
                    this.level().playSound(null, targetPos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                    if(!player.getAbilities().instabuild) this.spawnAtLocation(filledBucket);
                    return;
                }
            }
        }
        runSpawnItemEntity();
    }

    private void runSpawnItemEntity(){
        this.spawnAtLocation(this.getItem());
    }
}
