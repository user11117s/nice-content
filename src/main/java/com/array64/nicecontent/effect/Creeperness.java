package com.array64.nicecontent.effect;

import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;

public class Creeperness extends InstantenousMobEffect {
    public Creeperness(MobEffectCategory category, int color) {
        super(category, color);
    }
    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if(!entity.level.isClientSide()) entity.level.explode(null, entity.getX(), entity.getY(), entity.getZ(),
                amplifier + 1, Explosion.BlockInteraction.BREAK);
    }

    @Override
    public boolean isDurationEffectTick(int p_19444_, int p_19445_) {
        return true;
    }
}
