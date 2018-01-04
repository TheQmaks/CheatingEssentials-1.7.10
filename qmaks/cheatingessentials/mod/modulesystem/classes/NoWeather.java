package qmaks.cheatingessentials.mod.modulesystem.classes;

import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class NoWeather extends Mod {

	public NoWeather()
	{
		super(ModuleCategories.RENDER);
	}

	public String getName(){
		return "NoWeather";
	}

	@Override
	public String getDescription(){
		return "Stops rain.";
	}

	@Override
	public void onTicks()
	{
		if(Wrapper.INSTANCE.world() != null) {
			Wrapper.INSTANCE.world().setRainStrength(0);
		}
	}

}
