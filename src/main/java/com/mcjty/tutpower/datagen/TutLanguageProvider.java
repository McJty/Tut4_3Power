package com.mcjty.tutpower.datagen;

import com.mcjty.tutpower.Registration;
import com.mcjty.tutpower.TutorialPower;
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
