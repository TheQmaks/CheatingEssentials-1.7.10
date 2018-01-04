package qmaks.cheatingessentials.mod.gui.reeszrbteam.window;

import qmaks.cheatingessentials.api.module.APICEMod;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.gui.reeszrbteam.element.YAWWindow;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;

public class WindowRender extends YAWWindow
{
	public WindowRender()
	{
		super("Render", 278, 14);
	}

	public YAWWindow init()
	{
		for(Mod mod: APICEMod.INSTANCE.mods)
		{
			if(mod.getCategory() == ModuleCategories.RENDER)
			{
				addButton(mod);
			}
		}
		return this;
	}
}
