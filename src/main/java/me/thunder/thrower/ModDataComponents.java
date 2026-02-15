package me.thunder.thrower;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModDataComponents {
    public static final DeferredRegister<DataComponentType<?>> COMPONENTS =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, Thrower.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<CompoundTag>> CAPTURED_ENTITY =
            COMPONENTS.register("captured_entity", () ->
                    DataComponentType.<CompoundTag>builder()
                            .persistent(CompoundTag.CODEC)
                            .build()
            );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> GLOVES_STATE =
            COMPONENTS.register("gloves_state", () ->
                    DataComponentType.<Integer>builder()
                            .persistent(Codec.INT)
                            .networkSynchronized(ByteBufCodecs.VAR_INT)
                            .build()
            );

    public static void register(IEventBus eventBus){
        COMPONENTS.register(eventBus);
    }
}
