package com.array64.nicecontent.event;

import com.array64.nicecontent.client.BarShow;
import com.array64.nicecontent.NiceContent;
import com.array64.nicecontent.entity.ThrownTridentSword;
import com.array64.nicecontent.item.enchantment.Comeback;
import com.array64.nicecontent.item.enchantment.ModEnchantments;
import com.array64.nicecontent.item.ModItems;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = NiceContent.MOD_ID,
            value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void laResistanceAuxExplosions(LivingAttackEvent event) {
            if(event.getSource().isExplosion()) {
                if(event.getEntity().isHolding(ModItems.EXPLOSION_RESISTANCE.get()))
                    event.setCanceled(true);
            }
        }
        @SubscribeEvent
        public static void comeback(CriticalHitEvent event) {
            if(!(event.getDamageModifier() > 1.49f)) return;
            if(!(event.getTarget() instanceof LivingEntity target)) return;
            Player attacker = event.getEntity();
            Map.Entry<EquipmentSlot, ItemStack> entry = EnchantmentHelper
                    .getRandomItemWith(ModEnchantments.COMEBACK.get(), target);
            if(entry != null) {
                attacker.hurt(Comeback.comeback(target), entry.getValue()
                        .getEnchantmentLevel(ModEnchantments.COMEBACK.get()) * 2.5f);
                entry.getValue().hurtAndBreak(1, attacker, (entity2) ->
                        entity2.broadcastBreakEvent(entry.getKey()));
            }
        }
        @SubscribeEvent
        public static void tridentSwordDamageReduce(LivingAttackEvent event) {
            if(!(event.getSource() instanceof IndirectEntityDamageSource source)) return;
            if(source instanceof TridentSwordDamageSource) return;
            if(!(source.getDirectEntity() instanceof ThrownTridentSword)) return;
            event.setCanceled(true);
            event.getEntity().hurt(new TridentSwordDamageSource("trident",
                    source.getDirectEntity(), source.getEntity()), 6.5f);
        }
        @SubscribeEvent
        public static void getReach(AttackEntityEvent event) {
            if(!(event.getTarget() instanceof LivingEntity target)) return;
            BarShow.reach = Math.round((float) getReach(event.getEntity(), target) * 100f);
        }
        static double getReach(Player attacker, LivingEntity target) {
            /* Vec3 reach;
            if(target.getEyeY() > attacker.getEyeY())
                reach = target.getEyePosition().subtract(attacker.position()
                        .add(0d, attacker.getBbHeight(), 0d));
            else if(attacker.getY() <= target.getEyeY())
                reach = new Vec3(attacker.getX() - target.getX(), 0f,
                        attacker.getZ() - target.getZ());
            else reach = attacker.position().subtract(target.getEyePosition());
            Vec3 diff = attacker.position().subtract(target.position());
            float width = attacker.getBbWidth() / 2;
            if(Mth.abs((float) diff.x) < width &&
            Mth.abs((float) diff.z) < width)
                return reach.add(-reach.x, 0f, -reach.z);
            if(Mth.abs((float) diff.x) < width)
                return reach.add(-reach.x, 0f, diff.z < 0d ? width : -width);
            if(Mth.abs((float) diff.z) < width)
                return reach.add(diff.x < 0d ? width : -width, 0f, -reach.z);
            return reach.add(diff.x < 0d ? width : -width, 0f, diff.z < 0d ? width : -width); */
            Vec3 eye = target.getEyePosition();
            Vec3 targetCenter = attacker.getPosition(1.0F).add(0, attacker.getBbHeight() / 2, 0);
            Optional<Vec3> hit = attacker.getBoundingBox().clip(eye, targetCenter);
            return hit.map(eye::distanceTo).orElseGet(() -> (double) target.distanceTo(attacker));
        }
        static class TridentSwordDamageSource extends IndirectEntityDamageSource {
            public TridentSwordDamageSource(String p_19406_, Entity p_19407_, @Nullable Entity p_19408_) {
                super(p_19406_, p_19407_, p_19408_);
            }
        }
    }
}
