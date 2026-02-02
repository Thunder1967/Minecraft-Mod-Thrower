package me.thunder.thrower.item.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

public class ItemRendererExtension<T extends BlockEntityWithoutLevelRenderer> implements IClientItemExtensions {
    private final RendererFactory<T> factory;
    private T renderer;
    public ItemRendererExtension(RendererFactory<T> factory){
        this.factory = factory;
    }
    @Override
    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
        if (this.renderer == null) {
            this.renderer = this.factory.create(
                    Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                    Minecraft.getInstance().getEntityModels()
            );
        }
        return this.renderer;
    }
    @FunctionalInterface
    public interface RendererFactory<T> {
        T create(BlockEntityRenderDispatcher dispatcher, EntityModelSet modelSet);
    }
}
