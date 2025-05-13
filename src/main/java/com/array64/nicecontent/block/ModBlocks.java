package com.array64.nicecontent.block;

import com.array64.nicecontent.NiceContent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> blocks = DeferredRegister.create(ForgeRegistries.BLOCKS,
            NiceContent.MOD_ID);
    public static final DeferredRegister<Item> items = DeferredRegister.create(ForgeRegistries.ITEMS,
            NiceContent.MOD_ID);
    public static RegistryObject<Block> GUNPOWDER_BLOCK = blocks.register("gunpowder_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().destroyTime(2f)));
    public static final RegistryObject<Item> GUNPOWDER_BLOCK_ITEM = items.register("gunpowder_block", () -> new BlockItem(GUNPOWDER_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
    public static void register(IEventBus bus) { // lol wheels on the bus go round and round
        items.register(bus);
        blocks.register(bus);
    }
}
