package com.array64.nicecontent.item.enchantment;

import com.array64.nicecontent.NiceContent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> enchantments = DeferredRegister.create(
            ForgeRegistries.ENCHANTMENTS, NiceContent.MOD_ID
    );
    public static final RegistryObject<Enchantment> COMEBACK = enchantments.register("comeback",
            () -> new Comeback(Enchantment.Rarity.RARE, EnchantmentCategory.ARMOR,
                    EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET));
    public static void register(IEventBus bus) {
        enchantments.register(bus);
    }
}