package com.mcjty.tut2block.datagen;

import com.mcjty.tut2block.Tutorial4Power;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class TutLanguageProvider extends LanguageProvider {

    public TutLanguageProvider(PackOutput output, String locale) {
        super(output, Tutorial4Power.MODID, locale);
    }

    @Override
    protected void addTranslations() {
    }
}
