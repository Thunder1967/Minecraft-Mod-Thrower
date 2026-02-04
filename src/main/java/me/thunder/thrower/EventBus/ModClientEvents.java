package me.thunder.thrower.EventBus;

import me.thunder.thrower.Thrower;
import me.thunder.thrower.entity.client.MobNetEntityCloseModel;
import me.thunder.thrower.entity.client.MobNetEntityOpenModel;
import me.thunder.thrower.item.ModItems;
import me.thunder.thrower.item.client.ItemRendererExtension;
import me.thunder.thrower.item.client.MobNetItemRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@EventBusSubscriber(modid = Thrower.MODID, value =  Dist.CLIENT)
public class ModClientEvents {
    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MobNetEntityOpenModel.LAYER_LOCATION, MobNetEntityOpenModel::createBodyLayer);
        event.registerLayerDefinition(MobNetEntityCloseModel.LAYER_LOCATION, MobNetEntityCloseModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new ItemRendererExtension<>(MobNetItemRenderer::new), ModItems.MOB_NET.get());
    }
}
