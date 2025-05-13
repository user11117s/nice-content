package com.array64.nicecontent.mixin;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ThrownTrident.class)
public interface TridentMixin {
    @Accessor("tridentItem")
    void setStack(ItemStack stack);
    @Accessor("tridentItem")
    ItemStack getStack();
    @Accessor("ID_LOYALTY")
    static EntityDataAccessor<Byte> loyalty() {
        return null;
    }
    @Accessor("ID_FOIL")
    static EntityDataAccessor<Boolean> foil() {
        return null;
    }
}
