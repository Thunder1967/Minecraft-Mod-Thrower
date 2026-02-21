package me.thunder.thrower.lambdynamiclights;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.lambdaurora.lambdynlights.api.entity.luminance.EntityLuminance;
import dev.lambdaurora.lambdynlights.api.item.ItemLightSourceManager;
import me.thunder.thrower.entity.GlovesThrowableProjectile;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Range;

public record CustomEntityLuminance(boolean invert) implements EntityLuminance {
    // The Codec of this entity luminance provider,
    // this describes how to parse the JSON file.
    public static final MapCodec<CustomEntityLuminance> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    Codec.BOOL.fieldOf("invert").forGetter(CustomEntityLuminance::invert)
            ).apply(instance, CustomEntityLuminance::new)
    );

    @Override
    public Type type() {
        // This is the registered type of this entity luminance provider.
        // We will modify the initializer to reflect this.
        return Initializer.CUSTOM_ENTITY_LUMINANCE;
    }

    @Override
    public @Range(from = 0, to = 15) int getLuminance(
            ItemLightSourceManager itemLightSourceManager,
            Entity entity
    ) {
        if (entity instanceof GlovesThrowableProjectile projectile) {
            ItemStack stack = projectile.getItem();
            int itemLuminance = itemLightSourceManager.getLuminance(stack,projectile.isInLiquid());
            return itemLuminance;
        }
        return 0;
    }
}
