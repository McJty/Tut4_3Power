package com.mcjty.tut2block.datagen;

import com.mcjty.tut2block.Tutorial4Power;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutBlockStates extends BlockStateProvider {

    public TutBlockStates(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Tutorial4Power.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
    }
}

