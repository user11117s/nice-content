package com.array64.nicecontent.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ThrownTridentSword extends ThrownTrident {
    public ThrownTridentSword(EntityType<? extends ThrownTridentSword> sword, Level level) {
        super(sword, level);
    }
    @Override
    public @NotNull ItemStack getPickupItem() {
        return super.getPickupItem();
    }

    /* @Override
    public void playerTouch(@NotNull Player p_37580_) {
        super.playerTouch(p_37580_);
        if(!p_37580_.level.isClientSide) {
            System.out.println(getPickupItem().getCount());
            System.out.println(switch(this.pickup) {
                case ALLOWED -> p_37580_.getInventory().add(this.getPickupItem());
                case CREATIVE_ONLY -> p_37580_.getAbilities().instabuild;
                default -> false;
            });
            System.out.println(((TridentMixin) this).getStack().getCount());
        }
    } */
}
