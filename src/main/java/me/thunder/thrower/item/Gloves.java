package me.thunder.thrower.item;

import me.thunder.thrower.ModDataComponents;
import me.thunder.thrower.enchantment.ModEnchantments;
import me.thunder.thrower.entity.*;
import me.thunder.thrower.util.ModDataAttachments;
import me.thunder.thrower.util.ModTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Objects;

public class Gloves extends Item {
    public enum GlovesState{
        DEFAULT,CHARGED_THROW,THROW_SELF,AUTO_THROW,HOVER_SHOOT
    }

    public Gloves(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isValidRepairItem(ItemStack pStack, ItemStack pRepairCandidate) {
        return pRepairCandidate.is(Items.LEATHER) || super.isValidRepairItem(pStack, pRepairCandidate);
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand curHand) {
        ItemStack curItem = player.getItemInHand(curHand);
        InteractionHand otherHand = (curHand == InteractionHand.MAIN_HAND) ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
        ItemStack otherItem = player.getItemInHand(otherHand);
        var lookup = player.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        if(!player.getCooldowns().isOnCooldown(curItem.getItem())) {
            if (otherItem.isEmpty()) {
                if(curItem.getEnchantmentLevel(lookup.getOrThrow(ModEnchantments.THROWSELF))>0){
                    setState(curItem,GlovesState.THROW_SELF);
                    player.startUsingItem(curHand);
                }
                else if(curItem.getEnchantmentLevel(lookup.getOrThrow(ModEnchantments.HOVER))>0 && player.getData(ModDataAttachments.HOVER_PROJECTILE_DASH_TRIGGER)>0){
                    setState(curItem,GlovesState.HOVER_SHOOT);
                    player.startUsingItem(curHand);
                }
                else{
                    return InteractionResultHolder.fail(curItem);
                }
            }
            else{
                if(curItem.getEnchantmentLevel(lookup.getOrThrow(ModEnchantments.HOVER))>0){
                    setState(curItem,GlovesState.AUTO_THROW);
                    if(!handleThrow(player, level, otherItem, curItem, 0)){
                        return InteractionResultHolder.fail(curItem);
                    }
                    else player.swing(curHand);
                }
                else{
                    setState(curItem,GlovesState.CHARGED_THROW);
                    player.startUsingItem(curHand);
                }
            }
            return InteractionResultHolder.consume(curItem);
        }
        return InteractionResultHolder.fail(curItem);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        if (Objects.requireNonNull(getState(stack)) == GlovesState.AUTO_THROW) {
            return UseAnim.NONE;
        }
        return UseAnim.BOW;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
        if(entity instanceof Player player){
            InteractionHand curHand = player.getUsedItemHand();
            ItemStack curItem = player.getItemInHand(curHand);
            InteractionHand otherHand = (curHand == InteractionHand.MAIN_HAND) ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
            ItemStack otherItem = player.getItemInHand(otherHand);
            int duration = this.getUseDuration(stack, player) - timeLeft;
            switch (getState(curItem)){
                case THROW_SELF -> throwSelf(level, player, curItem, duration);
                case HOVER_SHOOT -> hoverShoot(player, duration);
                case CHARGED_THROW -> {
                    if(!handleThrow(player, level, otherItem, curItem, duration)){
                        return;
                    }
                }
            }
            player.swing(otherHand);
        }
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int timeLeft) {
        int duration = this.getUseDuration(stack, entity) - timeLeft;
    }

    public GlovesState getState(ItemStack itemStack){
        return GlovesState.values()[itemStack.getOrDefault(ModDataComponents.GLOVES_STATE,GlovesState.DEFAULT.ordinal())];
    }

    public void setState(ItemStack itemStack, GlovesState state){
        itemStack.set(ModDataComponents.GLOVES_STATE,state.ordinal());
    }

    public static void throwSelf(Level level, Player player, ItemStack curItem,int duration){
        level.playSound(
                player,
                player.getX(), player.getY(), player.getZ(),
                SoundEvents.SNOWBALL_THROW,
                SoundSource.PLAYERS,
                0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
        );
        player.setDeltaMovement(getThrowSpeed(player, curItem, duration));
        player.getCooldowns().addCooldown(curItem.getItem(),40);
        //handle durability damage
        if(level instanceof ServerLevel serverLevel && !player.getAbilities().instabuild){
            curItem.hurtAndBreak(4, serverLevel, player, (p) -> {});
        }
    }

    public static void hoverShoot(Player player, int duration){
        // shoot out a specified number of hover entities
        var attach = ModDataAttachments.HOVER_PROJECTILE_DASH_TRIGGER;
        player.setData(attach, Math.max(0, player.getData(attach)-getSelectedHoverNum(duration)));
    }

    private static boolean handleThrow(Player player,Level level,ItemStack item,ItemStack gloves, int duration){
        if (item.isEmpty() || item.is(ModTags.Items.CanNotThrowByGloves)) return false;
        else if(item.is(Items.TNT)){
            ThrowEntity(player,level,item,gloves, duration, true, FlyingItem::new);
        }
        else if (item.getItem() instanceof DispensibleContainerItem || item.is(Items.BUCKET)) {
            ThrowEntity(player,level,item,gloves, duration, false, FlyingBucket::new);
            item.shrink(1);
        }
        else if(item.getItem() instanceof DiggerItem ||
                item.getItem() instanceof SwordItem ||
                item.is(Items.MACE)){
            ThrowEntity(player,level,item,gloves, duration, false, FlyingTool::new);
            item.shrink(1);
        }
        else if(item.getItem() instanceof BlockItem){
            ThrowEntity(player,level,item,gloves, duration, true, FlyingBlock::new);
        }
        else if(item.getItem() instanceof MobNetItem){
            ThrowEntity(player,level,item,gloves, duration, true, MobNetEntity::new);
        }
        else{
            ThrowEntity(player,level,item,gloves, duration, true, FlyingItem::new);
        }
        return true;
    }

    private static <T extends Entity> void ThrowEntity(Player player, Level level, ItemStack item, ItemStack gloves,
                                                       int duration, boolean doShrink,
                                                       resourceFactory<T> factory){
        level.playSound(
                player,
                player.getX(), player.getY(), player.getZ(),
                SoundEvents.SNOWBALL_THROW,
                SoundSource.PLAYERS,
                0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
        );
        T thrownEntity = factory.create(player,level,item,gloves);

        thrownEntity.setDeltaMovement(getThrowSpeed(player, gloves, duration));
        level.addFreshEntity(thrownEntity);

        if (doShrink && !player.getAbilities().instabuild) {
            item.shrink(1);
        }
    }

    private static Vec3 getThrowSpeed(Player player, ItemStack gloves, int duration){
        Vec3 lookAngle = player.getLookAngle();
        var lookup = player.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        int muscleLevel = gloves.getEnchantmentLevel(lookup.getOrThrow(ModEnchantments.MUSCLE));
        double speed = (0.4+duration*0.04)*(1+muscleLevel*0.1);
        return lookAngle.scale(speed).add(player.getDeltaMovement());
    }

    private static int getSelectedHoverNum(int duration){
        if(duration<=3) return 1;
        else if(duration<=10) return duration-2;
        else if(duration<=20) return 4*duration-36;
        else return 100;
    }

    @FunctionalInterface
    private interface resourceFactory<T extends Entity>{
        T create(Player player,Level level,ItemStack item,ItemStack gloves);
    }
}
