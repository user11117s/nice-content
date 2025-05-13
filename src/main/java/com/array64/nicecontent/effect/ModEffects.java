package com.array64.nicecontent.effect;

import com.array64.nicecontent.NiceContent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> effects = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS,
            NiceContent.MOD_ID);
    public static final RegistryObject<MobEffect> DRUNK = effects.register("drunk",
            () -> new Drunk(MobEffectCategory.HARMFUL, 13434624));
    public static final RegistryObject<MobEffect> CREEPERNESS = effects.register("creeperness",
            () -> new Creeperness(MobEffectCategory.HARMFUL, 6553600));
    public static void register(IEventBus bus) { // lol wheels on the bus go round and round
        effects.register(bus);
    }
}
