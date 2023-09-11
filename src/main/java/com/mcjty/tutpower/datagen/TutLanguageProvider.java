package com.mcjty.tutpower.datagen;

import com.mcjty.tutpower.Registration;
import com.mcjty.tutpower.TutorialPower;
import com.mcjty.tutpower.blocks.GeneratorBlock;
import com.mcjty.tutpower.cables.blocks.FacadeBlock;
import com.mcjty.tutpower.cables.blocks.FacadeBlockItem;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class TutLanguageProvider extends LanguageProvider {

    public TutLanguageProvider(PackOutput output, String locale) {
        super(output, TutorialPower.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add(Registration.GENERATOR_BLOCK.get(), "Power Generator");
        add(Registration.CHARGER_BLOCK.get(), "Charger");
        add(Registration.CABLE_BLOCK.get(), "Cable");
        add(Registration.FACADE_BLOCK.get(), "Facade");
        add(GeneratorBlock.SCREEN_TUTORIAL_GENERATOR, "Generator");
        add(FacadeBlockItem.FACADE_IS_MIMICING, "Facade is mimicking %s");
        add("tab.tutpower", "Tutorial Power");
    }
}
