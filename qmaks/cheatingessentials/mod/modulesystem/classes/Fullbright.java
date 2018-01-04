package qmaks.cheatingessentials.mod.modulesystem.classes;

import org.lwjgl.input.Keyboard;

import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class Fullbright extends Mod {

	public Fullbright()
	{
		super(ModuleCategories.RENDER);
		this.setKeybinding(Keyboard.KEY_F);
	}

	@Override
	public String getName(){
		return "Fullbright";
	}

	@Override
	public void onEnableMod(){
		float[] bright = Wrapper.INSTANCE.world().provider.lightBrightnessTable;

		for(int i = 0; i < bright.length; i++){
			bright[i] = 1.0F;
		}
	}

	@Override
	public void onDisableMod(){
		Wrapper.INSTANCE.world().provider.registerWorld(Wrapper.INSTANCE.world());
	}
}
