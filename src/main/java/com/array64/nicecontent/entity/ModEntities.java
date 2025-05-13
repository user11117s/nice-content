package com.array64.nicecontent.entity;

import com.array64.nicecontent.NiceContent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> entities = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,
            NiceContent.MOD_ID);
    public static final RegistryObject<EntityType<Grenade>> GRENADE = entities.register("grenade",
            () -> EntityType.Builder.of(Grenade::new,
                    MobCategory.MISC).sized(0.25f, 0.25f)
                    .build(new ResourceLocation(NiceContent.MOD_ID, "grenade").toString()));
    public static final RegistryObject<EntityType<ThrownTridentSword>> TRIDENT_SWORD = entities.register("trident_sword",
            () -> EntityType.Builder.of(ThrownTridentSword::new,
                    MobCategory.MISC).sized(0.5f, 0.5f)
                    .clientTrackingRange(4).updateInterval(10)
                    .build(new ResourceLocation(NiceContent.MOD_ID, "trident_sword").toString()));
    public static void register(IEventBus bus) { // lol wheels on the bus go round and round
        entities.register(bus);
    }
}
