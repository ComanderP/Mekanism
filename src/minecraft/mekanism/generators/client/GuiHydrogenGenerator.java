package mekanism.generators.client;

import mekanism.generators.common.ContainerHydrogenGenerator;
import mekanism.generators.common.TileEntityHydrogenGenerator;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

import org.lwjgl.opengl.GL11;

import universalelectricity.core.electricity.ElectricInfo;
import universalelectricity.core.electricity.ElectricInfo.ElectricUnit;

public class GuiHydrogenGenerator extends GuiContainer
{
	public TileEntityHydrogenGenerator tileEntity;
	
	private int guiWidth;
	private int guiHeight;
	
	public GuiHydrogenGenerator(InventoryPlayer inventory, TileEntityHydrogenGenerator tentity)
    {
        super(new ContainerHydrogenGenerator(inventory, tentity));
        tileEntity = tentity;
    }

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        fontRenderer.drawString(tileEntity.fullName, 45, 6, 0x404040);
        fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 0x404040);
        fontRenderer.drawString(ElectricInfo.getDisplayShort(tileEntity.electricityStored, ElectricUnit.JOULES), 51, 26, 0x00CD00);
        fontRenderer.drawString("H: " + tileEntity.hydrogenStored, 51, 35, 0x00CD00);
        fontRenderer.drawString(tileEntity.getVoltage() + "v", 51, 44, 0x00CD00);
    }

	@Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        int texture = mc.renderEngine.getTexture("/resources/mekanism/gui/GuiHydrogenGenerator.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(texture);
        guiWidth = (width - xSize) / 2;
        guiHeight = (height - ySize) / 2;
        drawTexturedModalRect(guiWidth, guiHeight, 0, 0, xSize, ySize);
        int displayInt;
        
        displayInt = tileEntity.getScaledHydrogenLevel(52);
        drawTexturedModalRect(guiWidth + 7, guiHeight + 17 + 52 - displayInt, 176, 52 + 52 - displayInt, 4, displayInt);
        
        displayInt = tileEntity.getScaledEnergyLevel(52);
        drawTexturedModalRect(guiWidth + 165, guiHeight + 17 + 52 - displayInt, 176, 52 - displayInt, 4, displayInt);
    }
}