package me.thunder.thrower.entity;

import me.thunder.thrower.enchantment.ModEnchantments;
import me.thunder.thrower.util.ModUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.syncher.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;

import java.util.Optional;

public abstract class ModThrowableProjectile extends ThrowableItemProjectile {
    protected ItemStack gloves;
    protected int returnTimer=0;
    protected int throwColdDown=0;


    public static final ModUtil.EntityDataContainer<Boolean> CanPickup =
            new ModUtil.EntityDataContainer<>(ModThrowableProjectile.class, EntityDataSerializers.BOOLEAN);
    public static final ModUtil.EntityDataContainer<Boolean> CanReturn =
            new ModUtil.EntityDataContainer<>(ModThrowableProjectile.class, EntityDataSerializers.BOOLEAN);
    public static final ModUtil.EntityDataContainer<Integer> LowGravityLevel =
            new ModUtil.EntityDataContainer<>(ModThrowableProjectile.class, EntityDataSerializers.INT);
    public static final ModUtil.EntityDataContainer<Integer> BoomerangLevel =
            new ModUtil.EntityDataContainer<>(ModThrowableProjectile.class, EntityDataSerializers.INT);

    public ModThrowableProjectile(EntityType<? extends ThrowableItemProjectile> p_37442_, Level p_37443_) {
        super(p_37442_, p_37443_);
    }

    public ModThrowableProjectile(EntityType<? extends ThrowableItemProjectile> entityType, LivingEntity owner, Level level, ItemStack item, ItemStack gloves) {
        super(entityType, owner, level);
        this.setItem(item);
        this.gloves = gloves;
        throwColdDown = 0;

        // get enchantment
        if(!level.isClientSide){
            var lookup = owner.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
            LowGravityLevel.set(this,this.gloves.getEnchantmentLevel(lookup.getOrThrow(ModEnchantments.LOWGRAVITY)));
            BoomerangLevel.set(this,this.gloves.getEnchantmentLevel(lookup.getOrThrow(ModEnchantments.BOOMERANG)));
            CanReturn.set(this,BoomerangLevel.get(this) > 0);
        }
    }

    @Override
    protected Item getDefaultItem() {
        return Items.PAPER;
    }

    @Override
    public void tick() {
        if(CanPickup.get(this)){
            if(CanReturn.get(this)){
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
                double speed = Math.min(-1+(BoomerangLevel.get(this)*2),(this.returnTimer * 0.1));
                this.setDeltaMovement(direction.scale(speed*0.6).add(this.getDeltaMovement().scale(0.4)));
                this.returnTimer++;

                this.move(MoverType.SELF, this.getDeltaMovement());
            }
            else{
                this.setDeltaMovement(0,0,0);
            }
            // detect collisions and retrieve item
            if (willHitPlayer()) {
                System.out.println(this.returnTimer);
                ItemStack stack = this.getItem();
                Player player = (Player) this.getOwner();

                player.getCooldowns().addCooldown(
                        stack.getItem(), Math.max(0, throwColdDown-this.tickCount));
                if (!player.getInventory().add(stack)) {
                    player.drop(stack, false);
                }
                this.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.TRIDENT_RETURN, SoundSource.PLAYERS, 1.0F, 1.0F);
                this.discard();
            }
        }
        else{
            super.tick();
        }
    }

    @Override
    protected void onHit(HitResult result) {
        if(!CanPickup.get(this)){
            super.onHit(result);
            CanPickup.set(this,true);
        }
    }

    @Override
    protected double getDefaultGravity() {
        System.out.println(LowGravityLevel.get(this)+" "+this.level().getClass());
        return (5-LowGravityLevel.get(this))*0.01;
    }

    @Override
    public boolean isInWater() {
        return false;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(CanPickup.getAccessor(), false);
        builder.define(CanReturn.getAccessor(), false);
        builder.define(LowGravityLevel.getAccessor(), 0);
        builder.define(BoomerangLevel.getAccessor(), 0);
    }

    @Override
    public boolean canUsePortal(boolean allowPassengers) {
        return CanReturn.get(this);
    }

    private boolean willHitPlayer() {
        if(this.distanceToSqr(this.getOwner())<9) return true;
        Vec3 startPos = this.position();
        Vec3 velocity = this.getDeltaMovement();
        Vec3 endPos = startPos.add(velocity);
        if(this.getOwner() instanceof Player player){
            // get player's hitbox and inflate it
            AABB playerBox = player.getBoundingBox().inflate(1D);

            // check collision happened or not
            Optional<Vec3> hitPoint = playerBox.clip(startPos, endPos);

            return hitPoint.isPresent();
        }
        return false;
    }
}
