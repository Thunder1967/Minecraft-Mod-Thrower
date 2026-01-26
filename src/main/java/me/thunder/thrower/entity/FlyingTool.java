package me.thunder.thrower.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
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

public class FlyingTool extends ThrowableItemProjectile {
    private static final EntityDataAccessor<Boolean> IS_RETURNING =
            SynchedEntityData.defineId(FlyingTool.class, EntityDataSerializers.BOOLEAN);
    private int returnTimer=0;
    private double moveDistance=0;

    public FlyingTool(EntityType<? extends ThrowableItemProjectile> p_37442_, Level p_37443_) {
        super(p_37442_, p_37443_);
    }

    public FlyingTool(LivingEntity livingEntity, Level level, ItemStack item) {
        super(ModEntities.FLYING_TOOL.get(), livingEntity, level);
        this.setItem(item);
        setIsReturning(false);
        moveDistance=0;
    }

    @Override
    protected Item getDefaultItem() {
        return Items.PAPER;
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        if(getIsReturning()) return;
        super.onHitBlock(result);
        runBreakBlock(result);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        if(getIsReturning()) return;
        super.onHitEntity(result);
        runHitMob(result.getEntity(),this.getOwner());
    }

    @Override
    protected void onHit(HitResult result) {
        if(getIsReturning()) return;
        super.onHit(result);
        setIsReturning(true);
        this.setNoGravity(true);
        this.noPhysics = true;
        returnTimer=0;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            if(getIsReturning()){
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
                double speed = 0.5 + Math.min(3,(this.returnTimer * 0.15));
                this.setDeltaMovement(direction.scale(speed));
                this.returnTimer++;

                // detect collisions and retrieve item
                if (this.distanceToSqr(owner) < 4.0) {
                    ItemStack stack = this.getItem().copy();
                    if (owner instanceof Player player) {
                        player.getCooldowns().addCooldown(
                                stack.getItem(), Math.max(0, (stack.getItem() instanceof SwordItem ? 10 : 20))-this.tickCount);
                        if (!player.getInventory().add(stack)) {
                            player.drop(stack, false);
                        }
                        this.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                                SoundEvents.TRIDENT_RETURN, SoundSource.PLAYERS, 1.0F, 1.0F);
                    }
                    this.discard();
                }
            }
            moveDistance+=this.getDeltaMovement().length();
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(IS_RETURNING, false);
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
//            if(!player.getAbilities().instabuild) this.spawnAtLocation(this.getItem());;
        }
    }

    public void setIsReturning(boolean x) {
        this.entityData.set(IS_RETURNING, x);
    }

    public boolean getIsReturning() {
        return this.entityData.get(IS_RETURNING);
    }

    private void runHitMob(Entity target, Entity owner){
        ItemStack item = this.getItem();
        if (owner instanceof Player player &&
                target instanceof LivingEntity livingTarget &&
                this.level() instanceof ServerLevel serverLevel) {

            DamageSource source = this.damageSources().playerAttack(player);
            float baseDamage = 1f;
            if (player.hasEffect(MobEffects.DAMAGE_BOOST)) {
                baseDamage += (player.getEffect(MobEffects.DAMAGE_BOOST).getAmplifier() + 1) * 3.0F;
            }

            ItemAttributeModifiers modifiers = item.getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY);
            for (ItemAttributeModifiers.Entry entry : modifiers.modifiers()) {
                if (entry.attribute().is(Attributes.ATTACK_DAMAGE)) {
                    baseDamage += (float) entry.modifier().amount();
                }
            }
            float finalDamage = EnchantmentHelper.modifyDamage(
                    serverLevel, item, livingTarget, source, baseDamage);

            // custom special damage calculation
            if(item.is(Items.MACE)){
                int densityLevel = EnchantmentHelper.getTagEnchantmentLevel(
                        serverLevel.registryAccess()
                                .lookupOrThrow(Registries.ENCHANTMENT)
                                .getOrThrow(Enchantments.DENSITY),
                        item
                );
                finalDamage += (float) moveDistance/(5-densityLevel);
            }

            if (livingTarget.hurt(source, finalDamage)){
                EnchantmentHelper.doPostAttackEffectsWithItemSource(
                        serverLevel, livingTarget, source, item);
                item.hurtAndBreak(1, serverLevel, player, (p) -> {});
            }
        }
    }
}
