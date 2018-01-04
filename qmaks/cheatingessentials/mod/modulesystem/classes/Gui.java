package qmaks.cheatingessentials.mod.modulesystem.classes;

import org.lwjgl.input.Keyboard;

import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.gui.reeszrbteam.YouAlwaysWinClickGui;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class Gui extends Mod {

	public Gui()
	{
		super(ModuleCategories.NONE);
		this.setKeybinding(Keyboard.KEY_G);
	}

	private YouAlwaysWinClickGui click = new YouAlwaysWinClickGui();

	public String getName()
	{
		return "Gui";
	}

	@Override
	public void toggle()
	{
		Wrapper.INSTANCE.mc().displayGuiScreen(click);
	}
}
