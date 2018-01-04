package qmaks.cheatingessentials.mod.modulesystem.classes;

import org.lwjgl.input.Keyboard;

import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.gui.reeszrbteam.YouAlwaysWinClickGui;
import qmaks.cheatingessentials.mod.gui.xraysettings.xray_Gui;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class GuiXRaySettings extends Mod {

	public GuiXRaySettings()
	{
		super(ModuleCategories.NONE);
		this.setKeybinding(Keyboard.KEY_F7);
	}

	private xray_Gui gui = new xray_Gui();

	public String getName()
	{
		return "GuiXRaySettings";
	}

	@Override
	public void toggle()
	{
		Wrapper.INSTANCE.mc().displayGuiScreen(gui);
	}
}
