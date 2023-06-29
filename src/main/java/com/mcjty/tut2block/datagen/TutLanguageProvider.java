package com.mcjty.tut2block.datagen;

import com.mcjty.tut2block.Registration;
import com.mcjty.tut2block.TutorialPower;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class TutLanguageProvider extends LanguageProvider {

    public TutLanguageProvider(PackOutput output, String locale) {
        super(output, TutorialPower.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add(Registration.GENERATOR_BLOCK.get(), "Power Generator");
    }
}
