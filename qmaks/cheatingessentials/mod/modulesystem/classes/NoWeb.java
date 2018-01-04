package qmaks.cheatingessentials.mod.modulesystem.classes;

import net.minecraft.entity.Entity;
import qmaks.cheatingessentials.api.module.Mod;
import cpw.mods.fml.relauncher.ReflectionHelper;
import qmaks.cheatingessentials.mod.util.Mappings;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class NoWeb extends Mod {

	public NoWeb()
	{
		super(ModuleCategories.PLAYER);
	}

	@Override
	public String getName(){
		return "NoWeb";
	}

	@Override
	public void onTicks(){
		ReflectionHelper.setPrivateValue(Entity.class, Wrapper.INSTANCE.player(), false, Mappings.isInWeb);
	}
}
