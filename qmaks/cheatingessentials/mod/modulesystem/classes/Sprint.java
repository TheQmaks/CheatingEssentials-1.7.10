package qmaks.cheatingessentials.mod.modulesystem.classes;

import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class Sprint extends Mod {

	public Sprint()
	{
		super(ModuleCategories.PLAYER);
	}

	@Override
	public String getName(){
		return "Sprint";
	}

	@Override
	public String getDescription(){
		return "Sprints automatically when you should be walking.";
	}

	@Override
	public void onTicks(){
		if(Wrapper.INSTANCE.player().moveForward > 0){
			Wrapper.INSTANCE.player().setSprinting(true);
		}
	}
}
