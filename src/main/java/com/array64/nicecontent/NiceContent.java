package com.array64.nicecontent;

import com.array64.nicecontent.block.ModBlocks;
// import client.BarShow;
// import client.TridentSwordModel;
// import client.ThrownTridentSwordRenderer;
import com.array64.nicecontent.client.BarShow;
import com.array64.nicecontent.client.ThrownTridentSwordRenderer;
import com.array64.nicecontent.client.TridentSwordModel;
import com.array64.nicecontent.effect.ModEffects;
import com.array64.nicecontent.entity.ModEntities;
import com.array64.nicecontent.item.ModItems;
import com.array64.nicecontent.item.enchantment.ModEnchantments;
import com.array64.nicecontent.network.FoodDataSyncher;
import com.array64.nicecontent.potion.ModPotions;
import com.array64.nicecontent.util.BetterBrewingRecipe;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(NiceContent.MOD_ID)
public class NiceContent
{
    public static final String MOD_ID = "nicecontent";
    public static final Logger LOGGER = LogUtils.getLogger();
    public NiceContent()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        bus.addListener(this::commonSetup);
        ModItems.register(bus);
        ModBlocks.register(bus);
        ModEntities.register(bus);
        ModPotions.register(bus);
        ModEffects.register(bus);
        ModEnchantments.register(bus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        FoodDataSyncher.init();
        event.enqueueWork(() -> {
            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(
                    Potions.WATER, Items.APPLE, ModPotions.VODKA.get()));
            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(
                    Potions.AWKWARD, ModBlocks.GUNPOWDER_BLOCK_ITEM.get(), ModPotions.CREEPERNESS.get()));
            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(
                    ModPotions.CREEPERNESS.get(), Items.GLOWSTONE_DUST, ModPotions.STRONG_CREEPERNESS.get()));
            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(
                    ModPotions.VODKA.get(), Items.GRASS, ModPotions.GRASS_VODKA.get()));
            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(
                    ModPotions.VODKA.get(), Items.POISONOUS_POTATO, ModPotions.POISONOUS_VODKA.get()));
            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(
                    ModPotions.POISONOUS_VODKA.get(), Items.GRASS, ModPotions.GRASS_POISONOUS_VODKA.get()));
            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(
                    ModPotions.GRASS_VODKA.get(), Items.POISONOUS_POTATO, ModPotions.GRASS_POISONOUS_VODKA.get()));
        });
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            event.enqueueWork(() -> {
                EntityRenderers.register(ModEntities.GRENADE.get(), ThrownItemRenderer::new);
                EntityRenderers.register(ModEntities.TRIDENT_SWORD.get(), ThrownTridentSwordRenderer::new);
            });
        }
        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("bar_show", BarShow.barShow);
        }
        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(TridentSwordModel.TEXTURE, TridentSwordModel::createLayer);
        }
    }
}
