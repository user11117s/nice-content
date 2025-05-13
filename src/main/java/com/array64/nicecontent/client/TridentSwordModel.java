package com.array64.nicecontent.client;

import com.array64.nicecontent.NiceContent;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class TridentSwordModel extends Model {
    public static final ModelLayerLocation TEXTURE
        = new ModelLayerLocation(new ResourceLocation(NiceContent.MOD_ID, "textures/entity/trident_sword.png"), "main");
    private final ModelPart root;

    public TridentSwordModel(ModelPart pRoot) {
        super(RenderType::entitySolid);
        root = pRoot;
    }

    public static LayerDefinition createLayer() {
        MeshDefinition definition = new MeshDefinition();
        PartDefinition part = definition.getRoot();
        PartDefinition pole = part.addOrReplaceChild("pole", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5f, 0f, -0.5f, 1f, 5f, 1f), PartPose.ZERO);
        pole.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 6).addBox(-1.5f, 0f, -0.5f, 3f, 1f, 1f), PartPose.ZERO);
        pole.addOrReplaceChild("spike", CubeListBuilder.create().texOffs(4, 0).addBox(-0.5f, -3f, -0.5f, 1f, 3f, 1f), PartPose.ZERO);
        return LayerDefinition.create(definition, 8, 8);
    }

    public void renderToBuffer(@NotNull PoseStack p_103919_, @NotNull VertexConsumer p_103920_, int p_103921_,
                               int p_103922_, float p_103923_, float p_103924_, float p_103925_, float p_103926_) {
        this.root.render(p_103919_, p_103920_, p_103921_, p_103922_, p_103923_, p_103924_, p_103925_, p_103926_);
    }
}