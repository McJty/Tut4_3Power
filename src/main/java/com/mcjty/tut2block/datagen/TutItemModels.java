package com.mcjty.tut2block.datagen;

import com.mcjty.tut2block.Tutorial4Power;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutItemModels extends ItemModelProvider {

    public TutItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Tutorial4Power.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
    }
}
