package qmaks.cheatingessentials.mod.modulesystem.classes;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.wrapper.Events;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class Nuker extends Mod {

	public static boolean isActive = false;
	private static int radius = (int)5.0F;

	public Nuker()
	{
		super(ModuleCategories.PLAYER);
	}

	@Override
	public String getName(){
		return "Nuker";
	}

	@Override
	public void onEnableMod(){
		isActive = true;
		Wrapper.INSTANCE.addChatMessage("&9[&bCE Nuker&9] &fRight click to choose a block.");
	}

	@Override
	public void onDisableMod(){
		isActive = false;
		Events.selectedBlock = null;
	}

	@Override
	public void onTicks(){
		if (Events.selectedBlock != null)
		{
			if (Wrapper.INSTANCE.mc().playerController.isInCreativeMode() == true)
			{
				for (int i = radius; i >= -radius; i--)
					for (int k = radius; k >= -radius; k--)
						for (int j = -radius; j <= radius; j++) {
							int x = (int)(Wrapper.INSTANCE.player().posX + i);
							int y = (int)(Wrapper.INSTANCE.player().posY + j);
							int z = (int)(Wrapper.INSTANCE.player().posZ + k);
							Block blockID = Wrapper.INSTANCE.world().getBlock(x, y, z);
							if ((blockID.getMaterial() == Material.air) || (Events.selectedBlock != blockID))
								continue;
							Wrapper.INSTANCE.player().sendQueue.addToSendQueue(new C07PacketPlayerDigging(0, x, y, z, 0));
						}
			} else if (Wrapper.INSTANCE.mc().playerController.isNotCreative() == true) {
				for (int i = radius; i >= -radius; i--)
					for (int k = radius; k >= -radius; k--)
						for (int j = -radius; j <= radius; j++) {
							int x = (int)(Wrapper.INSTANCE.player().posX + i);
							int y = (int)(Wrapper.INSTANCE.player().posY + j);
							int z = (int)(Wrapper.INSTANCE.player().posZ + k);
							Block block = Wrapper.INSTANCE.world().getBlock(x, y, z);
							if ((block.getMaterial() == Material.air) || (Events.selectedBlock != block))
								continue;
							Wrapper.INSTANCE.player().sendQueue.addToSendQueue(new C07PacketPlayerDigging(0, x, y, z, 0));
							Wrapper.INSTANCE.player().sendQueue.addToSendQueue(new C07PacketPlayerDigging(2, x, y, z, 0));
						}
			}
		}
	}
}
