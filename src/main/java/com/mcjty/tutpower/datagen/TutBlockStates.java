package com.mcjty.tutpower.datagen;

import com.mcjty.tutpower.Registration;
import com.mcjty.tutpower.TutorialPower;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
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
        BlockModelBuilder modelOn = models().cube(Registration.GENERATOR_BLOCK.getId().getPath(), BOTTOM, modLoc("block/generator_block_on"), SIDE, SIDE, SIDE, TOP);
        BlockModelBuilder modelOff = models().cube(Registration.GENERATOR_BLOCK.getId().getPath()+"_on", BOTTOM, modLoc("block/generator_block"), SIDE, SIDE, SIDE, TOP);
        directionalBlock(Registration.GENERATOR_BLOCK.get(), state -> {
            if (state.getValue(BlockStateProperties.POWERED)) {
                return modelOn;
            } else {
                return modelOff;
            }
        });
    }
}

