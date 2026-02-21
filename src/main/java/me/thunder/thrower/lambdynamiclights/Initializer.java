package me.thunder.thrower.lambdynamiclights;

import dev.lambdaurora.lambdynlights.api.DynamicLightsContext;
import dev.lambdaurora.lambdynlights.api.DynamicLightsInitializer;
import dev.lambdaurora.lambdynlights.api.entity.luminance.EntityLuminance;
import dev.lambdaurora.lambdynlights.api.item.ItemLightSourceManager;
import me.thunder.thrower.Thrower;
import net.minecraft.resources.ResourceLocation;

public class Initializer implements DynamicLightsInitializer {
    @Override
    public void onInitializeDynamicLights(DynamicLightsContext context) {
        DynamicLightsInitializer.super.onInitializeDynamicLights(context);
    }
    @Override
    @SuppressWarnings({"removal"})
    public void onInitializeDynamicLights(ItemLightSourceManager itemLightSourceManager) {

    }

    public static final EntityLuminance.Type CUSTOM_ENTITY_LUMINANCE
            = EntityLuminance.Type.register(
            ResourceLocation.fromNamespaceAndPath(Thrower.MODID, "custom_entity_luminance"),
            CustomEntityLuminance.CODEC
    );
}
