package com.array64.nicecontent.network;

import com.array64.nicecontent.NiceContent;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.nio.ByteBuffer;
import java.util.function.Supplier;

public class FoodDataMessage {
    public float saturation, exhaustion;
    public FoodDataMessage(float sat, float exh) {
        saturation = sat;
        exhaustion = exh;
    }
    public static void encode(FoodDataMessage message, FriendlyByteBuf buffer) {
        buffer.writeLong(ByteBuffer.allocate(8).putFloat(message.saturation)
                .putFloat(message.exhaustion).getLong(0));
    }
    public static FoodDataMessage decode(FriendlyByteBuf buffer) {
        long data = buffer.readLong();
        return new FoodDataMessage(Float.intBitsToFloat((int) (data >>> 32)),
                Float.intBitsToFloat((int) data));
    }
    public static void handle(final FoodDataMessage message, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {
            FoodData data = getSidedPlayer(context).getFoodData();
            data.setSaturation(message.saturation);
            data.setExhaustion(message.exhaustion);
        });
        context.setPacketHandled(true);
    }
    public static Player getSidedPlayer(NetworkEvent.Context ctx) {
        return ctx.getDirection() == NetworkDirection.PLAY_TO_SERVER ? ctx.getSender() : Minecraft.getInstance().player;
    }
    public String toString() {
        return "(" + saturation + ", " + exhaustion + ")";
    }
}
