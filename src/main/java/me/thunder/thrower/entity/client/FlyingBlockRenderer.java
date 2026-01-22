package me.thunder.thrower.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import me.thunder.thrower.entity.FlyingBlock;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;

public class FlyingBlockRenderer extends EntityRenderer<FlyingBlock> {
    private final BlockRenderDispatcher blockRenderer;

    public FlyingBlockRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.blockRenderer = context.getBlockRenderDispatcher();
    }

    @Override
    public void render(FlyingBlock entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        BlockState state = entity.getBlockState();
        poseStack.pushPose();
        poseStack.translate(-0.5, 0.0, -0.5);

        if (state.getRenderShape() == RenderShape.MODEL) {
            this.blockRenderer.renderSingleBlock(state, poseStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        }
        else if (state.getRenderShape() == RenderShape.ENTITYBLOCK_ANIMATED) {
            this.blockRenderer.renderSingleBlock(state, poseStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        }
        poseStack.popPose();
        super.render(entity, yaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(FlyingBlock entity) {
        return InventoryMenu.BLOCK_ATLAS;
    }
}
