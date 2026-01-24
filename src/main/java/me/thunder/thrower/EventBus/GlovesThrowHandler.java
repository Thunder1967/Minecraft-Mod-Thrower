package me.thunder.thrower.EventBus;

import me.thunder.thrower.Thrower;
import me.thunder.thrower.entity.FlyingBlock;
import me.thunder.thrower.entity.FlyingItem;
import me.thunder.thrower.entity.FlyingTool;
import me.thunder.thrower.util.ModTags;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class GlovesThrowHandler {
    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        if(handleThrow(event.getEntity(),event.getLevel(),event.getItemStack())){
            event.setCancellationResult(InteractionResult.SUCCESS);
            event.setCanceled(true);
        }
    }

    private static boolean handleThrow(Player player,Level level,ItemStack stack){
        if (stack.isEmpty() || stack.is(ModTags.Items.CanNotThrowByGloves)) return false;
        else if (stack.is(Items.TNT)){
            ThrowTnt(player, level, 1);
        } else if (stack.getItem() instanceof SpawnEggItem) {
            ThrowItem(player,level,stack,1,FlyingItem.run.SPAWN_FROM_SPAWN_EGG);
        } else if (stack.is(Items.BUCKET)) {
            ThrowItem(player, level, stack, 1, FlyingItem.run.BUCKET_COLLECT_LIQUID);
        } else if (stack.getItem() instanceof BucketItem) {
            ThrowItem(player,level,stack,1,FlyingItem.run.PUT_LIQUID);
            if (!player.getAbilities().instabuild) {
                ItemStack emptyBucket = new ItemStack(Items.BUCKET);
                if (!player.getInventory().add(emptyBucket)) {
                    player.drop(emptyBucket, false);
                }
            }
        } else if(stack.getItem() instanceof DiggerItem){
            ThrowTool(player,level,stack,1);
        } else if(stack.getItem() instanceof BlockItem blockItem){
            ThrowBlock(player, level, blockItem.getBlock(), 1);
        }
        else{
            ThrowItemEntity(player,level,stack,1.5);
        }
        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }
        return true;
    }

    private static void ThrowItemEntity(Player player,Level level,ItemStack stack, double speed){
        level.playSound(
                player,
                player.getX(), player.getY(), player.getZ(),
                SoundEvents.SNOWBALL_THROW,
                SoundSource.PLAYERS,
                0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
        );
        if(!level.isClientSide){
            Vec3 eyePos = player.getEyePosition();
            ItemStack stackCopy = stack.copy();
            stackCopy.setCount(1);
            ItemEntity thrownEntity = new ItemEntity(level,eyePos.x,eyePos.y+0.3,eyePos.z,stackCopy);

            Vec3 lookAngle = player.getLookAngle();
            thrownEntity.setDeltaMovement(lookAngle.x*speed, lookAngle.y*speed+0.1, lookAngle.z*speed);
            thrownEntity.setPickUpDelay(40);
            thrownEntity.hasImpulse = true;
            level.addFreshEntity(thrownEntity);
        }
    }

    private static void ThrowBlock(Player player,Level level,Block block, double speed){
        level.playSound(
                player,
                player.getX(), player.getY(), player.getZ(),
                SoundEvents.SNOWBALL_THROW,
                SoundSource.PLAYERS,
                0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
        );
        if(!level.isClientSide){
            BlockState state = block.defaultBlockState();

            Vec3 look = player.getLookAngle();
            Vec3 spawnPos = player.getEyePosition().add(look.scale(1.2));
            FlyingBlock fallingBlock = new FlyingBlock(level, player, state);
            fallingBlock.setPos(spawnPos);
            fallingBlock.setDeltaMovement(look.x * speed, look.y * speed + 0.2, look.z * speed);
            level.addFreshEntity(fallingBlock);
        }
    }

    private static void ThrowTnt(Player player, Level level, double speed){
        player.playSound(SoundEvents.SNOWBALL_THROW, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        if(!level.isClientSide){
            PrimedTnt tnt = new PrimedTnt(level, player.getX(), player.getEyeY(), player.getZ(), player);

            Vec3 look = player.getLookAngle();
            tnt.setDeltaMovement(look.scale(speed)); // 1.5 是力道倍率

            tnt.setFuse(60);
            level.addFreshEntity(tnt);
        }
    }

    private static void ThrowItem(Player player, Level level, ItemStack stack, double speed, FlyingItem.run whatToDo){
        level.playSound(
                player,
                player.getX(), player.getY(), player.getZ(),
                SoundEvents.SNOWBALL_THROW,
                SoundSource.PLAYERS,
                0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
        );
        if(!level.isClientSide){
            ItemStack stackCopy = stack.copy();
            stackCopy.setCount(1);
            FlyingItem thrownEntity = new FlyingItem(player,level,stackCopy, whatToDo);

            Vec3 lookAngle = player.getLookAngle();
            thrownEntity.setDeltaMovement(lookAngle.x*speed, lookAngle.y*speed+0.1, lookAngle.z*speed);
            level.addFreshEntity(thrownEntity);
        }
    }

    private static void ThrowTool(Player player, Level level, ItemStack stack, double speed){
        level.playSound(
                player,
                player.getX(), player.getY(), player.getZ(),
                SoundEvents.SNOWBALL_THROW,
                SoundSource.PLAYERS,
                0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
        );
        if(!level.isClientSide){
            ItemStack stackCopy = stack.copy();
            stackCopy.setCount(1);
            FlyingTool thrownEntity = new FlyingTool(player,level,stackCopy);

            Vec3 lookAngle = player.getLookAngle();
            thrownEntity.setDeltaMovement(lookAngle.x*speed, lookAngle.y*speed+0.1, lookAngle.z*speed);
            level.addFreshEntity(thrownEntity);
            if (player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }
    }
}
