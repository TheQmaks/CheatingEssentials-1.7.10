package qmaks.cheatingessentials.mod.modulesystem.classes;

import net.minecraft.item.ItemBow;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class FastBow extends Mod {

	public FastBow()
	{
		super(ModuleCategories.COMBAT);
	}

	@Override
	public String getName(){
		return "FastBow";
	}

	@Override
	public void onTicks(){
		if(!Wrapper.INSTANCE.mc().isSingleplayer()) {
			(new Thread() {
				public void run() {
					if(Wrapper.INSTANCE.player().isUsingItem() && Wrapper.INSTANCE.player().inventory.getCurrentItem().getItem() instanceof ItemBow && Wrapper.INSTANCE.player().onGround) {
						try {
							Wrapper.INSTANCE.player().sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(-1, -1, -1, 255, Wrapper.INSTANCE.player().inventory.getCurrentItem(), -1.0F, -1.0F, -1.0F));

							for(int i = 0; i < 25; ++i) {
								Wrapper.INSTANCE.player().sendQueue.addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(Wrapper.INSTANCE.player().rotationYaw, Wrapper.INSTANCE.player().rotationPitch, Wrapper.INSTANCE.player().onGround));
								Thread.sleep(1L);
							}

							Wrapper.INSTANCE.player().sendQueue.addToSendQueue(new C07PacketPlayerDigging(5, 0, 0, 0, 255));
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}

				}
			}).start();
		} else if(Wrapper.INSTANCE.player().isUsingItem() && Wrapper.INSTANCE.player().inventory.getCurrentItem().getItem() instanceof ItemBow && Wrapper.INSTANCE.player().onGround) {
			try {
				Wrapper.INSTANCE.player().setSprinting(true);
				Wrapper.INSTANCE.player().sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(-1, -1, -1, 255, Wrapper.INSTANCE.player().inventory.getCurrentItem(), 1.0F, 1.0F, 1.0F));

				for(int i = 0; i < 20; ++i) {
					Thread.sleep(1L);
					Wrapper.INSTANCE.player().sendQueue.addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(Wrapper.INSTANCE.player().rotationYaw, Wrapper.INSTANCE.player().rotationPitch, Wrapper.INSTANCE.player().onGround));
				}

				Wrapper.INSTANCE.player().sendQueue.addToSendQueue(new C07PacketPlayerDigging(5, 0, 0, 0, 255));
				Wrapper.INSTANCE.player().setSprinting(false);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
