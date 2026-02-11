package me.thunder.thrower.EventBus;

import me.thunder.thrower.enchantment.ModEnchantments;
import me.thunder.thrower.entity.*;
import me.thunder.thrower.item.MobNetItem;
import me.thunder.thrower.item.ModItems;
import me.thunder.thrower.util.ModTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class GlovesThrowHandler {
    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        if(throwItemWithGloves(event)){
            event.setCancellationResult(InteractionResult.SUCCESS);
            event.setCanceled(true);
        }
    }
    @SubscribeEvent
    public static void onRightClickEmpty(PlayerInteractEvent.RightClickEmpty event){
        throwItemWithGloves(event);
    }

    public static boolean throwItemWithGloves(PlayerInteractEvent event){
        Player player = event.getEntity();
        InteractionHand curHand = event.getHand();
        InteractionHand otherHand = (curHand == InteractionHand.MAIN_HAND) ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
        ItemStack otherItem = player.getItemInHand(otherHand);
        if(otherItem.is(ModItems.GLOVES.get()) && !player.getCooldowns().isOnCooldown(otherItem.getItem())){
            return handleThrow(player,event.getLevel(),event.getItemStack(),otherItem);
        }
        return false;
    }

    private static boolean handleThrow(Player player,Level level,ItemStack item,ItemStack gloves){
        if (item.is(ModTags.Items.CanNotThrowByGloves)) return false;
        else if(item.isEmpty()){
            level.playSound(
                    player,
                    player.getX(), player.getY(), player.getZ(),
                    SoundEvents.SNOWBALL_THROW,
                    SoundSource.PLAYERS,
                    0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
            );
            player.setDeltaMovement(getThrowSpeed(player, gloves));
            player.getCooldowns().addCooldown(gloves.getItem(),40);
        }
        else if (item.is(Items.TNT)){
            ThrowEntity(player,level,item,gloves, true,
                    (a,b,c,d) -> new PrimedTnt(b, a.getX(), a.getEyeY(), a.getZ(), a));
        }
        else if (item.getItem() instanceof SpawnEggItem ||
                item.is(Items.FIRE_CHARGE) ||
                item.is(Items.END_CRYSTAL)) {
            ThrowEntity(player,level,item,gloves, true, FlyingItem::new);
        }
        else if (item.getItem() instanceof DispensibleContainerItem || item.is(Items.BUCKET)) {
            ThrowEntity(player,level,item,gloves, true, FlyingBucket::new);
        }
        else if(item.getItem() instanceof DiggerItem ||
                item.getItem() instanceof SwordItem ||
                item.is(Items.MACE)){
            ThrowEntity(player,level,item,gloves, false, FlyingTool::new);
            item.shrink(1);
        }
        else if(item.getItem() instanceof BlockItem){
            ThrowEntity(player,level,item,gloves, true,FlyingBlock::new);
        }
        else if(item.getItem() instanceof MobNetItem){
            ThrowEntity(player,level,item,gloves, true, MobNetEntity::new);
        }
        else{
            ThrowEntity(player,level,item,gloves, true,
                    (a,b,c,d)->{
                        ItemEntity thrownEntity = new ItemEntity(b,a.getX(), a.getEyeY(), a.getZ(),c);
                        thrownEntity.setPickUpDelay(40);
                        thrownEntity.hasImpulse = true;
                        return thrownEntity;
            });
        }
        return true;
    }

    private static <T extends Entity> void ThrowEntity(Player player, Level level, ItemStack item,ItemStack gloves ,boolean doShrink, resourceFactory<T> factory){
        level.playSound(
                player,
                player.getX(), player.getY(), player.getZ(),
                SoundEvents.SNOWBALL_THROW,
                SoundSource.PLAYERS,
                0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
        );
        ItemStack stackCopy = item.copyWithCount(1);
        T thrownEntity = factory.create(player,level,stackCopy,gloves);

        thrownEntity.setDeltaMovement(getThrowSpeed(player, gloves));
        level.addFreshEntity(thrownEntity);

        if (doShrink && !player.getAbilities().instabuild) {
            item.shrink(1);
        }
    }

    private static Vec3 getThrowSpeed(Player player, ItemStack gloves){
        Vec3 lookAngle = player.getLookAngle();
        var lookup = player.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        double speed = 1 + 0.1*gloves.getEnchantmentLevel(lookup.getOrThrow(ModEnchantments.MUSCLE));
        return lookAngle.scale(speed).add(player.getDeltaMovement());
    }

    @FunctionalInterface
    public interface resourceFactory<T extends Entity>{
        T create(Player player,Level level,ItemStack item,ItemStack gloves);
    }
}
