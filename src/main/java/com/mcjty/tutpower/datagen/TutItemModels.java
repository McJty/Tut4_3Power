package com.mcjty.tutpower.datagen;

import com.mcjty.tutpower.Registration;
import com.mcjty.tutpower.TutorialPower;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutItemModels extends ItemModelProvider {

    public TutItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TutorialPower.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(Registration.GENERATOR_BLOCK.getId().getPath(), modLoc("block/generator_block_off"));
        withExistingParent(Registration.CHARGER_BLOCK.getId().getPath(), modLoc("block/charger_block_on"));
        withExistingParent(Registration.CABLE_BLOCK.getId().getPath(), modLoc("block/cable"));
        withExistingParent(Registration.FACADE_BLOCK.getId().getPath(), modLoc("block/facade"));
    }
}
