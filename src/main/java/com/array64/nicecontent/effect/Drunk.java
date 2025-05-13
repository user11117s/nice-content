package com.array64.nicecontent.effect;

import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class Drunk extends MobEffect {
    public static final RandomSource rs = RandomSource.createNewThreadLocalInstance();
    protected Drunk(MobEffectCategory category, int amplifier) {
        super(category, amplifier);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        Vec3 velocity = entity.getDeltaMovement();
        Vec3 add = velocity.yRot(360f * rs.nextFloat()).scale(0.2f * (amplifier + 1));
        double yPos = entity.getY(), yVelocity = entity.getDeltaMovement().y;
        boolean originallyOnGround = entity.isOnGround();
        entity.move(MoverType.SELF, add);
        System.out.println(velocity);
        System.out.println(entity.isOnGround());
        entity.setPos(entity.getX(), yPos, entity.getZ());
        entity.setDeltaMovement(entity.getDeltaMovement().x,
                yVelocity, entity.getDeltaMovement().z);
        entity.setOnGround(originallyOnGround);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
