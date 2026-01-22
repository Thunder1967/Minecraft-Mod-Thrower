package me.thunder.thrower.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class FlyingItem extends ThrowableItemProjectile {
    public enum run{
        SPAWN_FROM_SPAWN_EGG,
        PUT_LIQUID
    }
    private run whatToDo;
    public FlyingItem(EntityType<? extends FlyingItem> type, Level level) {
        super(type, level);
    }

    public FlyingItem(LivingEntity owner, Level level, ItemStack item, run whatToDo) {
        super(ModEntities.THROWN_SPAWN_EGG.get(), owner, level);
        this.setItem(item);
        this.whatToDo = whatToDo;
    }
    @Override
    protected Item getDefaultItem() {
        return Items.PAPER;
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide) {
            switch (whatToDo){
                case SPAWN_FROM_SPAWN_EGG:
                    runSpawnFromEgg(result);
                    break;
                case PUT_LIQUID:
                    runPutLiquid(result);
                    break;
                default:
                    runSpawnItemEntity();
                    break;
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
        if(stack.getItem() instanceof BucketItem bucketItem && result instanceof BlockHitResult blockHit){
            BlockPos targetPos = blockHit.getBlockPos().relative(blockHit.getDirection());
            bucketItem.emptyContents(player, this.level(), targetPos, blockHit);
            this.spawnAtLocation(Items.BUCKET);
        }
        else{
            runSpawnItemEntity();
        }
    }

    private void runSpawnItemEntity(){
        this.spawnAtLocation(this.getItem());
    }

    public ItemStack getItemStackSnapshot() {
        ItemStack stack = this.getItem();
        return stack.isEmpty() ? new ItemStack(this.getDefaultItem()) : stack;
    }
}
