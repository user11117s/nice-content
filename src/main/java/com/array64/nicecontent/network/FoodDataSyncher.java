package com.array64.nicecontent.network;

import com.array64.nicecontent.NiceContent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.food.FoodData;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FoodDataSyncher {
    static final String PROTOCOL_VERSION = Integer.toString(1);
    public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(NiceContent.MOD_ID, "data_syncher"))
            .clientAcceptedVersions(s -> true).serverAcceptedVersions(s -> true)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();
    private static final Map<UUID, FoodDataMessage> lastMessages = new HashMap<>();
    public static void init() {
        CHANNEL.registerMessage(1, FoodDataMessage.class, FoodDataMessage::encode, FoodDataMessage::decode, FoodDataMessage::handle);
        MinecraftForge.EVENT_BUS.register(new FoodDataSyncher());
    }
    @SubscribeEvent
    public void updateData(LivingEvent.LivingTickEvent event) {
        if(!(event.getEntity() instanceof ServerPlayer player)) return;
        FoodDataMessage lastMessage = lastMessages.get(player.getUUID());
        FoodData data = player.getFoodData();
        Object msg = new Object();
        boolean dataMissing = false;
        if(lastMessage == null) {
            dataMissing = true;
            msg = new FoodDataMessage(data.getSaturationLevel(), data.getExhaustionLevel());
        } else if(lastMessage.saturation != data.getSaturationLevel()) {
            dataMissing = true;
            msg = new FoodDataMessage(data.getSaturationLevel(), lastMessage.exhaustion);
        } else if(Math.abs(lastMessage.exhaustion - data.getExhaustionLevel()) > 0.01f) {
            dataMissing = true;
            msg = new FoodDataMessage(lastMessage.saturation, data.getExhaustionLevel());
        }
        if(dataMissing) {
            CHANNEL.sendTo(msg, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
            lastMessages.put(player.getUUID(), (FoodDataMessage) msg);
        }
    }
    @SubscribeEvent
    public void login(PlayerEvent.PlayerLoggedInEvent event) {
        if(!(event.getEntity() instanceof ServerPlayer player)) return;
        lastMessages.remove(player.getUUID());
    }
}
