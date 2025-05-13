package com.array64.nicecontent.mixin;

import com.array64.nicecontent.item.ModItems;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.SmithingMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.Map;
import java.util.Set;

@Mixin(SmithingMenu.class)
public class SmithingMenuMixin {
    @Inject(method = "createResult()V", at = @At("RETURN"))
    public void createResult(CallbackInfo info) {
        ResultContainer resultSlots = ((ICMenuMixin) (Object) this).getResultSlots();
        if(!resultSlots.getItem(0).is(ModItems.TRIDENT_SWORD.get())) return;
        ItemStack result = resultSlots.getItem(0);
        ListTag enchantments = result.getEnchantmentTags();
        Set<Map.Entry<Enchantment, Integer>> pEnchantments = result.getAllEnchantments().entrySet();
        enchantments.clear();
        for(Map.Entry<Enchantment, Integer> enchantment : pEnchantments) {
            if(enchantment.getKey() != Enchantments.RIPTIDE
            && enchantment.getKey() != Enchantments.IMPALING)
                enchantments.add(EnchantmentHelper.storeEnchantment(EnchantmentHelper
                    .getEnchantmentId(enchantment.getKey()), enchantment.getValue()));
        }
    }
}
