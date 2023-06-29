package com.mcjty.tut2block.datagen;

import com.mcjty.tut2block.Registration;
import com.mcjty.tut2block.TutorialPower;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutItemModels extends ItemModelProvider {

    public TutItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TutorialPower.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(Registration.GENERATOR_BLOCK.getId().getPath(), modLoc("block/simple_block"));
    }
}
