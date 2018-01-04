package qmaks.cheatingessentials.mod.modulesystem.classes;

import org.lwjgl.input.Keyboard;

import net.minecraft.network.play.client.C03PacketPlayer;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class Regen extends Mod {

	public Regen()
	{
		super(ModuleCategories.PLAYER);
	}

	@Override
	public String getName(){
		return "Regen";
	}

	@Override
	public void onTicks(){
		if (!Wrapper.INSTANCE.player().onGround)
		{
			return;
		}
		boolean canHeal = (Wrapper.INSTANCE.player().onGround) || (Wrapper.INSTANCE.player().isInWater()) || (Wrapper.INSTANCE.player().isOnLadder());
		boolean shouldHeal = (Wrapper.INSTANCE.player().getHealth() <= 18.5F) && (Wrapper.INSTANCE.player().getFoodStats().getFoodLevel() > 8);

		if (canHeal && shouldHeal)
		{
			for (int i = 0; i < 1000; i++)
			{
				Wrapper.INSTANCE.player().sendQueue.addToSendQueue(new C03PacketPlayer(false));
			}
		}
	}
}
