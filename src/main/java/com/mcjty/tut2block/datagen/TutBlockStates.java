package com.mcjty.tut2block.datagen;

import com.mcjty.tut2block.Registration;
import com.mcjty.tut2block.TutorialPower;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutBlockStates extends BlockStateProvider {

    public static final ResourceLocation BOTTOM = new ResourceLocation(TutorialPower.MODID, "block/machine_bottom");
    public static final ResourceLocation TOP = new ResourceLocation(TutorialPower.MODID, "block/machine_top");
    public static final ResourceLocation SIDE = new ResourceLocation(TutorialPower.MODID, "block/machine_side");

    public TutBlockStates(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TutorialPower.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        directionalBlock(Registration.GENERATOR_BLOCK.get(), state -> {
            if (state.getValue(BlockStateProperties.POWERED)) {
                return models().cube(Registration.GENERATOR_BLOCK.getId().getPath(), BOTTOM, TOP, modLoc("block/generator_block_on"), SIDE, SIDE, SIDE);
            } else {
                return models().cube(Registration.GENERATOR_BLOCK.getId().getPath(), BOTTOM, TOP, modLoc("block/generator_block_off"), SIDE, SIDE, SIDE);
            }
        });
    }
}

