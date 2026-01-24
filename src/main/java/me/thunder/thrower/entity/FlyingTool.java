package me.thunder.thrower.entity;

import me.thunder.thrower.Thrower;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class FlyingTool extends ThrowableItemProjectile {
    private boolean isReturning;
    private int returnTimer=0;

    public FlyingTool(EntityType<? extends ThrowableItemProjectile> p_37442_, Level p_37443_) {
        super(p_37442_, p_37443_);
    }

    public FlyingTool(LivingEntity livingEntity, Level level, ItemStack item) {
        super(ModEntities.FLYING_TOOL.get(), livingEntity, level);
        this.setItem(item);
        this.isReturning = false;
    }

    @Override
    protected Item getDefaultItem() {
        return Items.PAPER;
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        if(this.isReturning) return;
        super.onHitBlock(result);
        runBreakBlock(result);
    }

    @Override
    protected void onHit(HitResult result) {
        if(this.isReturning) return;
        super.onHit(result);
        this.isReturning = true;
        this.setNoGravity(true);
        this.noPhysics = true;
        returnTimer=0;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide && this.isReturning) {
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
            double speed = 0.5 + (this.returnTimer * 0.05);
            this.setDeltaMovement(direction.scale(speed));
            this.returnTimer++;

            // detect collisions and retrieve item
            if (this.distanceToSqr(owner) < 4.0) {
                ItemStack stack = this.getItem();
                if (owner instanceof Player player) {
                    if (!player.getInventory().add(stack)) {
                        player.drop(stack, false);
                    }
                    this.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                            SoundEvents.TRIDENT_RETURN, SoundSource.PLAYERS, 1.0F, 1.0F);
                }
                this.discard();
            }
        }
    }

    private void runBreakBlock(BlockHitResult blockHitResult) {
        if(this.level() instanceof ServerLevel serverLevel){
            BlockPos pos = blockHitResult.getBlockPos();
            BlockState state = this.level().getBlockState(pos);
            ItemStack tool = this.getItem();
            Player player = this.getOwner() instanceof Player p ? p : null;
            if (tool.isCorrectToolForDrops(state)) {
                LootParams.Builder builder = new LootParams.Builder(serverLevel)
                        .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
                        .withParameter(LootContextParams.TOOL, tool)
                        .withOptionalParameter(LootContextParams.THIS_ENTITY, player)
                        .withOptionalParameter(LootContextParams.BLOCK_ENTITY, serverLevel.getBlockEntity(pos));

                List<ItemStack> drops = state.getDrops(builder);

                serverLevel.destroyBlock(pos, false, player);
                if(!player.getAbilities().instabuild){
                    // generate drops
                    for (ItemStack drop : drops) {
                        Block.popResource(serverLevel, pos, drop);
                    }
                    // handle durability
                    tool.hurtAndBreak(1, serverLevel, player, (p) -> {});
                }

                // launch block breaking effect
                serverLevel.levelEvent(2001, pos, Block.getId(state));
            }
            if(!player.getAbilities().instabuild) this.spawnAtLocation(this.getItem());;
        }
    }

    public boolean getIsReturning(){
        return this.isReturning;
    }
}
