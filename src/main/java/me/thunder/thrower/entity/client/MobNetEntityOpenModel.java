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

public class MobNetEntityOpenModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			ResourceLocation.fromNamespaceAndPath(Thrower.MODID, "mob_net_entity_open"), "main");
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

	public MobNetEntityOpenModel(ModelPart root) {
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

		PartDefinition controller = partdefinition.addOrReplaceChild("controller", CubeListBuilder.create().texOffs(42, 93).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 13.0F, 0.0F));

		PartDefinition main1 = controller.addOrReplaceChild("main1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bone = main1.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(36, 79).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(94, 0).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-2.8F, 1.4F, 0.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition cube_r1 = bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(14, 80).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.2F, -3.0F, 0.2074F, 1.169F, 0.2248F));

		PartDefinition bone2 = main1.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 80).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(94, 21).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-9.2838F, 3.1931F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition cube_r2 = bone2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -2.0F, -1.25F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 0.7F, -5.0F, 0.3366F, 1.1993F, 0.3673F));

		PartDefinition bone3 = main1.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(72, 80).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(28, 95).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-15.1649F, 6.4263F, 0.0F, 0.0F, 0.0F, -0.6545F));

		PartDefinition cube_r3 = bone3.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(22, 8).addBox(-3.7628F, -0.9533F, -1.4568F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.229F, 0.8704F, -9.9649F, 0.5604F, 1.0513F, 0.5661F));

		PartDefinition cube_r4 = bone3.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 32).addBox(-3.0F, -0.75F, -0.85F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.75F, -0.75F, -4.0F, 0.7494F, 1.3246F, 0.7777F));

		PartDefinition bone4 = main1.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(80, 0).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 96).addBox(-8.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(2.5F)), PartPose.offsetAndRotation(-20.2834F, 10.7916F, 0.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition frame = bone4.addOrReplaceChild("frame", CubeListBuilder.create().texOffs(40, 24).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -1.0F, 0.0F));

		PartDefinition cube_r5 = frame.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(36, 40).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r6 = frame.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(40, 27).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r7 = frame.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(54, 72).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition frame3 = bone4.addOrReplaceChild("frame3", CubeListBuilder.create().texOffs(42, 8).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r8 = frame3.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(42, 14).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r9 = frame3.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(42, 11).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r10 = frame3.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(72, 56).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition frame2 = bone4.addOrReplaceChild("frame2", CubeListBuilder.create().texOffs(42, 17).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r11 = frame2.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(36, 43).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r12 = frame2.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(42, 20).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r13 = frame2.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(72, 59).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition main2 = controller.addOrReplaceChild("main2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition bone5 = main2.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(50, 81).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(96, 4).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-2.8F, 1.4F, 0.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition cube_r14 = bone5.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(28, 83).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.2F, -3.0F, 0.2074F, 1.169F, 0.2248F));

		PartDefinition bone6 = main2.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(0, 84).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(8, 96).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-9.2838F, 3.1931F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition cube_r15 = bone6.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 4).addBox(-5.0F, -2.0F, -1.25F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 0.7F, -5.0F, 0.3366F, 1.1993F, 0.3673F));

		PartDefinition bone7 = main2.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(14, 84).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(96, 8).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-15.1649F, 6.4263F, 0.0F, 0.0F, 0.0F, -0.6545F));

		PartDefinition cube_r16 = bone7.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(22, 12).addBox(-3.7628F, -0.9533F, -1.4568F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.229F, 0.8704F, -9.9649F, 0.5604F, 1.0513F, 0.5661F));

		PartDefinition cube_r17 = bone7.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(18, 32).addBox(-3.0F, -0.75F, -0.85F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.75F, -0.75F, -4.0F, 0.7494F, 1.3246F, 0.7777F));

		PartDefinition bone8 = main2.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(64, 84).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(96, 12).addBox(-8.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(2.5F)), PartPose.offsetAndRotation(-20.2834F, 10.7916F, 0.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition frame4 = bone8.addOrReplaceChild("frame4", CubeListBuilder.create().texOffs(0, 44).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -1.0F, 0.0F));

		PartDefinition cube_r18 = frame4.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(44, 3).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r19 = frame4.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(44, 0).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r20 = frame4.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(72, 62).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition frame5 = bone8.addOrReplaceChild("frame5", CubeListBuilder.create().texOffs(18, 44).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r21 = frame5.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(0, 47).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r22 = frame5.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(36, 46).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r23 = frame5.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(72, 65).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition frame6 = bone8.addOrReplaceChild("frame6", CubeListBuilder.create().texOffs(18, 47).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r24 = frame6.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(0, 50).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r25 = frame6.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(36, 49).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r26 = frame6.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(72, 68).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition main3 = controller.addOrReplaceChild("main3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition bone9 = main3.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(78, 84).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(16, 96).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-2.8F, 1.4F, 0.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition cube_r27 = bone9.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(42, 85).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.2F, -3.0F, 0.2074F, 1.169F, 0.2248F));

		PartDefinition bone10 = main3.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(86, 80).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(96, 16).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-9.2838F, 3.1931F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition cube_r28 = bone10.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(0, 8).addBox(-5.0F, -2.0F, -1.25F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 0.7F, -5.0F, 0.3366F, 1.1993F, 0.3673F));

		PartDefinition bone11 = main3.addOrReplaceChild("bone11", CubeListBuilder.create().texOffs(28, 87).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(50, 96).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-15.1649F, 6.4263F, 0.0F, 0.0F, 0.0F, -0.6545F));

		PartDefinition cube_r29 = bone11.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(22, 16).addBox(-3.7628F, -0.9533F, -1.4568F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.229F, 0.8704F, -9.9649F, 0.5604F, 1.0513F, 0.5661F));

		PartDefinition cube_r30 = bone11.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(0, 36).addBox(-3.0F, -0.75F, -0.85F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.75F, -0.75F, -4.0F, 0.7494F, 1.3246F, 0.7777F));

		PartDefinition bone12 = main3.addOrReplaceChild("bone12", CubeListBuilder.create().texOffs(0, 88).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(58, 96).addBox(-8.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(2.5F)), PartPose.offsetAndRotation(-20.2834F, 10.7916F, 0.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition frame7 = bone12.addOrReplaceChild("frame7", CubeListBuilder.create().texOffs(18, 50).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -1.0F, 0.0F));

		PartDefinition cube_r31 = frame7.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(0, 53).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r32 = frame7.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(36, 52).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r33 = frame7.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(72, 71).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition frame8 = bone12.addOrReplaceChild("frame8", CubeListBuilder.create().texOffs(18, 53).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r34 = frame8.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(54, 33).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r35 = frame8.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(54, 30).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r36 = frame8.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(36, 73).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition frame9 = bone12.addOrReplaceChild("frame9", CubeListBuilder.create().texOffs(54, 36).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r37 = frame9.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(54, 42).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r38 = frame9.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(54, 39).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r39 = frame9.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(0, 74).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition main4 = controller.addOrReplaceChild("main4", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.3562F, 0.0F));

		PartDefinition bone13 = main4.addOrReplaceChild("bone13", CubeListBuilder.create().texOffs(14, 88).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(66, 96).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-2.8F, 1.4F, 0.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition cube_r40 = bone13.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(56, 88).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.2F, -3.0F, 0.2074F, 1.169F, 0.2248F));

		PartDefinition bone14 = main4.addOrReplaceChild("bone14", CubeListBuilder.create().texOffs(70, 88).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(74, 96).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-9.2838F, 3.1931F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition cube_r41 = bone14.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(0, 12).addBox(-5.0F, -2.0F, -1.25F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 0.7F, -5.0F, 0.3366F, 1.1993F, 0.3673F));

		PartDefinition bone15 = main4.addOrReplaceChild("bone15", CubeListBuilder.create().texOffs(84, 88).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(82, 96).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-15.1649F, 6.4263F, 0.0F, 0.0F, 0.0F, -0.6545F));

		PartDefinition cube_r42 = bone15.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(22, 20).addBox(-3.7628F, -0.9533F, -1.4568F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.229F, 0.8704F, -9.9649F, 0.5604F, 1.0513F, 0.5661F));

		PartDefinition cube_r43 = bone15.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(18, 36).addBox(-3.0F, -0.75F, -0.85F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.75F, -0.75F, -4.0F, 0.7494F, 1.3246F, 0.7777F));

		PartDefinition bone16 = main4.addOrReplaceChild("bone16", CubeListBuilder.create().texOffs(42, 89).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(90, 96).addBox(-8.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(2.5F)), PartPose.offsetAndRotation(-20.2834F, 10.7916F, 0.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition frame10 = bone16.addOrReplaceChild("frame10", CubeListBuilder.create().texOffs(54, 45).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -1.0F, 0.0F));

		PartDefinition cube_r44 = frame10.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(54, 51).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r45 = frame10.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(54, 48).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r46 = frame10.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(18, 74).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition frame11 = bone16.addOrReplaceChild("frame11", CubeListBuilder.create().texOffs(54, 54).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r47 = frame11.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(0, 56).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r48 = frame11.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(36, 55).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r49 = frame11.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(72, 74).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition frame12 = bone16.addOrReplaceChild("frame12", CubeListBuilder.create().texOffs(18, 56).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r50 = frame12.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(58, 23).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r51 = frame12.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(54, 57).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r52 = frame12.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(54, 75).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition main5 = controller.addOrReplaceChild("main5", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition bone17 = main5.addOrReplaceChild("bone17", CubeListBuilder.create().texOffs(90, 27).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(36, 97).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-2.8F, 1.4F, 0.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition cube_r53 = bone17.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(90, 31).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.2F, -3.0F, 0.2074F, 1.169F, 0.2248F));

		PartDefinition bone18 = main5.addOrReplaceChild("bone18", CubeListBuilder.create().texOffs(90, 35).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(98, 88).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-9.2838F, 3.1931F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition cube_r54 = bone18.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(0, 16).addBox(-5.0F, -2.0F, -1.25F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 0.7F, -5.0F, 0.3366F, 1.1993F, 0.3673F));

		PartDefinition bone19 = main5.addOrReplaceChild("bone19", CubeListBuilder.create().texOffs(90, 39).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(98, 92).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-15.1649F, 6.4263F, 0.0F, 0.0F, 0.0F, -0.6545F));

		PartDefinition cube_r55 = bone19.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(0, 24).addBox(-3.7628F, -0.9533F, -1.4568F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.229F, 0.8704F, -9.9649F, 0.5604F, 1.0513F, 0.5661F));

		PartDefinition cube_r56 = bone19.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(36, 32).addBox(-3.0F, -0.75F, -0.85F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.75F, -0.75F, -4.0F, 0.7494F, 1.3246F, 0.7777F));

		PartDefinition bone20 = main5.addOrReplaceChild("bone20", CubeListBuilder.create().texOffs(90, 43).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(98, 96).addBox(-8.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(2.5F)), PartPose.offsetAndRotation(-20.2834F, 10.7916F, 0.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition frame13 = bone20.addOrReplaceChild("frame13", CubeListBuilder.create().texOffs(58, 26).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -1.0F, 0.0F));

		PartDefinition cube_r57 = frame13.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(0, 59).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r58 = frame13.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(36, 58).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r59 = frame13.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(76, 21).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition frame14 = bone20.addOrReplaceChild("frame14", CubeListBuilder.create().texOffs(18, 59).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r60 = frame14.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(60, 9).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r61 = frame14.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(60, 6).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r62 = frame14.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(76, 24).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition frame15 = bone20.addOrReplaceChild("frame15", CubeListBuilder.create().texOffs(60, 12).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r63 = frame15.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(60, 18).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r64 = frame15.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(60, 15).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r65 = frame15.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(36, 76).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition main6 = controller.addOrReplaceChild("main6", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 2.3562F, 0.0F));

		PartDefinition bone21 = main6.addOrReplaceChild("bone21", CubeListBuilder.create().texOffs(90, 47).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(24, 99).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-2.8F, 1.4F, 0.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition cube_r66 = bone21.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(90, 51).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.2F, -3.0F, 0.2074F, 1.169F, 0.2248F));

		PartDefinition bone22 = main6.addOrReplaceChild("bone22", CubeListBuilder.create().texOffs(90, 55).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 100).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-9.2838F, 3.1931F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition cube_r67 = bone22.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(0, 20).addBox(-5.0F, -2.0F, -1.25F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 0.7F, -5.0F, 0.3366F, 1.1993F, 0.3673F));

		PartDefinition bone23 = main6.addOrReplaceChild("bone23", CubeListBuilder.create().texOffs(90, 59).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(8, 100).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-15.1649F, 6.4263F, 0.0F, 0.0F, 0.0F, -0.6545F));

		PartDefinition cube_r68 = bone23.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(20, 24).addBox(-3.7628F, -0.9533F, -1.4568F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.229F, 0.8704F, -9.9649F, 0.5604F, 1.0513F, 0.5661F));

		PartDefinition cube_r69 = bone23.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(36, 36).addBox(-3.0F, -0.75F, -0.85F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.75F, -0.75F, -4.0F, 0.7494F, 1.3246F, 0.7777F));

		PartDefinition bone24 = main6.addOrReplaceChild("bone24", CubeListBuilder.create().texOffs(90, 63).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(16, 100).addBox(-8.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(2.5F)), PartPose.offsetAndRotation(-20.2834F, 10.7916F, 0.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition frame16 = bone24.addOrReplaceChild("frame16", CubeListBuilder.create().texOffs(54, 60).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -1.0F, 0.0F));

		PartDefinition cube_r70 = frame16.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(0, 62).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r71 = frame16.addOrReplaceChild("cube_r71", CubeListBuilder.create().texOffs(36, 61).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r72 = frame16.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(0, 77).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition frame17 = bone24.addOrReplaceChild("frame17", CubeListBuilder.create().texOffs(62, 0).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r73 = frame17.addOrReplaceChild("cube_r73", CubeListBuilder.create().texOffs(18, 62).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r74 = frame17.addOrReplaceChild("cube_r74", CubeListBuilder.create().texOffs(62, 3).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r75 = frame17.addOrReplaceChild("cube_r75", CubeListBuilder.create().texOffs(18, 77).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition frame18 = bone24.addOrReplaceChild("frame18", CubeListBuilder.create().texOffs(54, 63).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r76 = frame18.addOrReplaceChild("cube_r76", CubeListBuilder.create().texOffs(0, 65).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r77 = frame18.addOrReplaceChild("cube_r77", CubeListBuilder.create().texOffs(36, 64).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r78 = frame18.addOrReplaceChild("cube_r78", CubeListBuilder.create().texOffs(72, 77).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition main7 = controller.addOrReplaceChild("main7", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition bone25 = main7.addOrReplaceChild("bone25", CubeListBuilder.create().texOffs(90, 67).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(44, 100).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-2.8F, 1.4F, 0.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition cube_r79 = bone25.addOrReplaceChild("cube_r79", CubeListBuilder.create().texOffs(90, 71).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.2F, -3.0F, 0.2074F, 1.169F, 0.2248F));

		PartDefinition bone26 = main7.addOrReplaceChild("bone26", CubeListBuilder.create().texOffs(90, 75).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(52, 100).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-9.2838F, 3.1931F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition cube_r80 = bone26.addOrReplaceChild("cube_r80", CubeListBuilder.create().texOffs(22, 0).addBox(-5.0F, -2.0F, -1.25F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 0.7F, -5.0F, 0.3366F, 1.1993F, 0.3673F));

		PartDefinition bone27 = main7.addOrReplaceChild("bone27", CubeListBuilder.create().texOffs(28, 91).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(60, 100).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-15.1649F, 6.4263F, 0.0F, 0.0F, 0.0F, -0.6545F));

		PartDefinition cube_r81 = bone27.addOrReplaceChild("cube_r81", CubeListBuilder.create().texOffs(0, 28).addBox(-3.7628F, -0.9533F, -1.4568F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.229F, 0.8704F, -9.9649F, 0.5604F, 1.0513F, 0.5661F));

		PartDefinition cube_r82 = bone27.addOrReplaceChild("cube_r82", CubeListBuilder.create().texOffs(0, 40).addBox(-3.0F, -0.75F, -0.85F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.75F, -0.75F, -4.0F, 0.7494F, 1.3246F, 0.7777F));

		PartDefinition bone28 = main7.addOrReplaceChild("bone28", CubeListBuilder.create().texOffs(0, 92).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(68, 100).addBox(-8.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(2.5F)), PartPose.offsetAndRotation(-20.2834F, 10.7916F, 0.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition frame19 = bone28.addOrReplaceChild("frame19", CubeListBuilder.create().texOffs(18, 65).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -1.0F, 0.0F));

		PartDefinition cube_r83 = frame19.addOrReplaceChild("cube_r83", CubeListBuilder.create().texOffs(36, 67).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r84 = frame19.addOrReplaceChild("cube_r84", CubeListBuilder.create().texOffs(54, 66).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r85 = frame19.addOrReplaceChild("cube_r85", CubeListBuilder.create().texOffs(78, 6).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition frame20 = bone28.addOrReplaceChild("frame20", CubeListBuilder.create().texOffs(0, 68).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r86 = frame20.addOrReplaceChild("cube_r86", CubeListBuilder.create().texOffs(54, 69).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r87 = frame20.addOrReplaceChild("cube_r87", CubeListBuilder.create().texOffs(18, 68).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r88 = frame20.addOrReplaceChild("cube_r88", CubeListBuilder.create().texOffs(78, 9).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition frame21 = bone28.addOrReplaceChild("frame21", CubeListBuilder.create().texOffs(36, 70).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r89 = frame21.addOrReplaceChild("cube_r89", CubeListBuilder.create().texOffs(18, 71).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r90 = frame21.addOrReplaceChild("cube_r90", CubeListBuilder.create().texOffs(0, 71).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r91 = frame21.addOrReplaceChild("cube_r91", CubeListBuilder.create().texOffs(78, 12).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition main8 = controller.addOrReplaceChild("main8", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition bone29 = main8.addOrReplaceChild("bone29", CubeListBuilder.create().texOffs(14, 92).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(76, 100).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-2.8F, 1.4F, 0.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition cube_r92 = bone29.addOrReplaceChild("cube_r92", CubeListBuilder.create().texOffs(56, 92).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.2F, -3.0F, 0.2074F, 1.169F, 0.2248F));

		PartDefinition bone30 = main8.addOrReplaceChild("bone30", CubeListBuilder.create().texOffs(70, 92).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(100, 79).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-9.2838F, 3.1931F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition cube_r93 = bone30.addOrReplaceChild("cube_r93", CubeListBuilder.create().texOffs(22, 4).addBox(-5.0F, -2.0F, -1.25F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 0.7F, -5.0F, 0.3366F, 1.1993F, 0.3673F));

		PartDefinition bone31 = main8.addOrReplaceChild("bone31", CubeListBuilder.create().texOffs(84, 92).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(84, 100).addBox(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-15.1649F, 6.4263F, 0.0F, 0.0F, 0.0F, -0.6545F));

		PartDefinition cube_r94 = bone31.addOrReplaceChild("cube_r94", CubeListBuilder.create().texOffs(20, 28).addBox(-3.7628F, -0.9533F, -1.4568F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.229F, 0.8704F, -9.9649F, 0.5604F, 1.0513F, 0.5661F));

		PartDefinition cube_r95 = bone31.addOrReplaceChild("cube_r95", CubeListBuilder.create().texOffs(18, 40).addBox(-3.0F, -0.75F, -0.85F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.75F, -0.75F, -4.0F, 0.7494F, 1.3246F, 0.7777F));

		PartDefinition bone32 = main8.addOrReplaceChild("bone32", CubeListBuilder.create().texOffs(92, 84).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(92, 100).addBox(-8.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(2.5F)), PartPose.offsetAndRotation(-20.2834F, 10.7916F, 0.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition frame22 = bone32.addOrReplaceChild("frame22", CubeListBuilder.create().texOffs(72, 29).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -1.0F, 0.0F));

		PartDefinition cube_r96 = frame22.addOrReplaceChild("cube_r96", CubeListBuilder.create().texOffs(72, 35).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r97 = frame22.addOrReplaceChild("cube_r97", CubeListBuilder.create().texOffs(72, 32).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r98 = frame22.addOrReplaceChild("cube_r98", CubeListBuilder.create().texOffs(78, 15).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition frame23 = bone32.addOrReplaceChild("frame23", CubeListBuilder.create().texOffs(72, 38).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r99 = frame23.addOrReplaceChild("cube_r99", CubeListBuilder.create().texOffs(72, 44).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r100 = frame23.addOrReplaceChild("cube_r100", CubeListBuilder.create().texOffs(72, 41).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r101 = frame23.addOrReplaceChild("cube_r101", CubeListBuilder.create().texOffs(78, 18).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition frame24 = bone32.addOrReplaceChild("frame24", CubeListBuilder.create().texOffs(72, 47).addBox(-4.0F, 3.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r102 = frame24.addOrReplaceChild("cube_r102", CubeListBuilder.create().texOffs(72, 53).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r103 = frame24.addOrReplaceChild("cube_r103", CubeListBuilder.create().texOffs(72, 50).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r104 = frame24.addOrReplaceChild("cube_r104", CubeListBuilder.create().texOffs(54, 78).addBox(-4.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

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