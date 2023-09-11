package com.mcjty.tutpower.datagen;

import com.google.gson.JsonObject;
import com.mcjty.tutpower.Registration;
import com.mcjty.tutpower.TutorialPower;
import com.mcjty.tutpower.cables.client.CableModelLoader;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.BiConsumer;

public class TutBlockStates extends BlockStateProvider {

    public static final ResourceLocation BOTTOM = new ResourceLocation(TutorialPower.MODID, "block/machine_bottom");
    public static final ResourceLocation TOP = new ResourceLocation(TutorialPower.MODID, "block/machine_top");
    public static final ResourceLocation SIDE = new ResourceLocation(TutorialPower.MODID, "block/machine_side");

    public TutBlockStates(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TutorialPower.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        registerGenerator();
        registerCharger();
        registerCable();
        registerFacade();
    }

    private void registerCable() {
        BlockModelBuilder model = models().getBuilder("cable")
                .parent(models().getExistingFile(mcLoc("cube")))
                .customLoader((builder, helper) -> new CableLoaderBuilder(CableModelLoader.GENERATOR_LOADER, builder, helper, false))
                .end();
        simpleBlock(Registration.CABLE_BLOCK.get(), model);
    }

    private void registerFacade() {
        BlockModelBuilder model = models().getBuilder("facade")
                .parent(models().getExistingFile(mcLoc("cube")))
                .customLoader((builder, helper) -> new CableLoaderBuilder(CableModelLoader.GENERATOR_LOADER, builder, helper, true))
                .end();
        simpleBlock(Registration.FACADE_BLOCK.get(), model);
    }

    private void registerCharger() {
        BlockModelBuilder modelOn = models().slab(Registration.CHARGER_BLOCK.getId().getPath()+"_on", SIDE, BOTTOM, modLoc("block/charger_block_on")).texture("particle", SIDE);
        BlockModelBuilder modelOff = models().slab(Registration.CHARGER_BLOCK.getId().getPath()+"_off", SIDE, BOTTOM, modLoc("block/charger_block")).texture("particle", SIDE);
        getVariantBuilder(Registration.CHARGER_BLOCK.get()).forAllStates(state -> {
            ConfiguredModel.Builder<?> bld = ConfiguredModel.builder();
            bld.modelFile(state.getValue(BlockStateProperties.POWERED) ? modelOn : modelOff);
            return bld.build();
        });
    }

    private void registerGenerator() {
        BlockModelBuilder modelOn = models().cube(Registration.GENERATOR_BLOCK.getId().getPath()+"_on", BOTTOM, TOP, modLoc("block/generator_block_on"), SIDE, SIDE, SIDE).texture("particle", SIDE);
        BlockModelBuilder modelOff = models().cube(Registration.GENERATOR_BLOCK.getId().getPath()+"_off", BOTTOM, TOP, modLoc("block/generator_block"), SIDE, SIDE, SIDE).texture("particle", SIDE);
        directionBlock(Registration.GENERATOR_BLOCK.get(), (state, builder) -> {
            builder.modelFile(state.getValue(BlockStateProperties.POWERED) ? modelOn : modelOff);
        });
    }

    private VariantBlockStateBuilder directionBlock(Block block, BiConsumer<BlockState, ConfiguredModel.Builder<?>> model) {
        VariantBlockStateBuilder builder = getVariantBuilder(block);
        builder.forAllStates(state -> {
            ConfiguredModel.Builder<?> bld = ConfiguredModel.builder();
            model.accept(state, bld);
            applyRotationBld(bld, state.getValue(BlockStateProperties.FACING));
            return bld.build();
        });
        return builder;
    }

    private void applyRotationBld(ConfiguredModel.Builder<?> builder, Direction direction) {
        switch (direction) {
            case DOWN -> builder.rotationX(90);
            case UP -> builder.rotationX(-90);
            case NORTH -> { }
            case SOUTH -> builder.rotationY(180);
            case WEST -> builder.rotationY(270);
            case EAST -> builder.rotationY(90);
        }
    }

    public static class CableLoaderBuilder extends CustomLoaderBuilder<BlockModelBuilder> {

        private final boolean facade;

        public CableLoaderBuilder(ResourceLocation loader, BlockModelBuilder parent, ExistingFileHelper existingFileHelper,
                                  boolean facade) {
            super(loader, parent, existingFileHelper);
            this.facade = facade;
        }

        @Override
        public JsonObject toJson(JsonObject json) {
            JsonObject obj = super.toJson(json);
            obj.addProperty("facade", facade);
            return obj;
        }
    }
}

