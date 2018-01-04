package qmaks.cheatingessentials.mod.gui.reeszrbteam.window;

import qmaks.cheatingessentials.api.module.APICEMod;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.gui.reeszrbteam.element.YAWWindow;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;

public class WindowMinigames extends YAWWindow {

	public WindowMinigames() {
		super("Minigames", 370, 14);
	}
	
	public YAWWindow init()
	{
		for(Mod mod: APICEMod.INSTANCE.mods)
		{
			if(mod.getCategory() == ModuleCategories.MINIGAMES)
			{
				addButton(mod);
			}
		}
		return this;
	}

}
