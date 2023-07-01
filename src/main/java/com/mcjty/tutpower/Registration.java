package com.mcjty.tutpower;

import com.mcjty.tutpower.blocks.GeneratorBlock;
import com.mcjty.tutpower.blocks.GeneratorBlockEntity;
import com.mcjty.tutpower.blocks.GeneratorContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registration {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialPower.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TutorialPower.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TutorialPower.MODID);
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, TutorialPower.MODID);

    public static final RegistryObject<GeneratorBlock> GENERATOR_BLOCK = BLOCKS.register("generator_block", GeneratorBlock::new);
    public static final RegistryObject<Item> GENERATOR_BLOCK_ITEM = ITEMS.register("generator_block", () -> new BlockItem(GENERATOR_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockEntityType<GeneratorBlockEntity>> GENERATOR_BLOCK_ENTITY = BLOCK_ENTITIES.register("generator_block",
            () -> BlockEntityType.Builder.of(GeneratorBlockEntity::new, GENERATOR_BLOCK.get()).build(null));
    public static final RegistryObject<MenuType<GeneratorContainer>> GENERATOR_CONTAINER = MENU_TYPES.register("generator_block",
            () -> IForgeMenuType.create((windowId, inv, data) -> new GeneratorContainer(windowId, inv.player, data.readBlockPos())));

    public static void init(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
        MENU_TYPES.register(modEventBus);
    }
}
