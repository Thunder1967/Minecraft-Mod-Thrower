package me.thunder.thrower.item.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import me.thunder.thrower.Thrower;
import me.thunder.thrower.entity.MobNetEntity;
import me.thunder.thrower.entity.client.MobNetEntityCloseModel;
import me.thunder.thrower.entity.client.MobNetEntityOpenModel;
import me.thunder.thrower.item.MobNetItem;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class MobNetItemRenderer extends BlockEntityWithoutLevelRenderer {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Thrower.MODID, "textures/entity/mob_net.png");

    protected final MobNetEntityOpenModel<MobNetEntity> openModel;
    protected final MobNetEntityCloseModel<MobNetEntity> closeModel;

    public MobNetItemRenderer(BlockEntityRenderDispatcher dispatcher,EntityModelSet modelSet) {
        super(dispatcher, modelSet);
        this.openModel = new MobNetEntityOpenModel<>(modelSet.bakeLayer(MobNetEntityOpenModel.LAYER_LOCATION));
        this.closeModel = new MobNetEntityCloseModel<>(modelSet.bakeLayer(MobNetEntityCloseModel.LAYER_LOCATION));
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        HierarchicalModel<MobNetEntity> model = MobNetItem.isEmptyNet(stack) ? openModel : closeModel;
        poseStack.pushPose();

        // correct shape
        poseStack.translate(0.5, 0.5, 0.5);
        poseStack.mulPose(Axis.ZP.rotationDegrees(180f));
        poseStack.scale(0.25f,0.25f,0.25f);
        poseStack.mulPose(Axis.XP.rotationDegrees(100f));
        poseStack.mulPose(Axis.YP.rotationDegrees(-70f));
        poseStack.mulPose(Axis.ZP.rotationDegrees(45f));
        poseStack.translate(-1, -3, 0);

        // render model
        VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entityCutout(TEXTURE));
        model.renderToBuffer(poseStack, vertexconsumer, packedLight, packedOverlay);

        poseStack.popPose();
    }
}
