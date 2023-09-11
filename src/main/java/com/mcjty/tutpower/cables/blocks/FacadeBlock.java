package com.mcjty.tutpower.cables.blocks;

import com.mcjty.tutpower.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FacadeBlock extends CableBlock implements EntityBlock {

    public FacadeBlock() {
        super();
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new FacadeBlockEntity(pos, state);
    }

    @Override
    public void playerDestroy(@Nonnull Level level, @Nonnull Player player, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nullable BlockEntity te, @Nonnull ItemStack stack) {
        ItemStack item = new ItemStack(Registration.FACADE_BLOCK.get());
        BlockState mimicBlock;
        if (te instanceof FacadeBlockEntity) {
            mimicBlock = ((FacadeBlockEntity) te).getMimicBlock();
        } else {
            mimicBlock = Blocks.COBBLESTONE.defaultBlockState();
        }
        FacadeBlockItem.setMimicBlock(item, mimicBlock);
        popResource(level, pos, item);
    }

    private boolean replaceWithCable(LevelAccessor level, BlockPos pos, BlockState state) {
        BlockState defaultState = Registration.CABLE_BLOCK.get().defaultBlockState();
        BlockState newState = CableBlock.calculateState(level, pos, defaultState);
        // @todo needed?
        return level.setBlock(pos, newState, level.isClientSide()
                ? Block.UPDATE_ALL + Block.UPDATE_IMMEDIATE
                : Block.UPDATE_ALL);
    }

    @Override
    public void destroy(LevelAccessor level, @Nonnull BlockPos pos, @Nonnull BlockState state) {
        if (level.isClientSide()) {
            replaceWithCable(level, pos, state);
        }
    }


    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level world, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        return replaceWithCable(world, pos, state);
    }
}
