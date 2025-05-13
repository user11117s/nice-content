package com.array64.nicecontent.item;

import com.array64.nicecontent.entity.Grenade;
import com.array64.nicecontent.entity.ModEntities;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class GrenadeItem extends Item {
    public GrenadeItem(Properties p_41383_) {
        super(p_41383_);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack items = player.getItemInHand(hand);
        Vec3 pos = player.position();
        if(!level.isClientSide()) {
            Grenade grenade = new Grenade(ModEntities.GRENADE.get(), level);
            grenade.setOwner(player);
            grenade.setPos(player.getEyePosition());
            grenade.setItem(items);
            grenade.shootFromRotation(player, player.getXRot(), player.getYRot(), 0f, 1.5f, 1f);
            level.addFreshEntity(grenade);
        }
        if(!player.getAbilities().instabuild) items.shrink(1);
        level.playSound(null, pos.x, pos.y, pos.z, SoundEvents.SNOWBALL_THROW, SoundSource.PLAYERS, 1f, 1f);
        return InteractionResultHolder.sidedSuccess(items, level.isClientSide());
    }
}
