package me.thunder.thrower.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class FlyingItem extends GlovesThrowableProjectile {
    public FlyingItem(EntityType<? extends FlyingItem> type, Level level) {
        super(type, level);
    }

    public FlyingItem(LivingEntity owner, Level level, ItemStack item, ItemStack gloves) {
        super(ModEntities.FLYING_ITEM.get(), owner, level, item, gloves);
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if(this.level().isClientSide) return;
        ItemStack itemStack = this.getItem();
        Item item = itemStack.getItem();
        if (item instanceof SpawnEggItem eggItem){
            EntityType<?> type = eggItem.getType(itemStack);
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
        else if (itemStack.is(Items.FIRE_CHARGE)){
            this.level().explode(
                    this,
                    this.damageSources().explosion(this, this.getOwner()),
                    null,
                    this.getX(), this.getY(), this.getZ(),
                    5,
                    true,
                    Level.ExplosionInteraction.BLOCK
            );
        }
        else if (itemStack.is(Items.END_CRYSTAL)){
            this.level().explode(
                    this,
                    this.damageSources().explosion(this, this.getOwner()),
                    null,
                    this.getX(), this.getY(), this.getZ(),
                    10,
                    true,
                    Level.ExplosionInteraction.BLOCK
            );
        }
        else{
            this.spawnAtLocation(itemStack);
        }
        this.discard();
    }
    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
    }
}
