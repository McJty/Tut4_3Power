package com.mcjty.tutpower;

import com.mcjty.tutpower.compat.TopCompatibility;
import com.mcjty.tutpower.datagen.DataGeneration;
import com.mcjty.tutpower.network.Channel;
import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(TutorialPower.MODID)
public class TutorialPower {

    public static final String MODID = "tutpower";
    public static final Logger LOGGER = LogUtils.getLogger();

    public TutorialPower() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        Registration.init(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(DataGeneration::generate);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        Channel.register();
        TopCompatibility.register();
    }
}
