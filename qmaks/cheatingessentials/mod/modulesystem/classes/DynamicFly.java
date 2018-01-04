package qmaks.cheatingessentials.mod.modulesystem.classes;

import net.minecraft.entity.EntityLivingBase;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

import org.lwjgl.input.Keyboard;

public class DynamicFly extends Mod {

	public DynamicFly()
	{
		super(ModuleCategories.PLAYER);
		this.setKeybinding(Keyboard.KEY_R);
	}

	@Override
	public String getName(){
		return "DynamicFly";
	}

	@Override 
	public void onTicks(){
		Wrapper.INSTANCE.player().jumpMovementFactor = 0.4F;
		Wrapper.INSTANCE.player().motionX = 0;
		Wrapper.INSTANCE.player().motionY = 0;
		Wrapper.INSTANCE.player().motionZ = 0;
		Wrapper.INSTANCE.player().jumpMovementFactor *= 3;

		if(Wrapper.INSTANCE.mc().gameSettings.keyBindJump.getIsKeyPressed()){
			Wrapper.INSTANCE.player().motionY += 1;
		}
		if(Wrapper.INSTANCE.mc().gameSettings.keyBindSneak.getIsKeyPressed()){
			Wrapper.INSTANCE.player().motionY -= 1;
		}

		Wrapper.INSTANCE.player().motionY -= 0.05;
	}
}
