package qmaks.cheatingessentials.mod.gui.reeszrbteam.element;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;

import scala.Console;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.gui.reeszrbteam.YouAlwaysWinClickGui;
import qmaks.cheatingessentials.mod.util.GLUtils;

public class YAWWindow
{
	private String title;
	public int xPos;
	public int yPos;
	
	private boolean isOpen;
	private boolean isExtended;
	private boolean isPinned;
	
	public int dragX;
	public int dragY;
	public int lastDragX;
	public int lastDragY;
	protected boolean dragging;
	
	public ArrayList<YAWButton> buttons = new ArrayList<YAWButton>();
	
	public YAWWindow(String title, int x, int y)
	{
		this.title = title;
		this.xPos = x;
		this.yPos = y;
		YouAlwaysWinClickGui.windows.add(this);
		YouAlwaysWinClickGui.unFocusedWindows.add(this);
	}
	
	public void windowDragged(int x, int y) {
		dragX = x - lastDragX;
		dragY = y - lastDragY;
	}
	
	private int buttonCount = 0, sliderCount = 0;
	
	public void addButton(Mod mod) {
		buttons.add(new YAWButton(this, mod, xPos + 2, yPos + (11 * buttons.size()) + 16));
	}
	
	public void draw(int x, int y)
	{
		if(isOpen)
		{
			if(dragging)
			{
				windowDragged(x, y);
			}
			
			GLUtils.drawGradientBorderedRect(xPos + dragX, yPos + dragY, xPos + 90 + dragX, yPos + 12 + dragY, 0.5F, 0xFF000000, 0xCF999999, 0xCF777777);
			Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(title, xPos + 2 + dragX, 2 + yPos + dragY, 0x55FFFF);
			
			GLUtils.drawGradientBorderedRect(xPos + 70 + dragX, yPos + 2 + dragY, xPos + 78 + dragX, yPos + 10 + dragY, 1F, 0xFF666666, isPinned ? 0xFF777777 : 0xFF888888, isPinned ? 0xFF555555 : 0xFF666666);
			GLUtils.drawGradientBorderedRect(xPos + 80 + dragX, yPos + 2 + dragY, xPos + 88 + dragX, yPos + 10 + dragY, 1F, 0xFF666666, isExtended ? 0xFF777777 : 0xFF888888, isExtended ? 0xFF555555 : 0xFF666666);
			
			if(isExtended)
			{
				GLUtils.drawGradientBorderedRect(xPos + dragX, yPos + 14 + dragY, xPos + 90 + dragX, yPos + (11 * buttons.size() + 19)/* + (18 * sliders.size())*/ + dragY, 0.5F, 0xFF000000, 0xCF999999, 0xCF777777);
				
				for(YAWButton button: buttons)
				{
					button.draw();
					if(x >= button.getX() + dragX && y >= button.getY() + 1 + dragY && x <= button.getX() + 23 + dragX && y <= button.getY() + 10 + dragY)
					{
						button.isOverButton = true;
					}
					else
					{
						button.isOverButton = false;
					}
				}
			}
		}
	}
	
	public void mouseClicked(int x, int y, int button)
	{
		for(YAWButton xButton: buttons)
		{
			xButton.mouseClicked(x, y, button);
		}
		
		if(x >= xPos + 80 + dragX && y >= yPos + 2 + dragY && x <= xPos + 88 + dragX && y <= yPos + 10 + dragY)
		{
			isExtended = !isExtended;
		}
		if(x >= xPos + 70 + dragX && y >= yPos + 2 + dragY && x <= xPos + 78 + dragX && y <= yPos + 10 + dragY)
		{
			isPinned = !isPinned;
		}
		if(x >= xPos + dragX && y >= yPos + dragY && x <= xPos + 69 + dragX && y <= yPos + 12 + dragY)
		{
			YouAlwaysWinClickGui.sendPanelToFront(this);
			
			dragging = !dragging;
			
			lastDragX = x - dragX;
			lastDragY = y - dragY;
		}
	}
	
	public void mouseMovedOrUp(int x, int y, int b)
	{
		if(b == 0) {
			dragging = false;
		}
	}
	
	public final String getTitle()
	{
		return this.title;
	}
	
	public final int getX()
	{
		return this.xPos;
	}
	
	public final int getY()
	{
		return this.yPos;
	}
	
	public boolean isExtended()
	{
		return isExtended;
	}
	
	public boolean isOpen()
	{
		return isOpen;
	}
	
	public boolean isPinned()
	{
		return dragging ? false : isPinned;
	}
	
	public void setOpen(boolean flag)
	{
		this.isOpen = flag;
	}
	
	public void setExtended(boolean flag)
	{
		this.isExtended = flag;
	}
	
	public void setPinned(boolean flag)
	{
		this.isPinned = flag;
	}
	
	public void setX(int xPos)
	{
		this.dragX = xPos;
	}
	
	public void setY(int yPos)
	{
		this.dragY = yPos;
	}
}
