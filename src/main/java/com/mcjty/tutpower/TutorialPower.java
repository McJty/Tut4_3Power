package com.mcjty.tutpower;

import com.mcjty.tutpower.compat.TopCompatibility;
import com.mcjty.tutpower.datagen.DataGeneration;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import org.slf4j.Logger;

@Mod(TutorialPower.MODID)
public class TutorialPower {

    public static final String MODID = "tutpower";
    public static final Logger LOGGER = LogUtils.getLogger();

    public TutorialPower(IEventBus modEventBus) {
        Registration.init(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(DataGeneration::generate);
        modEventBus.addListener(this::registerCapabilities);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        TopCompatibility.register();
    }

    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, Registration.CHARGER_BLOCK_ENTITY.get(), (o, direction) -> o.getEnergyHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, Registration.GENERATOR_BLOCK_ENTITY.get(), (o, direction) -> o.getItemHandler());
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, Registration.GENERATOR_BLOCK_ENTITY.get(), (o, direction) -> o.getEnergyHandler());
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, Registration.CABLE_BLOCK_ENTITY.get(), (o, direction) -> o.getEnergyHandler());
    }
}
