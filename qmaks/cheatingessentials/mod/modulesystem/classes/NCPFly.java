package qmaks.cheatingessentials.mod.modulesystem.classes;

import net.minecraft.network.play.client.C03PacketPlayer;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class NCPFly extends Mod {

	public NCPFly()
	{
		super(ModuleCategories.NOCHEATPLUS);
	}

	@Override
	public String getName(){
		return "Fly";
	}

	@Override
	public void onEnableMod() {
		for (int i = 0; i < 4; i++)
		{
			Wrapper.INSTANCE.player().sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(Wrapper.INSTANCE.player().posX, Wrapper.INSTANCE.player().boundingBox.minY + 1.01D, Wrapper.INSTANCE.player().posY + 1.01D, Wrapper.INSTANCE.player().posZ, false));
			Wrapper.INSTANCE.player().sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(Wrapper.INSTANCE.player().posX, Wrapper.INSTANCE.player().boundingBox.minY, Wrapper.INSTANCE.player().posY, Wrapper.INSTANCE.player().posZ, false));
		}
		Wrapper.INSTANCE.player().setPosition(Wrapper.INSTANCE.player().posX, Wrapper.INSTANCE.player().posY + 0.8D, Wrapper.INSTANCE.player().posZ);
	}

	@Override
	public void onTicks(){
		Wrapper.INSTANCE.player().motionY = -0.04D;
		if (Wrapper.INSTANCE.mcSettings().keyBindJump.getIsKeyPressed()) {
			Wrapper.INSTANCE.player().motionY = 0.3D;
		}
		if (Wrapper.INSTANCE.mcSettings().keyBindSneak.getIsKeyPressed()) {
			Wrapper.INSTANCE.player().motionY = -0.3D;
		}
	}
}