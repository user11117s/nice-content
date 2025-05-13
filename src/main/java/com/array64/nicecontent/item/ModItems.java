package com.array64.nicecontent.item;

import com.array64.nicecontent.NiceContent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> items = DeferredRegister.create(ForgeRegistries.ITEMS,
            NiceContent.MOD_ID);
    public static final RegistryObject<Item> GRENADE = items.register("grenade",
                () -> new GrenadeItem(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> TRIDENT_SWORD = items.register("trident_sword",
            () -> new TridentSword(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).durability(150)));
    public static final RegistryObject<Item> EXPLOSION_RESISTANCE = items.register("explosion_resistance",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).stacksTo(1)));
    public static void register(IEventBus bus) { // lol wheels on the bus go round and round
        items.register(bus);
    }
}
