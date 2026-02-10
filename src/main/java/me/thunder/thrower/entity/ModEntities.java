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

    public static final DeferredHolder<EntityType<?>, EntityType<FlyingItem>> FLYING_ITEM =
            ENTITY_TYPES.register("flying_item", () -> EntityType.Builder.<FlyingItem>of(FlyingItem::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .fireImmune()
                    .build("flying_item"));

    public static final DeferredHolder<EntityType<?>, EntityType<FlyingBlock>> FLYING_BLOCK =
            ENTITY_TYPES.register("flying_block", () -> EntityType.Builder.<FlyingBlock>of(FlyingBlock::new, MobCategory.MISC)
                    .sized(0.8F, 0.8F)
                    .clientTrackingRange(4)
                    .updateInterval(2)
                    .fireImmune()
                    .build("flying_block"));

    public static final DeferredHolder<EntityType<?>, EntityType<FlyingTool>> FLYING_TOOL =
            ENTITY_TYPES.register("flying_tool", () -> EntityType.Builder.<FlyingTool>of(FlyingTool::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(4)
                    .updateInterval(2)
                    .fireImmune()
                    .build("flying_tool"));

    public static final DeferredHolder<EntityType<?>, EntityType<MobNetEntity>> MOB_NET_ENTITY =
            ENTITY_TYPES.register("mob_net_entity", () -> EntityType.Builder.<MobNetEntity>of(MobNetEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(4)
                    .updateInterval(2)
                    .fireImmune()
                    .build("mob_net_entity"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
