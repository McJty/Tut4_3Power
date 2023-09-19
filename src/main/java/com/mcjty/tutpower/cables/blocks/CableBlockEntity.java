package com.mcjty.tutpower.cables.blocks;

import com.mcjty.tutpower.Registration;
import com.mcjty.tutpower.tools.AdaptedEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class CableBlockEntity extends BlockEntity {

    public static final String ENERGY_TAG = "Energy";

    public static final int MAXTRANSFER = 100;
    public static final int CAPACITY = 1000;

    private final EnergyStorage energy = createEnergyStorage();
    private final LazyOptional<IEnergyStorage> energyHandler = LazyOptional.of(() -> new AdaptedEnergyStorage(energy) {
        @Override
        public int extractEnergy(int maxExtract, boolean simulate) {
            return 0;
        }

        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            setChanged();
            return super.receiveEnergy(maxReceive, simulate);
        }

        @Override
        public boolean canExtract() {
            return false;
        }

        @Override
        public boolean canReceive() {
            return true;
        }
    });

    // Cached outputs
    private Set<BlockPos> outputs = null;

    protected CableBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public CableBlockEntity(BlockPos pos, BlockState state) {
        super(Registration.CABLE_BLOCK_ENTITY.get(), pos, state);
    }

    public void tickServer() {
        if (energy.getEnergyStored() > 0) {
            // Only do something if we have energy
            checkOutputs();
            if (!outputs.isEmpty()) {
                // Distribute energy over all outputs
                int amount = energy.getEnergyStored() / outputs.size();
                for (BlockPos p : outputs) {
                    BlockEntity te = level.getBlockEntity(p);
                    if (te != null) {
                        te.getCapability(ForgeCapabilities.ENERGY).ifPresent(handler -> {
                            if (handler.canReceive()) {
                                int received = handler.receiveEnergy(amount, false);
                                energy.extractEnergy(received, false);
                            }
                        });
                    }
                }
            }
        }
    }

    // This function will cache all outputs for this cable network. It will do this
    // by traversing all cables connected to this cable and then check for all energy
    // receivers around those cables.
    private void checkOutputs() {
        if (outputs == null) {
            outputs = new HashSet<>();
            traverse(worldPosition, cable -> {
                // Check for all energy receivers around this position (ignore cables)
                for (Direction direction : Direction.values()) {
                    BlockPos p = cable.getBlockPos().relative(direction);
                    BlockEntity te = level.getBlockEntity(p);
                    if (te != null && !(te instanceof CableBlockEntity)) {
                        te.getCapability(ForgeCapabilities.ENERGY).ifPresent(handler -> {
                            if (handler.canReceive()) {
                                outputs.add(p);
                            }
                        });
                    }
                }
            });
        }
    }

    public void markDirty() {
        traverse(worldPosition, cable -> cable.outputs = null);
    }

    // This is a generic function that will traverse all cables connected to this cable
    // and call the given consumer for each cable.
    private void traverse(BlockPos pos, Consumer<CableBlockEntity> consumer) {
        Set<BlockPos> traversed = new HashSet<>();
        traversed.add(pos);
        consumer.accept(this);
        traverse(pos, traversed, consumer);
    }

    private void traverse(BlockPos pos, Set<BlockPos> traversed, Consumer<CableBlockEntity> consumer) {
        for (Direction direction : Direction.values()) {
            BlockPos p = pos.relative(direction);
            if (!traversed.contains(p)) {
                traversed.add(p);
                if (level.getBlockEntity(p) instanceof CableBlockEntity cable) {
                    consumer.accept(cable);
                    cable.traverse(p, traversed, consumer);
                }
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put(ENERGY_TAG, energy.serializeNBT());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains(ENERGY_TAG)) {
            energy.deserializeNBT(tag.get(ENERGY_TAG));
        }
    }

    @Nonnull
    private EnergyStorage createEnergyStorage() {
        return new EnergyStorage(CAPACITY, MAXTRANSFER, MAXTRANSFER);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ENERGY) {
            return energyHandler.cast();
        } else {
            return super.getCapability(cap, side);
        }
    }
}
