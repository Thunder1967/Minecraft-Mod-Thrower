package me.thunder.thrower.item;

import me.thunder.thrower.ModDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class MobNetItem extends Item {
    public MobNetItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        ItemStack item = context.getItemInHand();
        BlockPos blockPos = context.getClickedPos().relative(context.getClickedFace());
        Vec3 spawnPos = new Vec3(blockPos.getX()+0.5,blockPos.getY()+0.5,blockPos.getZ()+0.5);

        if(!level.isClientSide && releaseEntityAndClear(level,spawnPos,item)){
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    private static boolean releaseEntity(Level level, Vec3 pos, CompoundTag nbt){
        return EntityType.by(nbt).map(type -> {
            Entity entity = type.create(level);
            if (entity != null) {
                entity.load(nbt);
                entity.setUUID(UUID.randomUUID());

                entity.moveTo(pos.x,pos.y,pos.z, 0, 0);

                return level.addFreshEntity(entity);
            }
            return false;
        }).orElse(false);
    }

    public static boolean releaseEntityAndClear(Level level, Vec3 pos, @NotNull ItemStack item){
        CompoundTag nbt = item.get(ModDataComponents.CAPTURED_ENTITY.get());
        if(nbt!=null && !level.isClientSide && releaseEntity(level,pos,nbt)){
            item.remove(ModDataComponents.CAPTURED_ENTITY.get());
            item.remove(DataComponents.CUSTOM_NAME);
            return true;
        }
        return false;
    }

    public static boolean isEmptyNet(ItemStack item){
        return item.get(ModDataComponents.CAPTURED_ENTITY.get())==null;
    }
}
