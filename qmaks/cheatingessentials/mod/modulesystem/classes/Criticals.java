package qmaks.cheatingessentials.mod.modulesystem.classes;

import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;

public class Criticals extends Mod {
	
	public static boolean isActive = false;
	
	public Criticals()
	{
		super(ModuleCategories.COMBAT);
	}
	
	@Override
	public String getName(){
		return "Criticals";
	}
	
	@Override
	public void onEnableMod() {
		isActive = true;
	}
	
	@Override
	public void onDisableMod() {
		isActive = false;
	}
}
