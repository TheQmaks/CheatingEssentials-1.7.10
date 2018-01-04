package qmaks.cheatingessentials.mod.modulesystem.classes;

import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class SeeHealth extends Mod {

	public static boolean isActive = false;

	public SeeHealth()
	{
		super(ModuleCategories.COMBAT);
	}

	@Override
	public String getName(){
		return "SeeHealth";
	}

	@Override
	public void onEnableMod(){
		isActive = true;
	}
	
	@Override
	public void onDisableMod(){
		isActive = false;
	}
}
