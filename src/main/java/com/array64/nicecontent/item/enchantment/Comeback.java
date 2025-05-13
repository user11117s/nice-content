package com.array64.nicecontent.item.enchantment;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
public class Comeback extends Enchantment {

    protected Comeback(Rarity rarity, EnchantmentCategory category, EquipmentSlot... slots) {
        super(rarity, category, slots);
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    /* @Override
    public void doPostHurt(@NotNull LivingEntity attacker, @NotNull Entity target, int level) {
        if(!(target instanceof Player target2)) return;
        boolean crit = target2.getAttackStrengthScale(0.5f) > 0.9f && target2.fallDistance > 0.0F && !target2.isOnGround() && !target2.onClimbable() && !target2.isInWater() && !target2.hasEffect(MobEffects.BLINDNESS) && !target2.isPassenger();
        if(!crit) return;
        Map.Entry<EquipmentSlot, ItemStack> entry = EnchantmentHelper
                .getRandomItemWith(ModEnchantments.COMEBACK.get(), attacker);
        target2.hurt(comeback(attacker), level * 2.5f);
        if(entry != null) {
            entry.getValue().hurtAndBreak(1, attacker, (entity2) ->
                    entity2.broadcastBreakEvent(entry.getKey()));
        }
    } */
    public static DamageSource comeback(LivingEntity entity) {
        return new EntityDamageSource("comeback", entity).setMagic();
    }
}
