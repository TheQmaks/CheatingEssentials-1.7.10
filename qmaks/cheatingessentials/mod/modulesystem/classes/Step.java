package qmaks.cheatingessentials.mod.modulesystem.classes;

import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.commands.ACommandStepHeight;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class Step extends Mod {

	public Step()
	{
		super(ModuleCategories.PLAYER);
	}

	@Override
	public String getName(){
		return "Step";
	}

	@Override
	public void onTicks() {
		Wrapper.INSTANCE.player().stepHeight = ACommandStepHeight.STEP_HEIGHT_VALUE;
	}

	@Override
	public void onDisableMod() {
		Wrapper.INSTANCE.player().stepHeight = 0.5F;
	}
}
