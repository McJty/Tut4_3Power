package com.mcjty.tutpower.datagen;

import com.mcjty.tutpower.Registration;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class TutRecipes extends RecipeProvider {

    public TutRecipes(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registration.GENERATOR_BLOCK.get())
                .pattern("dsd")
                .pattern("ixi")
                .pattern("iii")
                .define('d', ItemTags.DIRT)
                .define('i', Tags.Items.INGOTS_IRON)
                .define('x', Tags.Items.GEMS_DIAMOND)
                .define('s', Items.STICK)
                .group("tutorial")
                .unlockedBy("has_iron", InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(Tags.Items.INGOTS_IRON).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registration.CHARGER_BLOCK.get())
                .pattern("ixi")
                .pattern("iii")
                .define('i', Tags.Items.INGOTS_IRON)
                .define('x', Tags.Items.GEMS_DIAMOND)
                .group("tutorial")
                .unlockedBy("has_iron", InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(Tags.Items.INGOTS_IRON).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registration.CABLE_BLOCK.get())
                .pattern("iii")
                .pattern("rrr")
                .pattern("iii")
                .define('i', Tags.Items.INGOTS_COPPER)
                .define('r', Tags.Items.DUSTS_REDSTONE)
                .group("tutorial")
                .unlockedBy("has_iron", InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(Tags.Items.INGOTS_IRON).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registration.FACADE_BLOCK.get())
                .pattern("iwi")
                .pattern("wiw")
                .pattern("iwi")
                .define('i', Tags.Items.INGOTS_COPPER)
                .define('w', ItemTags.WOOL)
                .group("tutorial")
                .unlockedBy("has_iron", InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(Tags.Items.INGOTS_IRON).build()))
                .save(consumer);
    }
}
