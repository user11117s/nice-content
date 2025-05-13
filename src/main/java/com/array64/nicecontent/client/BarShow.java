package com.array64.nicecontent.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.food.FoodData;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class BarShow {
    public static int reach = 0;
    public static final IGuiOverlay barShow = (gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        try {
            LocalPlayer player = Minecraft.getInstance().player;
            if(player.isSpectator()) return;
            Font font = Minecraft.getInstance().font;
            font.drawShadow(poseStack, (reach % 100 == 0 ? Integer.toString((int) (reach / 100f))
                            : (float) reach / 100f) + " block" + (reach == 100 ? "" : "s"),
                    9f, 9f, 11579568);
            if(player.getAbilities().instabuild) return;
            FoodData data = player.getFoodData();
            int health = Math.round(player.getHealth() * 10);
            int hunger = data.getFoodLevel();
            int saturation = Math.round(data.getSaturationLevel() * 10f);
            int exhaustion = Math.round(data.getExhaustionLevel() * 10f);
            int armor = player.getArmorValue();
            font.drawShadow(poseStack, Float.toString((float) health / 10f),
                    screenWidth / 2f - 115f, screenHeight - 38f, 16742520);
            font.drawShadow(poseStack, Integer.toString(armor),
                    screenWidth / 2f - 115f, screenHeight - 50f, 11579568);
            font.drawShadow(poseStack, Integer.toString(hunger),
                    screenWidth / 2f + 95f, screenHeight - 50f, 16742520);
            font.drawShadow(poseStack, Float.toString((float) saturation / 10f),
                    screenWidth / 2f + 95f, screenHeight - 38f, 16776960);
            font.drawShadow(poseStack, Float.toString(exhaustion / 10f),
                    screenWidth / 2f + 95f, screenHeight - 26f, 11579568);
        }
        catch(Exception ignored) {}
    };
}
