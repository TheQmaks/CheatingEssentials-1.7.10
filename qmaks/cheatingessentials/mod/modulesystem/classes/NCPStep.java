package qmaks.cheatingessentials.mod.modulesystem.classes;

import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class NCPStep extends Mod {

	public NCPStep()
	{
		super(ModuleCategories.NOCHEATPLUS);
	}

	public String getName(){
		return "Step";
	}

	@Override
	public String getDescription(){
		return "Step for NoCheatPlus";
	}

	@Override
	public void onTicks()
	{
		if((Wrapper.INSTANCE.player().onGround) && (Wrapper.INSTANCE.player().isCollidedHorizontally) && (!Wrapper.INSTANCE.player().isInWater())) {
			Wrapper.INSTANCE.player().boundingBox.offset(0.0D, 1.0628D, 0.0D);
			Wrapper.INSTANCE.player().motionY = -420.0D;
			Wrapper.INSTANCE.player().isCollidedHorizontally = false;
		}
	}
}