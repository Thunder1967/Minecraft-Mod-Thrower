package me.thunder.thrower.util;

import com.mojang.serialization.Codec;
import me.thunder.thrower.Thrower;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModDataAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Thrower.MODID);

    // for entity with "Hover" enchantment  thrown by glove
    public static final Supplier<AttachmentType<Integer>> HOVER_PROJECTILE_DASH_TRIGGER =
            ATTACHMENT_TYPES.register("hover_projectile_dash_trigger", () -> AttachmentType.builder(() -> 0)
                    .serialize(Codec.INT)
                    .copyOnDeath()
                    .sync(ByteBufCodecs.VAR_INT)
                    .build());

    public static void register(IEventBus eventBus){
        ATTACHMENT_TYPES.register(eventBus);
    }
}
