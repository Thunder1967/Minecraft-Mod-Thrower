package me.thunder.thrower.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import me.thunder.thrower.Thrower;
import me.thunder.thrower.entity.MobNetEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class MobNetEntityRenderer extends EntityRenderer<MobNetEntity> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Thrower.MODID, "textures/entity/mob_net.png");

    protected final MobNetEntityOpenModel<MobNetEntity> openModel;
    protected final MobNetEntityCloseModel<MobNetEntity> closeModel;
    public MobNetEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.openModel = new MobNetEntityOpenModel<>(context.bakeLayer(MobNetEntityOpenModel.LAYER_LOCATION));
        this.closeModel = new MobNetEntityCloseModel<>(context.bakeLayer(MobNetEntityCloseModel.LAYER_LOCATION));
    }

    @Override
    public void render(MobNetEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        HierarchicalModel<MobNetEntity> model = MobNetEntity.IsEmptyNet.get(entity) ? this.openModel : this.closeModel;


        poseStack.pushPose();

        // correct diretion
        poseStack.translate(0.0D, 1D, 0.0D);
        poseStack.mulPose(Axis.ZP.rotationDegrees(180.0F));
        poseStack.scale(0.5f,0.5f,0.5f);
        // draw model
        VertexConsumer vertexconsumer = buffer.getBuffer(model.renderType(TEXTURE));
        model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);

        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(MobNetEntity p_114482_) {
        return TEXTURE;
    }
}
