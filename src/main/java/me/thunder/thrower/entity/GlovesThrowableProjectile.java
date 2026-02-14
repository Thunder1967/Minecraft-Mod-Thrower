package me.thunder.thrower.entity;

import me.thunder.thrower.enchantment.ModEnchantments;
import me.thunder.thrower.util.ModDataAttachments;
import me.thunder.thrower.util.ModDataContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.*;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.event.EventHooks;

import java.util.Optional;

public abstract class GlovesThrowableProjectile extends Projectile implements ItemSupplier {
    private static final float goldenAngle = Mth.PI * (3.0f - Mth.sqrt(5.0f));

    private static final EntityDataAccessor<ItemStack> DATA_ITEM_STACK =
            SynchedEntityData.defineId(GlovesThrowableProjectile.class, EntityDataSerializers.ITEM_STACK);

    public static final ModDataContainer.SynchedEntityDataContainer<Boolean> CanPickUp =
            new ModDataContainer.SynchedEntityDataContainer<>(GlovesThrowableProjectile.class, EntityDataSerializers.BOOLEAN,
                    "CanPickUp",
                    CompoundTag::putBoolean,
                    CompoundTag::getBoolean);
    public static final ModDataContainer.SynchedEntityDataContainer<Boolean> InGround =
            new ModDataContainer.SynchedEntityDataContainer<>(GlovesThrowableProjectile.class, EntityDataSerializers.BOOLEAN,
                    "InGround",
                    CompoundTag::putBoolean,
                    CompoundTag::getBoolean);
    public static final ModDataContainer.SynchedEntityDataContainer<Integer> LowGravityLevel =
            new ModDataContainer.SynchedEntityDataContainer<>(GlovesThrowableProjectile.class, EntityDataSerializers.INT,
                    "LowGravityLevel",
                    CompoundTag::putInt,
                    CompoundTag::getInt);
    public static final ModDataContainer.SynchedEntityDataContainer<Integer> MuscleLevel =
            new ModDataContainer.SynchedEntityDataContainer<>(GlovesThrowableProjectile.class, EntityDataSerializers.INT,
                    "MuscleLevel",
                    CompoundTag::putInt,
                    CompoundTag::getInt);
    public static final ModDataContainer.SynchedEntityDataContainer<Integer> HoverID =
            new ModDataContainer.SynchedEntityDataContainer<>(GlovesThrowableProjectile.class, EntityDataSerializers.INT,
                    "HoverID",
                    CompoundTag::putInt,
                    CompoundTag::getInt);

    public GlovesThrowableProjectile(EntityType<? extends GlovesThrowableProjectile> p_37442_, Level p_37443_) {
        super(p_37442_, p_37443_);
    }

    public GlovesThrowableProjectile(EntityType<? extends GlovesThrowableProjectile> entityType, LivingEntity owner, Level level, ItemStack item, ItemStack gloves) {
        super(entityType, level);
        this.setPos(owner.getX(), owner.getEyeY()-0.1, owner.getZ());
        this.setItem(item);
        this.setOwner(owner);

        if(level instanceof ServerLevel serverLevel && owner instanceof Player player){
            if(!player.getAbilities().instabuild){
                //handle durability damage
                gloves.hurtAndBreak(1, serverLevel, player, (p) -> {});
            }

            // get enchantment
            var lookup = player.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
            LowGravityLevel.set(this,gloves.getEnchantmentLevel(lookup.getOrThrow(ModEnchantments.LOWGRAVITY)));
            MuscleLevel.set(this,gloves.getEnchantmentLevel(lookup.getOrThrow(ModEnchantments.MUSCLE)));
            if(gloves.getEnchantmentLevel(lookup.getOrThrow(ModEnchantments.HOVER))>0){
                int hoverCounter = player.getData(ModDataAttachments.HOVER_PROJECTILE_DASH_TRIGGER)+1;
                player.setData(ModDataAttachments.HOVER_PROJECTILE_DASH_TRIGGER,hoverCounter);
                HoverID.set(this, hoverCounter);
                this.setNoGravity(true);
            }
        }
    }

    protected Item getDefaultItem() {
        return Items.PAPER;
    }

    @Override
    public void tick() {
        super.tick();
        this.updateRotation();

        // hover
        int hoverID = HoverID.get(this);
        if(hoverID!=-1){
            // hover
            int ownerCnt = this.getOwner().getData(ModDataAttachments.HOVER_PROJECTILE_DASH_TRIGGER);

            if(ownerCnt>=hoverID){
                // keep hover
                Vec3 newPos = getHoverNextPos(this.getOwner().getBoundingBox().getCenter(), hoverID,ownerCnt,1.5f,this.tickCount*0.1f);
                this.setPos(newPos);

                Vec3 moveVec = getTargetLocation(this.getOwner(),32).subtract(this.position());
                moveVec = moveVec.scale(0.1+MuscleLevel.get(this)*0.02);
                this.setDeltaMovement(moveVec);
            }
            else {
                // shoot
                // correct pos
                Vec3 bodyVec = this.getOwner().getEyePosition().subtract(this.getOwner().position());
                if(bodyVec.lengthSqr()<1e-6) bodyVec = new Vec3(0,1,0);
                bodyVec = bodyVec.normalize();
                Vec3 localPos = this.position().subtract(this.getOwner().position());
                double projectVal = bodyVec.dot(localPos);
                if(projectVal<0){
                    this.setPos(this.position().add(bodyVec.scale(-2*projectVal)));
                }
                if(Math.abs(projectVal)<0.5){
                    this.setPos(this.position().add(bodyVec));
                }

                HoverID.set(this,-1);
            }
        }
        else {
            if(!this.noPhysics){
                HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
                if (hitresult.getType() != HitResult.Type.MISS && !EventHooks.onProjectileImpact(this, hitresult)) {
                    this.hitTargetOrDeflectSelf(hitresult);
                }
                this.checkInsideBlocks();
                this.checkInGround();
            }

            if(!InGround.get(this) || this.noPhysics){
                simpleMove();
                this.applyDrag(0.99);
                this.applyGravity();
            }
        }

        if(CanPickUp.get(this)){
            // detect collisions and retrieve item
            if (willHitPlayer()) {
                ItemStack stack = this.getItem();
                Player player = (Player) this.getOwner();

                if (!player.getInventory().add(stack)) {
                    player.drop(stack, false);
                }
                this.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS,
                        0.5F, 0.4F / (this.level().getRandom().nextFloat() * 0.4F + 0.8F));
                this.discard();
            }
        }
    }

    @Override
    protected boolean canHitEntity(Entity target) {
        return super.canHitEntity(target) && !this.getOwner().equals(target);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Vec3 curMotion = this.getDeltaMovement();
        double curSpeed = curMotion.length();
        if (!this.level().isClientSide && curSpeed>0.3) {projectileHurt(result);}
        Vec3 reflect = this.position().subtract(result.getEntity().position()).normalize()
                .scale(curSpeed);
        this.setDeltaMovement(curMotion.add(reflect).scale(0.2));
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        if(!InGround.get(this)){
            InGround.set(this,true);
            this.applyDrag(0.5);
        }
    }

    @Override
    protected double getDefaultGravity() {
        return (5-LowGravityLevel.get(this))*0.01;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(DATA_ITEM_STACK, new ItemStack(this.getDefaultItem()));

        builder.define(CanPickUp.getAccessor(), false);
        builder.define(LowGravityLevel.getAccessor(), 0);
        builder.define(MuscleLevel.getAccessor(), 0);
        builder.define(InGround.getAccessor(), false);
        builder.define(HoverID.getAccessor(), -1);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);

        nbt.put("Item", this.getItem().save(this.registryAccess()));

        CanPickUp.saveNBT(this, nbt);
        InGround.saveNBT(this, nbt);
        LowGravityLevel.saveNBT(this, nbt);
        MuscleLevel.saveNBT(this, nbt);
        HoverID.saveNBT(this, nbt);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);

        if (nbt.contains("Item", 10)) {
            this.setItem(ItemStack.parse(this.registryAccess(), nbt.getCompound("Item")).orElseGet(() -> new ItemStack(this.getDefaultItem())));
        }

        CanPickUp.loadNBT(this, nbt);
        InGround.loadNBT(this, nbt);
        LowGravityLevel.loadNBT(this, nbt);
        MuscleLevel.loadNBT(this, nbt);
        HoverID.loadNBT(this, nbt);
    }

    @Override
    public boolean shouldRenderAtSqrDistance(double distance) {
        double d0 = this.getBoundingBox().getSize() * (double)4.0F;
        if (Double.isNaN(d0)) {
            d0 = 4.0F;
        }

        d0 *= 64.0F;
        return distance < d0 * d0;
    }

    protected void simpleMove(){
        Vec3 vec3 = this.getDeltaMovement();

        double d0 = this.getX() + vec3.x;
        double d1 = this.getY() + vec3.y;
        double d2 = this.getZ() + vec3.z;
        if (this.isInWater()) {
            for (int i = 0; i < 4; ++i) {
                double f1 = 0.25;
                this.level().addParticle(ParticleTypes.BUBBLE, d0 - vec3.x * f1, d1 - vec3.y * f1, d2 - vec3.z * f1, vec3.x, vec3.y, vec3.z);
            }
        }

        this.setPos(d0, d1, d2);
    }

    private void checkInGround(){
        if(!this.level().isClientSide){
            BlockPos blockpos = this.blockPosition();
            BlockState blockstate = this.level().getBlockState(blockpos);
            if (InGround.get(this) && !blockstate.isAir()) {
                VoxelShape voxelshape = blockstate.getCollisionShape(this.level(), blockpos);
                if (!voxelshape.isEmpty()) {
                    Vec3 vec31 = this.position();

                    for(AABB aabb : voxelshape.toAabbs()) {
                        if (aabb.move(blockpos).contains(vec31)) {
                            return;
                        }
                    }
                }
            }
            InGround.set(this, false);
        }
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

    public void setItem(ItemStack stack) {
        this.getEntityData().set(DATA_ITEM_STACK, stack.copyWithCount(1));
    }

    public ItemStack getItem() {
        return this.getEntityData().get(DATA_ITEM_STACK);
    }

    protected void applyDrag(double x){
        if(!isNoDrag()) this.setDeltaMovement(this.getDeltaMovement().scale(x));
    }

    protected boolean isNoDrag(){
        return this.isNoGravity();
    }

    public ItemEntity spawnAtLocation() {
        return super.spawnAtLocation(this.getItem());
    }

    protected boolean projectileHurt(Entity target, DamageSource source, double damage, double speedMultipler){
        if (target instanceof LivingEntity livingTarget) {
            // knockback
            livingTarget.knockback(0.5D, this.getDeltaMovement().x(), this.getDeltaMovement().z());

            // handle damage
            double speedSqr = this.getDeltaMovement().lengthSqr();
            return livingTarget.hurt(source, (float) (damage+speedMultipler*speedSqr));
        }
        return false;
    }

    protected boolean projectileHurt(EntityHitResult result){
        return this.projectileHurt(result.getEntity(), this.damageSources().generic(), 1, 0);
    }

    protected Vec3 getHoverNextPos(Vec3 curPos, int i, int n, float r, float time){
        n*=2;
        int i1 = 2*i-1;
        int i2 = 2*i;
        float a1 = r-(2*r*i1)/n;
        float a2 = r-(2*r*i2)/n;
        float b1 = Mth.sqrt(Mth.square(r)-Mth.square(a1));
        float b2 = Mth.sqrt(Mth.square(r)-Mth.square(a2));
        Vec3 v1 = new Vec3(b1*Mth.cos(i1*goldenAngle),b1*Mth.sin(i1*goldenAngle),a1);
        Vec3 v2 = new Vec3(b2*Mth.cos(i2*goldenAngle),b2*Mth.sin(i2*goldenAngle),a2);
        Vec3 v3 = v1.cross(v2);
        if(v3.lengthSqr()<1e-6){
            v3 = new Vec3(0,1,0);
        }
        else{
            v3 = v3.normalize();
        }
        v3 = v3.scale(r);
        return curPos.add(v1.scale(Mth.cos(time))).add(v3.scale(Mth.sin(time)));
    }

    public Vec3 getTargetLocation(Entity player, double range) {
        Vec3 eyePos = player.getEyePosition();
        Vec3 viewVec = player.getViewVector(1.0F);
        Vec3 reachVec = eyePos.add(viewVec.scale(range));

        // detect block hit
        BlockHitResult blockHit = player.level().clip(new ClipContext(
                eyePos, reachVec, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player));

        Vec3 finalTargetPos = blockHit.getType() != HitResult.Type.MISS ? blockHit.getLocation() : reachVec;

        // detect entity hit
        EntityHitResult entityHit = ProjectileUtil.getEntityHitResult(
                player.level(),
                player,
                eyePos,
                finalTargetPos,
                player.getBoundingBox().expandTowards(viewVec.scale(range)).inflate(1.0D),
                entity -> !entity.isSpectator() && entity.isPickable()
        );

        if (entityHit != null) {
            return entityHit.getLocation();
        } else {
            return finalTargetPos;
        }
    }
}
