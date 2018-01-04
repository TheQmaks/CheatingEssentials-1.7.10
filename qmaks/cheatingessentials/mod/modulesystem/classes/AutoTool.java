package qmaks.cheatingessentials.mod.modulesystem.classes;

import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;

public class AutoTool extends Mod {
	
	public static boolean isActive = false;
	
	public AutoTool()
	{
		super(ModuleCategories.PLAYER);
	}
	
	@Override
	public String getName(){
		return "AutoTool";
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
