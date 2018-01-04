package qmaks.cheatingessentials.mod.modulesystem.classes;

import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.commands.ACommandSpeedValue;
import qmaks.cheatingessentials.mod.util.TimerUtils;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;

public class Speed extends Mod {

	public Speed()
	{
		super(ModuleCategories.PLAYER);
	}

	@Override
	public String getName()
	{
		return "Speed";
	}

	@Override
	public void onTicks() {
		TimerUtils.getTimer().timerSpeed = ACommandSpeedValue.SPEED_VALUE;
	}

	@Override
	public void onDisableMod() {
		TimerUtils.getTimer().timerSpeed = 1.0F;
	}
}
