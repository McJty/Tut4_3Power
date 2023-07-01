package com.mcjty.tutpower.client;

import com.mcjty.tutpower.TutorialPower;
import com.mcjty.tutpower.blocks.GeneratorContainer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class GeneratorScreen extends AbstractContainerScreen<GeneratorContainer> {

    private final ResourceLocation GUI = new ResourceLocation(TutorialPower.MODID, "textures/gui/generator.png");

    public GeneratorScreen(GeneratorContainer container, Inventory inventory, Component title) {
        super(container, inventory, title);
        this.inventoryLabelY = this.imageHeight - 110;
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTicks, int mouseX, int mouseY) {
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        graphics.blit(GUI, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
    }
}
