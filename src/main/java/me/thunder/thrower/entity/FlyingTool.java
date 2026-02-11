package me.thunder.thrower.entity;

import me.thunder.thrower.util.ModDataContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.List;

public class FlyingTool extends GlovesCanReturnProjectile {
    private final ItemStack gloves;
    public FlyingTool(EntityType<? extends FlyingTool> p_37442_, Level p_37443_) {
        super(p_37442_, p_37443_);
        this.gloves = null;
    }

    public FlyingTool(LivingEntity livingEntity, Level level, ItemStack item, ItemStack gloves) {
        super(ModEntities.FLYING_TOOL.get(), livingEntity, level, item, gloves);
        this.gloves = gloves;
        if(livingEntity instanceof Player player){
            player.getCooldowns().addCooldown(gloves.getItem(), 10);
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        if(CanPickUp.get(this)) return;
        if(this.level() instanceof ServerLevel serverLevel){
            BlockPos pos = result.getBlockPos();
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
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if(this.getOwner() instanceof Player player && this.gloves!=null){
            player.getCooldowns().addCooldown(this.gloves.getItem(), getThrowCoolDown());
        }
    }

    private int getThrowCoolDown(){
        ItemStack itemStack = this.getItem();
        if(itemStack.is(ItemTags.SWORDS)){
            return 20;
        }
        else if(itemStack.is(Items.MACE)){
            return 40;
        }
        else{
            return 30;
        }
    }

    @Override
    protected boolean projectileHurt(EntityHitResult result) {
        if(CanPickUp.get(this)) return false;
        if (this.level() instanceof ServerLevel serverLevel &&
                this.getOwner() instanceof Player player &&
                result.getEntity() instanceof LivingEntity livingTarget) {

            ItemStack item = this.getItem();
            DamageSource source = this.damageSources().playerAttack(player);
            float baseDamage = 1;
            double speedMultipler = 0;

            // apply effect
            if (player.hasEffect(MobEffects.DAMAGE_BOOST)) {
                baseDamage += (player.getEffect(MobEffects.DAMAGE_BOOST).getAmplifier()+1) * 3.0F;
            }

            // apply item modifiers
            ItemAttributeModifiers modifiers = item.getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY);
            for (ItemAttributeModifiers.Entry entry : modifiers.modifiers()) {
                if (entry.attribute().is(Attributes.ATTACK_DAMAGE)) {
                    baseDamage += (float) entry.modifier().amount();
                }
            }

            // apply Enchantment
            float finalDamage = EnchantmentHelper.modifyDamage(
                    serverLevel, item, livingTarget, source, baseDamage);

            // custom special damage calculation
            if(item.is(Items.MACE)){
                finalDamage += item.getItem().getAttackDamageBonus(livingTarget,0f,source);
            }

            // apply damage
            if (this.projectileHurt(livingTarget,source,finalDamage,speedMultipler)){
                EnchantmentHelper.doPostAttackEffectsWithItemSource(
                        serverLevel, livingTarget, source, item);
                if(!player.getAbilities().instabuild) item.hurtAndBreak(1, serverLevel, player, (p) -> {});
                return true;
            }
        }
        return false;
    }

    @Override
    protected void applyDrag(double x) {
        if(!this.noPhysics && !this.getItem().is(Items.MACE)) this.setDeltaMovement(this.getDeltaMovement().scale(x));
    }
}
