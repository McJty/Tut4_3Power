package com.mcjty.tutpower;

import com.mcjty.tutpower.blocks.*;
import com.mcjty.tutpower.cables.blocks.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.mcjty.tutpower.TutorialPower.MODID;

public class Registration {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, MODID);
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(BuiltInRegistries.MENU, MODID);
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredBlock<GeneratorBlock> GENERATOR_BLOCK = BLOCKS.register("generator_block", GeneratorBlock::new);
    public static final DeferredItem<Item> GENERATOR_BLOCK_ITEM = ITEMS.register("generator_block", () -> new BlockItem(GENERATOR_BLOCK.get(), new Item.Properties()));
    public static final Supplier<BlockEntityType<GeneratorBlockEntity>> GENERATOR_BLOCK_ENTITY = BLOCK_ENTITIES.register("generator_block",
            () -> BlockEntityType.Builder.of(GeneratorBlockEntity::new, GENERATOR_BLOCK.get()).build(null));
    public static final Supplier<MenuType<GeneratorContainer>> GENERATOR_CONTAINER = MENU_TYPES.register("generator_block",
            () -> IMenuTypeExtension.create((windowId, inv, data) -> new GeneratorContainer(windowId, inv.player, data.readBlockPos())));

    public static final DeferredBlock<ChargerBlock> CHARGER_BLOCK = BLOCKS.register("charger_block", ChargerBlock::new);
    public static final DeferredItem<Item> CHARGER_BLOCK_ITEM = ITEMS.register("charger_block", () -> new BlockItem(CHARGER_BLOCK.get(), new Item.Properties()));
    public static final Supplier<BlockEntityType<ChargerBlockEntity>> CHARGER_BLOCK_ENTITY = BLOCK_ENTITIES.register("charger_block",
            () -> BlockEntityType.Builder.of(ChargerBlockEntity::new, CHARGER_BLOCK.get()).build(null));

    public static final DeferredBlock<CableBlock> CABLE_BLOCK = BLOCKS.register("cable", CableBlock::new);
    public static final DeferredItem<Item> CABLE_BLOCK_ITEM = ITEMS.register("cable", () -> new BlockItem(CABLE_BLOCK.get(), new Item.Properties()));
    public static final Supplier<BlockEntityType<CableBlockEntity>> CABLE_BLOCK_ENTITY = BLOCK_ENTITIES.register("cable",
            () -> BlockEntityType.Builder.of(CableBlockEntity::new, CABLE_BLOCK.get()).build(null));

    public static final DeferredBlock<FacadeBlock> FACADE_BLOCK = BLOCKS.register("facade", FacadeBlock::new);
    public static final DeferredItem<Item> FACADE_BLOCK_ITEM = ITEMS.register("facade", () -> new FacadeBlockItem(FACADE_BLOCK.get(), new Item.Properties()));
    public static final Supplier<BlockEntityType<FacadeBlockEntity>> FACADE_BLOCK_ENTITY = BLOCK_ENTITIES.register("facade",
            () -> BlockEntityType.Builder.of(FacadeBlockEntity::new, FACADE_BLOCK.get()).build(null));

    public static Supplier<CreativeModeTab> TAB = TABS.register("tutpower", () -> CreativeModeTab.builder()
            .title(Component.translatable("tab.tutpower"))
            .icon(() -> new ItemStack(GENERATOR_BLOCK.get()))
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .displayItems((featureFlags, output) -> {
                output.accept(GENERATOR_BLOCK.get());
                output.accept(CHARGER_BLOCK.get());
                output.accept(CABLE_BLOCK.get());
                output.accept(FACADE_BLOCK.get());
            })
            .build());

    public static void init(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
        MENU_TYPES.register(modEventBus);
        TABS.register(modEventBus);
    }
}
