package qmaks.cheatingessentials.mod.gui.reeszrbteam.window;

import net.minecraft.client.Minecraft;
import qmaks.cheatingessentials.api.module.APICEMod;
import qmaks.cheatingessentials.mod.gui.reeszrbteam.element.YAWWindow;
import qmaks.cheatingessentials.mod.util.GLUtils;

public class WindowActives extends YAWWindow {

	public WindowActives() {
		super("Enabled", 2, 148);
	}
	
	public void draw(int x, int y){
		if(isOpen())
		{
			if(dragging)
			{
				windowDragged(x, y);
			}
			GLUtils.drawGradientBorderedRect(getX() + dragX, getY() + dragY, getX() + 90 + dragX, getY() + 12 + dragY, 0.5F, 0xFF000000, 0xFF999999, 0xFF777777);
			Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(getTitle(), getX() + 2 + dragX, getY() + dragY + 2, 0x55FFFF);
			
			GLUtils.drawGradientBorderedRect(getX() + 70 + dragX, getY() + 2 + dragY, getX() + 78 + dragX, getY() + 10 + dragY, 1F, 0xFF666666, isPinned() ? 0xFF777777 : 0xFF888888, isPinned() ? 0xFF555555 : 0xFF666666);
			GLUtils.drawGradientBorderedRect(getX() + 80 + dragX, getY() + 2 + dragY, getX() + 88 + dragX, getY() + 10 + dragY, 1F, 0xFF666666, isExtended() ? 0xFF777777 : 0xFF888888, isExtended() ? 0xFF555555 : 0xFF666666);
			
			if(isExtended())
			{				
				for(int i = 0; i < APICEMod.INSTANCE.activeMods.size(); i++){
					GLUtils.drawGradientBorderedRect(getX() + dragX, getY() + 14 + dragY, getX() + 90 + dragX, getY() + 14 + dragY + APICEMod.INSTANCE.activeMods.size() + ((12*(i+1)) - (i*4)), 0.5F, 0xFF000000, 0xFF999999, 0xFF777777);
				}
					for(int i = 0; i < APICEMod.INSTANCE.activeMods.size(); i++){
					Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(" " + APICEMod.INSTANCE.activeMods.get(i).getName(), getX() + dragX,
							(i <= 5 ? getY() : getY() - 8) + dragY + 1 + APICEMod.INSTANCE.activeMods.size() + ((12*(i+1)) - (i*3)), 0x55FFFF);
				}
			}
		}
	}
}

