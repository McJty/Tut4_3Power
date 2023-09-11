package com.mcjty.tutpower.cables.blocks;

import com.mcjty.tutpower.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FacadeBlockEntity extends CableBlockEntity {

    @Nullable private BlockState mimicBlock = null;

    public FacadeBlockEntity(BlockPos pos, BlockState state) {
        super(Registration.FACADE_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket packet) {
        super.onDataPacket(net, packet);

        if (level.isClientSide) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
            requestModelDataUpdate();
        }
    }


    public @Nullable BlockState getMimicBlock() {
        return mimicBlock;
    }

    @Nonnull
    @Override
    public ModelData getModelData() {
        return ModelData.builder()
                .with(CableBlock.FACADEID, mimicBlock)
                .build();
    }


    public void setMimicBlock(BlockState mimicBlock) {
        this.mimicBlock = mimicBlock;
        setChanged();
        getLevel().sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_CLIENTS + Block.UPDATE_NEIGHBORS);
    }

    @Override
    public void load(CompoundTag tagCompound) {
        super.load(tagCompound);
        if (tagCompound != null && tagCompound.contains("mimic")) {
            mimicBlock = NbtUtils.readBlockState(BuiltInRegistries.BLOCK.asLookup(), tagCompound.getCompound("mimic"));
        } else {
            mimicBlock = null;
        }
    }

    @Override
    public void saveAdditional(@Nonnull CompoundTag tagCompound) {
        super.saveAdditional(tagCompound);
        if (mimicBlock != null) {
            CompoundTag tag = NbtUtils.writeBlockState(mimicBlock);
            tagCompound.put("mimic", tag);
        }
    }
}
