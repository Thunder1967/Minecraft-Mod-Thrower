package me.thunder.thrower.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import me.thunder.thrower.entity.FlyingTool;
import me.thunder.thrower.entity.ModThrowableProjectile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;

public class FlyingToolRenderer extends EntityRenderer<FlyingTool> {
    private final ItemRenderer itemRenderer;
    public FlyingToolRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(FlyingTool entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        // 轉正角度
        Vec3 motion = entity.getDeltaMovement().normalize();
        float correctY = (float) (Mth.atan2(motion.z, motion.x) * (180 / Math.PI))-180f;
        poseStack.mulPose(Axis.YP.rotationDegrees(-correctY));


        // rotation animation
        // compute the axis
        Vec3 up = new Vec3(0, 1, 0);
        Vec3 correctMotion = motion.yRot(correctY * ((float)Math.PI / 180F));
        Vec3 rotationAxis = correctMotion.cross(up).normalize();
        // check no zero
        if (rotationAxis.lengthSqr() < 1.0E-5D) {
            rotationAxis = new Vec3(0, 0, 1);
        } else {
            rotationAxis = rotationAxis.normalize();
        }
        float rotationAngle;
        if(ModThrowableProjectile.CanReturn.get(entity)){
            poseStack.mulPose(Axis.XP.rotationDegrees(-90f));
            // keep rotating
            float time = entity.tickCount + partialTicks;
            rotationAngle = (time * 45.0F)%360F;
        }
        else{
            // keep facing player
            float radians = (float) Mth.atan2(correctMotion.y, Math.abs(correctMotion.x));
            if(ModThrowableProjectile.CanPickUp.get(entity)){
                rotationAngle = ((float) Math.toDegrees(radians))+135f;
            }
            else{
                poseStack.scale(1.0F, -1.0F, 1.0F);
                rotationAngle = ((float) -Math.toDegrees(radians))-60f;
            }
        }
        poseStack.mulPose(new Quaternionf().rotateAxis(
                (float)Math.toRadians(rotationAngle), (float) rotationAxis.x,(float) rotationAxis.y,(float) rotationAxis.z
        ));


        this.itemRenderer.renderStatic(entity.getItem(), ItemDisplayContext.FIXED,
                packedLight, OverlayTexture.NO_OVERLAY, poseStack, buffer, entity.level(), entity.getId());

        poseStack.popPose();
        super.render(entity, yaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(FlyingTool p_114482_) {
        return InventoryMenu.BLOCK_ATLAS;
    }
}
