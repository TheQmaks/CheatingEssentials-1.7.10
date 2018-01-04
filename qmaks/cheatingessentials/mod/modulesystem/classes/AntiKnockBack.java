package qmaks.cheatingessentials.mod.modulesystem.classes;

import org.lwjgl.input.Keyboard;

import net.minecraft.network.play.client.C03PacketPlayer;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class AntiKnockBack extends Mod {

	public AntiKnockBack()
	{
		super(ModuleCategories.PLAYER);
	}

	@Override
	public String getName(){
		return "AntiKnockBack";
	}

	@Override
	public void onWorldRender() {
		if(Wrapper.INSTANCE.player().hurtResistantTime > 0 && Wrapper.INSTANCE.player()	.hurtTime > 0) {
			Wrapper.INSTANCE.player().hurtResistantTime = 0;
			Wrapper.INSTANCE.player().hurtTime = 0;
			Wrapper.INSTANCE.player().motionX = 0.0D;
			Wrapper.INSTANCE.player().motionY /= 10.0D;
			Wrapper.INSTANCE.player().motionZ = 0.0D;
		}
	}
}