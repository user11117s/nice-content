package com.array64.nicecontent.item;

import com.array64.nicecontent.entity.ThrownTridentSword;
import com.array64.nicecontent.mixin.TridentMixin;
import com.array64.nicecontent.entity.ModEntities;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;
import static com.array64.nicecontent.NiceContent.LOGGER;

public class TridentSword extends TridentItem {
    Multimap<Attribute, AttributeModifier> defaultModifiers;
    public TridentSword(Properties p_41383_) {
        super(p_41383_);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", 5.5d, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", -2.2f, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }
    @Override
    public void releaseUsing(@NotNull ItemStack p_43394_, @NotNull Level p_43395_, @NotNull LivingEntity p_43396_, int p_43397_) {
        try {
            if(p_43396_ instanceof Player player) {
                int i = this.getUseDuration(p_43394_) - p_43397_;
                if(i >= 10) {
                    if(!p_43395_.isClientSide) {
                        p_43394_.hurtAndBreak(1, player, (p_43388_) -> p_43388_.broadcastBreakEvent(p_43396_.getUsedItemHand()));
                        ThrownTridentSword trident = new ThrownTridentSword(ModEntities.TRIDENT_SWORD.get(), p_43395_);
                        ((TridentMixin) trident).setStack(p_43394_.copy());
                        trident.getEntityData().set(TridentMixin.loyalty(), (byte) EnchantmentHelper.getLoyalty(p_43394_));
                        trident.getEntityData().set(TridentMixin.foil(), p_43394_.hasFoil());
                        trident.setOwner(p_43396_);
                        trident.setPos(p_43396_.getEyePosition());
                        trident.shootFromRotation(player, player.getXRot(), player.getYRot(), 0f, 2.5f, 1f);
                        if(player.getAbilities().instabuild) {
                            trident.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                        } else trident.pickup = AbstractArrow.Pickup.ALLOWED;
                        p_43395_.addFreshEntity(trident);
                        p_43395_.playSound(null, trident, SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1f, 1f);
                        if(!player.getAbilities().instabuild) {
                            p_43394_.shrink(1);
                        }
                    }
                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        } catch(Exception e) {
            LOGGER.info("Failed to spawn Thrown Trident Sword");
        }
    }
    @Override
    public int getUseDuration(@NotNull ItemStack p_40680_) {
        return 50000;
    }

    @Override
    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot p_43383_) {
        return p_43383_ == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(p_43383_);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        return super.canPerformAction(stack, toolAction) || toolAction.equals(ToolActions.SWORD_SWEEP);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if(enchantment.equals(Enchantments.IMPALING)
        || enchantment.equals(Enchantments.RIPTIDE))
            return false;
        if(enchantment.category.equals(EnchantmentCategory.WEAPON))
            return true;
        return super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @Override
    public boolean isCorrectToolForDrops(BlockState p_41450_) {
        return p_41450_.is(Blocks.COBWEB);
    }

    @Override
    public float getDestroySpeed(ItemStack p_41425_, BlockState p_41426_) {
        if (p_41426_.is(Blocks.COBWEB)) {
            return 15f;
        } else {
            Material material = p_41426_.getMaterial();
            return material != Material.PLANT && material != Material.REPLACEABLE_PLANT && !p_41426_.is(BlockTags.LEAVES) && material != Material.VEGETABLE ? 1f : 1.5f;
        }
    }
}
