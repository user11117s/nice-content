package com.array64.nicecontent.entity;

import com.array64.nicecontent.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class Grenade extends ThrowableItemProjectile {
    public Grenade(EntityType<Grenade> grenade, Level level) {
        super(grenade, level);
    }
    @Override
    protected void onHit(@NotNull HitResult result) {
        if(!level.isClientSide()) {
            Vec3 hit = result.getLocation();
            level.explode(this, hit.x, hit.y,
                    hit.z, 4, Explosion.BlockInteraction.BREAK);
            kill();
        }
        super.onHit(result);
    }
    @Override
    protected @NotNull Item getDefaultItem() {
        return ModItems.GRENADE.get();
    }
}