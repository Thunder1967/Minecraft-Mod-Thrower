package me.thunder.thrower.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class FlyingBlock extends ThrowableItemProjectile {
    private static final EntityDataAccessor<BlockState> DATA_BLOCK_STATE =
            SynchedEntityData.defineId(FlyingBlock.class, EntityDataSerializers.BLOCK_STATE);
    public FlyingBlock(EntityType<? extends FlyingBlock> type, Level level) {
        super(type, level);
    }
    public FlyingBlock(Level level, LivingEntity owner, BlockState state) {
        super(ModEntities.FLYING_BLOCK.get(), owner,level);
        this.setBlockState(state);
        this.setItem(new ItemStack(state.getBlock()));
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
        if (!this.level().isClientSide) {
            if (result instanceof BlockHitResult blockHit) {
                createBlockByUseOn(blockHit);
            } else{
                this.spawnAtLocation(new ItemStack(getBlockState().getBlock()));
            }
        }
        this.discard();
    }
    private void createBlock(BlockHitResult blockHit){
        BlockPos hitPos = blockHit.getBlockPos();
        Direction direction = blockHit.getDirection();
        BlockPos placePos = hitPos.relative(direction);

        if (this.level().getBlockState(placePos).canBeReplaced()) {
            this.level().setBlock(placePos, getBlockState(), 3);
        } else {
            this.spawnAtLocation(new ItemStack(getBlockState().getBlock()));
        }
    }
    private void createBlockByUseOn(BlockHitResult blockHit){
        Player player = this.getOwner() instanceof Player p ? p : null;
        ItemStack stack = this.getItem();
        UseOnContext context = new UseOnContext(player, InteractionHand.MAIN_HAND, blockHit);
        InteractionResult res = stack.getItem().useOn(context);
        if(res == InteractionResult.PASS || res == InteractionResult.FAIL){
            this.spawnAtLocation(new ItemStack(this.getItem().getItem()));
        }
    }
    @Override
    protected Item getDefaultItem() {
        return Items.AIR;
    }
}
