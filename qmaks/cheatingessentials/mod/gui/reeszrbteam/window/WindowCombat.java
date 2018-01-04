package qmaks.cheatingessentials.mod.gui.reeszrbteam.window;

import qmaks.cheatingessentials.api.module.APICEMod;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.gui.reeszrbteam.element.YAWWindow;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;

public class WindowCombat extends YAWWindow
{
	public WindowCombat()
	{
		super("Combat", 186, 14);
	}
	
	public YAWWindow init()
	{
		for(Mod mod: APICEMod.INSTANCE.mods)
		{
			if(mod.getCategory() == ModuleCategories.COMBAT)
			{
				addButton(mod);
			}
		}
		return this;
	}
}
