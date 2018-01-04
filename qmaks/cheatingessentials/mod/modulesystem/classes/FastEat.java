package qmaks.cheatingessentials.mod.modulesystem.classes;

import org.lwjgl.input.Mouse;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAppleGold;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class FastEat extends Mod {

	private int mode = 0;

	public FastEat()
	{
		super(ModuleCategories.PLAYER);
	}

	@Override
	public String getName(){
		return "FastEat";
	}

	@Override
	public void onTicks(){
		if ((isActive()) && 
				(Mouse.getEventButton() == 1) && (Mouse.isButtonDown(1))) {
			if (this.mode == 1) {
				int[] ignoredBlockIds = { 23, 25, 26, 54, 58, 61, 62, 64, 69, 71, 77, 84, 92, 96, 107, 116, 117, 118, 120, 130, 137, 143, 145, 146, 149, 150, 154, 158 };

				for (int id : ignoredBlockIds)
					if (Block.getIdFromBlock(Wrapper.INSTANCE.world().getBlock(Wrapper.INSTANCE.mc().objectMouseOver.blockX, Wrapper.INSTANCE.mc().objectMouseOver.blockY, Wrapper.INSTANCE.mc().objectMouseOver.blockZ)) == id)
						return;
			}
			Item item;
			if (Wrapper.INSTANCE.player().inventory.getCurrentItem() != null)
				item = Wrapper.INSTANCE.player().inventory.getCurrentItem().getItem();
			else
				return;
			if ((Wrapper.INSTANCE.player().onGround) && 
					(((item instanceof ItemFood)) || ((item instanceof ItemPotion)) || ((item instanceof ItemAppleGold))) && (
							(Wrapper.INSTANCE.player().getFoodStats().needFood()) || ((item instanceof ItemPotion)) || ((item instanceof ItemAppleGold)))) {
				Wrapper.INSTANCE.player().sendQueue.addToSendQueue(new C09PacketHeldItemChange(Wrapper.INSTANCE.player().inventory.currentItem));
				for (int i = 0; i < 1000; i++) {
					Wrapper.INSTANCE.player().sendQueue.addToSendQueue(new C03PacketPlayer(false));
				}
				Wrapper.INSTANCE.player().sendQueue.addToSendQueue(new C07PacketPlayerDigging(5, 0, 0, 0, 255));
			}
		}
	}
}
