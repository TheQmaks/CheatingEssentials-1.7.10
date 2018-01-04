package qmaks.cheatingessentials.mod.modulesystem.classes;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class WaterFall extends Mod {

	private int delay;

	public WaterFall()
	{
		super(ModuleCategories.NOCHEATPLUS);
	}

	@Override
	public String getName(){
		return "WaterFall";
	}

	@Override
	public void onTicks(){
		if (Wrapper.INSTANCE.player().fallDistance >= 5.0F) {
			switchToItem(I18n.format("item.bucketWater.name", new Object[0]));
			Block blocks = Wrapper.INSTANCE.world().getBlock((int)Wrapper.INSTANCE.player().posX, (int)Wrapper.INSTANCE.player().posY - 3, (int)Wrapper.INSTANCE.player().posZ);
			if ((blocks.getMaterial() != Material.air) && 
					(hasItem(I18n.format("item.bucketWater.name", new Object[0])))) {
				useItem();
				this.delay += 1;
				if (this.delay >= 20)
				{
					switchToItem(I18n.format("item.bucket.name", new Object[0]));
					useItem();
					this.delay = 0;
				}
			}
		}
	}

	private void useItem() {
		ItemStack item = Wrapper.INSTANCE.mc().thePlayer.inventory.getCurrentItem();
		Wrapper.INSTANCE.player().sendQueue.addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(90.0F, 90.0F, false));
		Wrapper.INSTANCE.mc().playerController.sendUseItem(Wrapper.INSTANCE.player(),Wrapper.INSTANCE.mc().theWorld, item);
		Wrapper.INSTANCE.player().sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement((int)Wrapper.INSTANCE.player().posX,Wrapper.INSTANCE.mc().theWorld.getHeightValue((int)Wrapper.INSTANCE.player().posX, (int)Wrapper.INSTANCE.player().posZ), (int)Wrapper.INSTANCE.player().posZ, -1, item, 0.0F, 0.0F, 0.0F));
	}


	private boolean hasItem(String blockTileName) {
		for (int i = 36; i <= 44; i++) {
			if (Wrapper.INSTANCE.player().inventoryContainer.getSlot(i).getHasStack()) {
				String blockName = Wrapper.INSTANCE.player().inventoryContainer.getSlot(i).getStack().getDisplayName();
				if (blockName.equalsIgnoreCase(blockTileName))
					return true;
			}
		}
		return false;
	}

	private void switchToItem(String itemName) {
		for (int i = 36; i <= 44; i++)
			if (Wrapper.INSTANCE.player().inventoryContainer.getSlot(i).getHasStack()) {
				String blockName = Wrapper.INSTANCE.player().inventoryContainer.getSlot(i).getStack().getDisplayName();
				if (blockName.equalsIgnoreCase(itemName)) {
					Wrapper.INSTANCE.player().inventory.currentItem = (i - 36);
					break;
				}
			}
	}
}
