package qmaks.cheatingessentials.mod.modulesystem.classes;

import org.lwjgl.input.Keyboard;

import net.minecraft.network.play.client.C03PacketPlayer;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class NoFall extends Mod {

	public NoFall()
	{
		super(ModuleCategories.PLAYER);
		this.setKeybinding(Keyboard.KEY_V);
	}

	@Override
	public String getName(){
		return "NoFall";
	}

	@Override
	public void onTicks() {
		if(Wrapper.INSTANCE.player().fallDistance > 2.0F) 
			Wrapper.INSTANCE.player().sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(Wrapper.INSTANCE.player().motionX, -999.0D, -999.0D, Wrapper.INSTANCE.player().motionZ, !Wrapper.INSTANCE.player().onGround));
	}
}
