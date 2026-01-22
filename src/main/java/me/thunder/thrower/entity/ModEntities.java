package me.thunder.thrower.entity;

import me.thunder.thrower.Thrower;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, Thrower.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<FlyingItem>> THROWN_SPAWN_EGG =
            ENTITY_TYPES.register("thrown_spawn_egg", () -> EntityType.Builder.<FlyingItem>of(FlyingItem::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_spawn_egg"));

    public static final DeferredHolder<EntityType<?>, EntityType<FlyingBlock>> FLYING_BLOCK =
            ENTITY_TYPES.register("flying_block", () -> EntityType.Builder.<FlyingBlock>of(FlyingBlock::new, MobCategory.MISC)
                    .sized(0.98F, 0.98F)
                    .clientTrackingRange(4)
                    .updateInterval(2)
                    .build("flying_block"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
