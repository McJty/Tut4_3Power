package com.mcjty.tutpower.client;

import com.mcjty.tutpower.TutorialPower;
import com.mcjty.tutpower.blocks.GeneratorBlockEntity;
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
        graphics.blit(GUI, leftPos, topPos, 0, 0, this.imageWidth, this.imageHeight);
        int power = menu.getPower();
        int energyLeft = 96;
        int energyWidth = 72;
        int energyTop = 8;
        int energyHeight = 8;
        int p = (int) ((power / (float) GeneratorBlockEntity.CAPACITY) * energyWidth);
        graphics.fillGradient(leftPos + energyLeft, topPos + energyTop, leftPos + energyLeft + p, topPos + energyTop + energyHeight, 0xffff0000, 0xff000000);
        graphics.fill(leftPos + energyLeft + p, topPos + energyTop, leftPos + energyLeft + energyWidth, topPos + energyTop + energyHeight, 0xff330000);
    }

    @Override
    public void render(GuiGraphics graphics, int mousex, int mousey, float partialTick) {
        super.render(graphics, mousex, mousey, partialTick);
        int energyLeft = 96;
        int energyWidth = 72;
        int energyTop = 8;
        int energyHeight = 8;
        // Render tooltip with power if in the energy box
        if (mousex >= leftPos + energyLeft && mousex < leftPos + energyLeft + energyWidth && mousey >= topPos + energyTop && mousey < topPos + energyTop + energyHeight) {
            int power = menu.getPower();
            graphics.renderTooltip(this.font, Component.literal(power + " RF"), mousex, mousey);
        }
    }
}
