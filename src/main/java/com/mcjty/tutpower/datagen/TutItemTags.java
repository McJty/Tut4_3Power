package com.mcjty.tutpower.datagen;

import com.mcjty.tutpower.TutorialPower;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class TutItemTags extends ItemTagsProvider {

    public TutItemTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, BlockTagsProvider blockTags, ExistingFileHelper helper) {
        super(packOutput, lookupProvider, blockTags.contentsGetter(), TutorialPower.MODID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
    }
}
