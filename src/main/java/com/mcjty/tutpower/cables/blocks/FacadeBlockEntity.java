package com.mcjty.tutpower.cables.blocks;

import com.mcjty.tutpower.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FacadeBlockEntity extends CableBlockEntity {

    public static final String MIMIC_TAG = "mimic";

    @Nullable private BlockState mimicBlock = null;

    public FacadeBlockEntity(BlockPos pos, BlockState state) {
        super(Registration.FACADE_BLOCK_ENTITY.get(), pos, state);
    }

    // The default onDataPacket() will call load() to load the data from the packet.
    // In addition to that we send a block update to the client
    // and also request a model data update (for the cable baked model)
    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket packet) {
        super.onDataPacket(net, packet);

        if (level.isClientSide) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
            requestModelDataUpdate();
        }
    }

    // getUpdatePacket() is called on the server when a block is placed or updated.
    // It should return a packet containing all information needed to render this block on the client.
    // In our case this is the block mimic information. On the client side onDataPacket() is called
    // with this packet.
    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        CompoundTag nbtTag = new CompoundTag();
        saveMimic(nbtTag);
        return ClientboundBlockEntityDataPacket.create(this, (BlockEntity entity) -> {return nbtTag;});
    }

    // getUpdateTag() is called on the server on initial load of the chunk. It will cause
    // the packet to be sent to the client and handleUpdateTag() will be called on the client.
    // The default implementation of handleUpdateTag() will call load() to load the data from the packet.
    // In our case this is sufficient
    @Nonnull
    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag updateTag = super.getUpdateTag();
        saveMimic(updateTag);
        return updateTag;
    }

    public @Nullable BlockState getMimicBlock() {
        return mimicBlock;
    }

    // This is used to build the model data for the cable baked model.
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
        loadMimic(tagCompound);
    }

    private void loadMimic(CompoundTag tagCompound) {
        if (tagCompound.contains(MIMIC_TAG)) {
            mimicBlock = NbtUtils.readBlockState(BuiltInRegistries.BLOCK.asLookup(), tagCompound.getCompound(MIMIC_TAG));
        } else {
            mimicBlock = null;
        }
    }

    @Override
    public void saveAdditional(@Nonnull CompoundTag tagCompound) {
        super.saveAdditional(tagCompound);
        saveMimic(tagCompound);
    }

    private void saveMimic(@NotNull CompoundTag tagCompound) {
        if (mimicBlock != null) {
            CompoundTag tag = NbtUtils.writeBlockState(mimicBlock);
            tagCompound.put(MIMIC_TAG, tag);
        }
    }
}
