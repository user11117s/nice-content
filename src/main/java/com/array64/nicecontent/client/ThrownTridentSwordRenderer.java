package com.array64.nicecontent.client;

import com.array64.nicecontent.NiceContent;
import com.array64.nicecontent.entity.ThrownTridentSword;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class ThrownTridentSwordRenderer extends EntityRenderer<ThrownTridentSword> {
    public static final ResourceLocation TRIDENT_LOCATION = new ResourceLocation(NiceContent.MOD_ID, "textures/entity/trident_sword.png");
    final TridentSwordModel model;
    public ThrownTridentSwordRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new TridentSwordModel(context.bakeLayer(TridentSwordModel.TEXTURE));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull ThrownTridentSword p_114482_) {
        return TRIDENT_LOCATION;
    }

    @Override
    public void render(ThrownTridentSword p_114485_, float p_114486_, float p_114487_, PoseStack p_114488_, @NotNull MultiBufferSource p_114489_, int p_114490_) {
        p_114488_.pushPose();
        p_114488_.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(p_114487_, p_114485_.yRotO, p_114485_.getYRot()) - 90.0F));
        p_114488_.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(p_114487_, p_114485_.xRotO, p_114485_.getXRot()) + 90.0F));
        VertexConsumer consumer = ItemRenderer.getFoilBufferDirect(p_114489_, model.renderType(getTextureLocation(p_114485_)), false, p_114485_.isFoil());
        model.renderToBuffer(p_114488_, consumer, p_114490_, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);
        p_114488_.popPose();
        super.render(p_114485_, p_114486_, p_114487_, p_114488_, p_114489_, p_114490_);
    }
}
