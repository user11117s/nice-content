package com.array64.nicecontent.potion;

import com.array64.nicecontent.NiceContent;
import com.array64.nicecontent.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPotions {
    public static final DeferredRegister<Potion> potions = DeferredRegister.create(ForgeRegistries.POTIONS,
                NiceContent.MOD_ID);
    public static final RegistryObject<Potion> VODKA = potions.register("vodka",
            () -> new Potion(new MobEffectInstance(ModEffects.DRUNK.get(), 1200, 4)));
    public static final RegistryObject<Potion> GRASS_VODKA = potions.register("grass_vodka",
            () -> new Potion(new MobEffectInstance(MobEffects.CONFUSION, 120, 0),
                    new MobEffectInstance(ModEffects.DRUNK.get(), 1200, 4)));
    public static final RegistryObject<Potion> POISONOUS_VODKA = potions.register("poisonous_potato_vodka",
            () -> new Potion(new MobEffectInstance(MobEffects.POISON, 240, 0),
                    new MobEffectInstance(ModEffects.DRUNK.get(), 1200, 4)));
    public static final RegistryObject<Potion> GRASS_POISONOUS_VODKA = potions.register("grass_poisonous_potato_vodka",
            () -> new Potion(new MobEffectInstance(MobEffects.CONFUSION, 120, 0),
                    new MobEffectInstance(MobEffects.POISON, 240, 0),
                    new MobEffectInstance(ModEffects.DRUNK.get(), 1200, 4)));
    public static final RegistryObject<Potion> CREEPERNESS = potions.register("creeperness",
            () -> new Potion(new MobEffectInstance(ModEffects.CREEPERNESS.get(), 1, 2)));
    public static final RegistryObject<Potion> STRONG_CREEPERNESS = potions.register("strong_creeperness",
            () -> new Potion(new MobEffectInstance(ModEffects.CREEPERNESS.get(), 1, 4)));
    public static void register(IEventBus bus) { // lol wheels on the bus go round and round
        potions.register(bus);
    }
}
