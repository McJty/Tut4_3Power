package com.mcjty.tut2block;

import com.mcjty.tut2block.compat.TopCompatibility;
import com.mcjty.tut2block.datagen.DataGeneration;
import com.mcjty.tut2block.network.Channel;
import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Tutorial4Power.MODID)
public class Tutorial4Power {

    public static final String MODID = "tut4power";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Tutorial4Power() {
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
