package me.thunder.thrower.entity.client;// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.thunder.thrower.Thrower;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class MobNetEntityCloseModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			ResourceLocation.fromNamespaceAndPath(Thrower.MODID, "mob_net_entity_close"), "main");
	private final ModelPart controller;
	private final ModelPart main1;
	private final ModelPart bone;
	private final ModelPart bone2;
	private final ModelPart bone3;
	private final ModelPart bone4;
	private final ModelPart frame;
	private final ModelPart frame3;
	private final ModelPart frame2;
	private final ModelPart main2;
	private final ModelPart bone5;
	private final ModelPart bone6;
	private final ModelPart bone7;
	private final ModelPart bone8;
	private final ModelPart frame4;
	private final ModelPart frame5;
	private final ModelPart frame6;
	private final ModelPart main3;
	private final ModelPart bone9;
	private final ModelPart bone10;
	private final ModelPart bone11;
	private final ModelPart bone12;
	private final ModelPart frame7;
	private final ModelPart frame8;
	private final ModelPart frame9;
	private final ModelPart main4;
	private final ModelPart bone13;
	private final ModelPart bone14;
	private final ModelPart bone15;
	private final ModelPart bone16;
	private final ModelPart frame10;
	private final ModelPart frame11;
	private final ModelPart frame12;
	private final ModelPart main5;
	private final ModelPart bone17;
	private final ModelPart bone18;
	private final ModelPart bone19;
	private final ModelPart bone20;
	private final ModelPart frame13;
	private final ModelPart frame14;
	private final ModelPart frame15;
	private final ModelPart main6;
	private final ModelPart bone21;
	private final ModelPart bone22;
	private final ModelPart bone23;
	private final ModelPart bone24;
	private final ModelPart frame16;
	private final ModelPart frame17;
	private final ModelPart frame18;
	private final ModelPart main7;
	private final ModelPart bone25;
	private final ModelPart bone26;
	private final ModelPart bone27;
	private final ModelPart bone28;
	private final ModelPart frame19;
	private final ModelPart frame20;
	private final ModelPart frame21;
	private final ModelPart main8;
	private final ModelPart bone29;
	private final ModelPart bone30;
	private final ModelPart bone31;
	private final ModelPart bone32;
	private final ModelPart frame22;
	private final ModelPart frame23;
	private final ModelPart frame24;

	public MobNetEntityCloseModel(ModelPart root) {
		this.controller = root.getChild("controller");
		this.main1 = this.controller.getChild("main1");
		this.bone = this.main1.getChild("bone");
		this.bone2 = this.main1.getChild("bone2");
		this.bone3 = this.main1.getChild("bone3");
		this.bone4 = this.main1.getChild("bone4");
		this.frame = this.bone4.getChild("frame");
		this.frame3 = this.bone4.getChild("frame3");
		this.frame2 = this.bone4.getChild("frame2");
		this.main2 = this.controller.getChild("main2");
		this.bone5 = this.main2.getChild("bone5");
		this.bone6 = this.main2.getChild("bone6");
		this.bone7 = this.main2.getChild("bone7");
		this.bone8 = this.main2.getChild("bone8");
		this.frame4 = this.bone8.getChild("frame4");
		this.frame5 = this.bone8.getChild("frame5");
		this.frame6 = this.bone8.getChild("frame6");
		this.main3 = this.controller.getChild("main3");
		this.bone9 = this.main3.getChild("bone9");
		this.bone10 = this.main3.getChild("bone10");
		this.bone11 = this.main3.getChild("bone11");
		this.bone12 = this.main3.getChild("bone12");
		this.frame7 = this.bone12.getChild("frame7");
		this.frame8 = this.bone12.getChild("frame8");
		this.frame9 = this.bone12.getChild("frame9");
		this.main4 = this.controller.getChild("main4");
		this.bone13 = this.main4.getChild("bone13");
		this.bone14 = this.main4.getChild("bone14");
		this.bone15 = this.main4.getChild("bone15");
		this.bone16 = this.main4.getChild("bone16");
		this.frame10 = this.bone16.getChild("frame10");
		this.frame11 = this.bone16.getChild("frame11");
		this.frame12 = this.bone16.getChild("frame12");
		this.main5 = this.controller.getChild("main5");
		this.bone17 = this.main5.getChild("bone17");
		this.bone18 = this.main5.getChild("bone18");
		this.bone19 = this.main5.getChild("bone19");
		this.bone20 = this.main5.getChild("bone20");
		this.frame13 = this.bone20.getChild("frame13");
		this.frame14 = this.bone20.getChild("frame14");
		this.frame15 = this.bone20.getChild("frame15");
		this.main6 = this.controller.getChild("main6");
		this.bone21 = this.main6.getChild("bone21");
		this.bone22 = this.main6.getChild("bone22");
		this.bone23 = this.main6.getChild("bone23");
		this.bone24 = this.main6.getChild("bone24");
		this.frame16 = this.bone24.getChild("frame16");
		this.frame17 = this.bone24.getChild("frame17");
		this.frame18 = this.bone24.getChild("frame18");
		this.main7 = this.controller.getChild("main7");
		this.bone25 = this.main7.getChild("bone25");
		this.bone26 = this.main7.getChild("bone26");
		this.bone27 = this.main7.getChild("bone27");
		this.bone28 = this.main7.getChild("bone28");
		this.frame19 = this.bone28.getChild("frame19");
		this.frame20 = this.bone28.getChild("frame20");
		this.frame21 = this.bone28.getChild("frame21");
		this.main8 = this.controller.getChild("main8");
		this.bone29 = this.main8.getChild("bone29");
		this.bone30 = this.main8.getChild("bone30");
		this.bone31 = this.main8.getChild("bone31");
		this.bone32 = this.main8.getChild("bone32");
		this.frame22 = this.bone32.getChild("frame22");
		this.frame23 = this.bone32.getChild("frame23");
		this.frame24 = this.bone32.getChild("frame24");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition controller = partdefinition.addOrReplaceChild("controller", CubeListBuilder.create().texOffs(42, 93).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 13.0F, 0.0F));

		PartDefinition main1 = controller.addOrReplaceChild("main1", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6109F));

		PartDefinition bone = main1.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(36, 79).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(94, 0).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-2.8F, 1.4F, 0.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition cube_r1 = bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(16, 80).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.2F, -2.25F, 0.7397F, 1.146F, 0.8137F));

		PartDefinition bone2 = main1.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 80).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(94, 21).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-9.2838F, 3.1931F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition cube_r2 = bone2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(4, 0).addBox(-3.0F, -2.0F, -1.25F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.7F, -3.0F, 0.9993F, 1.1729F, 1.0556F));

		PartDefinition bone3 = main1.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(72, 80).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(28, 95).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-15.1649F, 6.4263F, 0.0F, 0.0F, 0.0F, -0.6545F));

		PartDefinition cube_r3 = bone3.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(-1, 32).addBox(-4.0F, -0.75F, -0.85F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.85F, -0.25F, -4.0F, 1.2143F, 1.1626F, 1.2712F));

		PartDefinition bone4 = main1.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(80, 0).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 96).addBox(-8.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(2.5F)), PartPose.offsetAndRotation(-20.2834F, 10.7916F, 0.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition frame = bone4.addOrReplaceChild("frame", CubeListBuilder.create().texOffs(40, 24).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -1.0F, 0.0F));

		PartDefinition cube_r4 = frame.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(36, 40).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r5 = frame.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(40, 27).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r6 = frame.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(54, 72).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition frame3 = bone4.addOrReplaceChild("frame3", CubeListBuilder.create().texOffs(42, 8).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r7 = frame3.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(42, 14).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r8 = frame3.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(42, 11).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r9 = frame3.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(72, 56).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition frame2 = bone4.addOrReplaceChild("frame2", CubeListBuilder.create().texOffs(42, 17).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r10 = frame2.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(36, 43).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r11 = frame2.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(42, 20).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r12 = frame2.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(72, 59).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition main2 = controller.addOrReplaceChild("main2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5208F, -0.6178F, -0.7805F));

		PartDefinition bone5 = main2.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(36, 79).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(94, 0).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-2.8F, 1.4F, 0.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition cube_r13 = bone5.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(16, 80).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.2F, -2.25F, 0.7397F, 1.146F, 0.8137F));

		PartDefinition bone6 = main2.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(0, 80).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(94, 21).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-9.2838F, 3.1931F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition cube_r14 = bone6.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(4, 0).addBox(-3.0F, -2.0F, -1.25F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.7F, -3.0F, 0.9993F, 1.1729F, 1.0556F));

		PartDefinition bone7 = main2.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(72, 80).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(28, 95).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-15.1649F, 6.4263F, 0.0F, 0.0F, 0.0F, -0.6545F));

		PartDefinition cube_r15 = bone7.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(-1, 32).addBox(-4.0F, -0.75F, -0.85F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.85F, -0.25F, -4.0F, 1.2143F, 1.1626F, 1.2712F));

		PartDefinition bone8 = main2.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(80, 0).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 96).addBox(-8.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(2.5F)), PartPose.offsetAndRotation(-20.2834F, 10.7916F, 0.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition frame4 = bone8.addOrReplaceChild("frame4", CubeListBuilder.create().texOffs(40, 24).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -1.0F, 0.0F));

		PartDefinition cube_r16 = frame4.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(36, 40).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r17 = frame4.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(40, 27).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r18 = frame4.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(54, 72).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition frame5 = bone8.addOrReplaceChild("frame5", CubeListBuilder.create().texOffs(42, 8).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r19 = frame5.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(42, 14).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r20 = frame5.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(42, 11).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r21 = frame5.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(72, 56).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition frame6 = bone8.addOrReplaceChild("frame6", CubeListBuilder.create().texOffs(42, 17).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r22 = frame6.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(36, 43).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r23 = frame6.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(42, 20).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r24 = frame6.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(72, 59).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition main3 = controller.addOrReplaceChild("main3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, -0.9599F, -1.5708F));

		PartDefinition bone9 = main3.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(36, 79).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(94, 0).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-2.8F, 1.4F, 0.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition cube_r25 = bone9.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(16, 80).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.2F, -2.25F, 0.7397F, 1.146F, 0.8137F));

		PartDefinition bone10 = main3.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(0, 80).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(94, 21).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-9.2838F, 3.1931F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition cube_r26 = bone10.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(4, 0).addBox(-3.0F, -2.0F, -1.25F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.7F, -3.0F, 0.9993F, 1.1729F, 1.0556F));

		PartDefinition bone11 = main3.addOrReplaceChild("bone11", CubeListBuilder.create().texOffs(72, 80).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(28, 95).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-15.1649F, 6.4263F, 0.0F, 0.0F, 0.0F, -0.6545F));

		PartDefinition cube_r27 = bone11.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(-1, 32).addBox(-4.0F, -0.75F, -0.85F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.85F, -0.25F, -4.0F, 1.2143F, 1.1626F, 1.2712F));

		PartDefinition bone12 = main3.addOrReplaceChild("bone12", CubeListBuilder.create().texOffs(80, 0).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 96).addBox(-8.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(2.5F)), PartPose.offsetAndRotation(-20.2834F, 10.7916F, 0.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition frame7 = bone12.addOrReplaceChild("frame7", CubeListBuilder.create().texOffs(40, 24).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -1.0F, 0.0F));

		PartDefinition cube_r28 = frame7.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(36, 40).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r29 = frame7.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(40, 27).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r30 = frame7.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(54, 72).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition frame8 = bone12.addOrReplaceChild("frame8", CubeListBuilder.create().texOffs(42, 8).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r31 = frame8.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(42, 14).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r32 = frame8.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(42, 11).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r33 = frame8.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(72, 56).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition frame9 = bone12.addOrReplaceChild("frame9", CubeListBuilder.create().texOffs(42, 17).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r34 = frame9.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(36, 43).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r35 = frame9.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(42, 20).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r36 = frame9.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(72, 59).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition main4 = controller.addOrReplaceChild("main4", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 2.6208F, -0.6178F, -2.3611F));

		PartDefinition bone13 = main4.addOrReplaceChild("bone13", CubeListBuilder.create().texOffs(36, 79).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(94, 0).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-2.8F, 1.4F, 0.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition cube_r37 = bone13.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(16, 80).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.2F, -2.25F, 0.7397F, 1.146F, 0.8137F));

		PartDefinition bone14 = main4.addOrReplaceChild("bone14", CubeListBuilder.create().texOffs(0, 80).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(94, 21).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-9.2838F, 3.1931F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition cube_r38 = bone14.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(4, 0).addBox(-3.0F, -2.0F, -1.25F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.7F, -3.0F, 0.9993F, 1.1729F, 1.0556F));

		PartDefinition bone15 = main4.addOrReplaceChild("bone15", CubeListBuilder.create().texOffs(72, 80).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(28, 95).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-15.1649F, 6.4263F, 0.0F, 0.0F, 0.0F, -0.6545F));

		PartDefinition cube_r39 = bone15.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(-1, 32).addBox(-4.0F, -0.75F, -0.85F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.85F, -0.25F, -4.0F, 1.2143F, 1.1626F, 1.2712F));

		PartDefinition bone16 = main4.addOrReplaceChild("bone16", CubeListBuilder.create().texOffs(80, 0).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 96).addBox(-8.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(2.5F)), PartPose.offsetAndRotation(-20.2834F, 10.7916F, 0.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition frame10 = bone16.addOrReplaceChild("frame10", CubeListBuilder.create().texOffs(40, 24).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -1.0F, 0.0F));

		PartDefinition cube_r40 = frame10.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(36, 40).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r41 = frame10.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(40, 27).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r42 = frame10.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(54, 72).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition frame11 = bone16.addOrReplaceChild("frame11", CubeListBuilder.create().texOffs(42, 8).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r43 = frame11.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(42, 14).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r44 = frame11.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(42, 11).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r45 = frame11.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(72, 56).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition frame12 = bone16.addOrReplaceChild("frame12", CubeListBuilder.create().texOffs(42, 17).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r46 = frame12.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(36, 43).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r47 = frame12.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(42, 20).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r48 = frame12.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(72, 59).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition main5 = controller.addOrReplaceChild("main5", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -3.1416F, 0.0F, -2.5307F));

		PartDefinition bone17 = main5.addOrReplaceChild("bone17", CubeListBuilder.create().texOffs(36, 79).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(94, 0).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-2.8F, 1.4F, 0.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition cube_r49 = bone17.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(16, 80).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.2F, -2.25F, 0.7397F, 1.146F, 0.8137F));

		PartDefinition bone18 = main5.addOrReplaceChild("bone18", CubeListBuilder.create().texOffs(0, 80).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(94, 21).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-9.2838F, 3.1931F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition cube_r50 = bone18.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(4, 0).addBox(-3.0F, -2.0F, -1.25F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.7F, -3.0F, 0.9993F, 1.1729F, 1.0556F));

		PartDefinition bone19 = main5.addOrReplaceChild("bone19", CubeListBuilder.create().texOffs(72, 80).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(28, 95).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-15.1649F, 6.4263F, 0.0F, 0.0F, 0.0F, -0.6545F));

		PartDefinition cube_r51 = bone19.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(-1, 32).addBox(-4.0F, -0.75F, -0.85F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.85F, -0.25F, -4.0F, 1.2143F, 1.1626F, 1.2712F));

		PartDefinition bone20 = main5.addOrReplaceChild("bone20", CubeListBuilder.create().texOffs(80, 0).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 96).addBox(-8.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(2.5F)), PartPose.offsetAndRotation(-20.2834F, 10.7916F, 0.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition frame13 = bone20.addOrReplaceChild("frame13", CubeListBuilder.create().texOffs(40, 24).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -1.0F, 0.0F));

		PartDefinition cube_r52 = frame13.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(36, 40).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r53 = frame13.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(40, 27).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r54 = frame13.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(54, 72).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition frame14 = bone20.addOrReplaceChild("frame14", CubeListBuilder.create().texOffs(42, 8).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r55 = frame14.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(42, 14).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r56 = frame14.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(42, 11).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r57 = frame14.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(72, 56).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition frame15 = bone20.addOrReplaceChild("frame15", CubeListBuilder.create().texOffs(42, 17).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r58 = frame15.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(36, 43).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r59 = frame15.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(42, 20).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r60 = frame15.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(72, 59).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition main6 = controller.addOrReplaceChild("main6", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -2.6208F, 0.6178F, -2.3611F));

		PartDefinition bone21 = main6.addOrReplaceChild("bone21", CubeListBuilder.create().texOffs(36, 79).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(94, 0).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-2.8F, 1.4F, 0.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition cube_r61 = bone21.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(16, 80).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.2F, -2.25F, 0.7397F, 1.146F, 0.8137F));

		PartDefinition bone22 = main6.addOrReplaceChild("bone22", CubeListBuilder.create().texOffs(0, 80).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(94, 21).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-9.2838F, 3.1931F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition cube_r62 = bone22.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(4, 0).addBox(-3.0F, -2.0F, -1.25F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.7F, -3.0F, 0.9993F, 1.1729F, 1.0556F));

		PartDefinition bone23 = main6.addOrReplaceChild("bone23", CubeListBuilder.create().texOffs(72, 80).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(28, 95).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-15.1649F, 6.4263F, 0.0F, 0.0F, 0.0F, -0.6545F));

		PartDefinition cube_r63 = bone23.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(-1, 32).addBox(-4.0F, -0.75F, -0.85F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.85F, -0.25F, -4.0F, 1.2143F, 1.1626F, 1.2712F));

		PartDefinition bone24 = main6.addOrReplaceChild("bone24", CubeListBuilder.create().texOffs(80, 0).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 96).addBox(-8.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(2.5F)), PartPose.offsetAndRotation(-20.2834F, 10.7916F, 0.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition frame16 = bone24.addOrReplaceChild("frame16", CubeListBuilder.create().texOffs(40, 24).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -1.0F, 0.0F));

		PartDefinition cube_r64 = frame16.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(36, 40).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r65 = frame16.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(40, 27).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r66 = frame16.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(54, 72).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition frame17 = bone24.addOrReplaceChild("frame17", CubeListBuilder.create().texOffs(42, 8).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r67 = frame17.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(42, 14).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r68 = frame17.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(42, 11).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r69 = frame17.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(72, 56).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition frame18 = bone24.addOrReplaceChild("frame18", CubeListBuilder.create().texOffs(42, 17).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r70 = frame18.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(36, 43).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r71 = frame18.addOrReplaceChild("cube_r71", CubeListBuilder.create().texOffs(42, 20).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r72 = frame18.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(72, 59).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition main7 = controller.addOrReplaceChild("main7", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.9599F, -1.5708F));

		PartDefinition bone25 = main7.addOrReplaceChild("bone25", CubeListBuilder.create().texOffs(36, 79).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(94, 0).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-2.8F, 1.4F, 0.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition cube_r73 = bone25.addOrReplaceChild("cube_r73", CubeListBuilder.create().texOffs(16, 80).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.2F, -2.25F, 0.7397F, 1.146F, 0.8137F));

		PartDefinition bone26 = main7.addOrReplaceChild("bone26", CubeListBuilder.create().texOffs(0, 80).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(94, 21).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-9.2838F, 3.1931F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition cube_r74 = bone26.addOrReplaceChild("cube_r74", CubeListBuilder.create().texOffs(4, 0).addBox(-3.0F, -2.0F, -1.25F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.7F, -3.0F, 0.9993F, 1.1729F, 1.0556F));

		PartDefinition bone27 = main7.addOrReplaceChild("bone27", CubeListBuilder.create().texOffs(72, 80).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(28, 95).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-15.1649F, 6.4263F, 0.0F, 0.0F, 0.0F, -0.6545F));

		PartDefinition cube_r75 = bone27.addOrReplaceChild("cube_r75", CubeListBuilder.create().texOffs(-1, 32).addBox(-4.0F, -0.75F, -0.85F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.85F, -0.25F, -4.0F, 1.2143F, 1.1626F, 1.2712F));

		PartDefinition bone28 = main7.addOrReplaceChild("bone28", CubeListBuilder.create().texOffs(80, 0).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 96).addBox(-8.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(2.5F)), PartPose.offsetAndRotation(-20.2834F, 10.7916F, 0.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition frame19 = bone28.addOrReplaceChild("frame19", CubeListBuilder.create().texOffs(40, 24).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -1.0F, 0.0F));

		PartDefinition cube_r76 = frame19.addOrReplaceChild("cube_r76", CubeListBuilder.create().texOffs(36, 40).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r77 = frame19.addOrReplaceChild("cube_r77", CubeListBuilder.create().texOffs(40, 27).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r78 = frame19.addOrReplaceChild("cube_r78", CubeListBuilder.create().texOffs(54, 72).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition frame20 = bone28.addOrReplaceChild("frame20", CubeListBuilder.create().texOffs(42, 8).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r79 = frame20.addOrReplaceChild("cube_r79", CubeListBuilder.create().texOffs(42, 14).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r80 = frame20.addOrReplaceChild("cube_r80", CubeListBuilder.create().texOffs(42, 11).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r81 = frame20.addOrReplaceChild("cube_r81", CubeListBuilder.create().texOffs(72, 56).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition frame21 = bone28.addOrReplaceChild("frame21", CubeListBuilder.create().texOffs(42, 17).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r82 = frame21.addOrReplaceChild("cube_r82", CubeListBuilder.create().texOffs(36, 43).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r83 = frame21.addOrReplaceChild("cube_r83", CubeListBuilder.create().texOffs(42, 20).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r84 = frame21.addOrReplaceChild("cube_r84", CubeListBuilder.create().texOffs(72, 59).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition main8 = controller.addOrReplaceChild("main8", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5208F, 0.6178F, -0.7805F));

		PartDefinition bone29 = main8.addOrReplaceChild("bone29", CubeListBuilder.create().texOffs(36, 79).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(94, 0).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-2.8F, 1.4F, 0.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition cube_r85 = bone29.addOrReplaceChild("cube_r85", CubeListBuilder.create().texOffs(16, 80).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.2F, -2.25F, 0.7397F, 1.146F, 0.8137F));

		PartDefinition bone30 = main8.addOrReplaceChild("bone30", CubeListBuilder.create().texOffs(0, 80).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(94, 21).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-9.2838F, 3.1931F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition cube_r86 = bone30.addOrReplaceChild("cube_r86", CubeListBuilder.create().texOffs(4, 0).addBox(-3.0F, -2.0F, -1.25F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.7F, -3.0F, 0.9993F, 1.1729F, 1.0556F));

		PartDefinition bone31 = main8.addOrReplaceChild("bone31", CubeListBuilder.create().texOffs(72, 80).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(28, 95).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-15.1649F, 6.4263F, 0.0F, 0.0F, 0.0F, -0.6545F));

		PartDefinition cube_r87 = bone31.addOrReplaceChild("cube_r87", CubeListBuilder.create().texOffs(-1, 32).addBox(-4.0F, -0.75F, -0.85F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.85F, -0.25F, -4.0F, 1.2143F, 1.1626F, 1.2712F));

		PartDefinition bone32 = main8.addOrReplaceChild("bone32", CubeListBuilder.create().texOffs(80, 0).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 96).addBox(-8.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(2.5F)), PartPose.offsetAndRotation(-20.2834F, 10.7916F, 0.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition frame22 = bone32.addOrReplaceChild("frame22", CubeListBuilder.create().texOffs(40, 24).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -1.0F, 0.0F));

		PartDefinition cube_r88 = frame22.addOrReplaceChild("cube_r88", CubeListBuilder.create().texOffs(36, 40).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r89 = frame22.addOrReplaceChild("cube_r89", CubeListBuilder.create().texOffs(40, 27).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r90 = frame22.addOrReplaceChild("cube_r90", CubeListBuilder.create().texOffs(54, 72).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition frame23 = bone32.addOrReplaceChild("frame23", CubeListBuilder.create().texOffs(42, 8).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r91 = frame23.addOrReplaceChild("cube_r91", CubeListBuilder.create().texOffs(42, 14).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r92 = frame23.addOrReplaceChild("cube_r92", CubeListBuilder.create().texOffs(42, 11).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r93 = frame23.addOrReplaceChild("cube_r93", CubeListBuilder.create().texOffs(72, 56).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition frame24 = bone32.addOrReplaceChild("frame24", CubeListBuilder.create().texOffs(42, 17).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r94 = frame24.addOrReplaceChild("cube_r94", CubeListBuilder.create().texOffs(36, 43).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r95 = frame24.addOrReplaceChild("cube_r95", CubeListBuilder.create().texOffs(42, 20).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r96 = frame24.addOrReplaceChild("cube_r96", CubeListBuilder.create().texOffs(72, 59).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack p_170625_, VertexConsumer p_170626_, int p_170627_, int p_170628_, int p_350603_) {
		super.renderToBuffer(p_170625_, p_170626_, p_170627_, p_170628_, p_350603_);
	}

	@Override
	public ModelPart root() {
		return controller;
	}
}