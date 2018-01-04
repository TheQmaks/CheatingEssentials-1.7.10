package qmaks.cheatingessentials.mod.modulesystem.classes;

import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NameProtect extends Mod {

	public static boolean isActive = false;

	public NameProtect()
	{
		super(ModuleCategories.RENDER);
	}

	@Override
	public String getName(){
		return "NameProtect";
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
