package qmaks.cheatingessentials.mod.modulesystem.classes;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.relationsystem.Friend;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class TriggerBot extends Mod {

	public static boolean isActive = false;

	private static Random rand = new Random();

	private static long lastMS;

	public TriggerBot()
	{
		super(ModuleCategories.COMBAT);
	}

	@Override
	public String getName(){
		return "TriggerBot";
	}

	@Override
	public String getDescription(){
		return "TriggerBot";
	}

	@Override
	public void onEnableMod() {
		isActive = true;
	}

	@Override
	public void onDisableMod() {
		isActive = false;
	}

	private static long getCurrentMS() {
		return System.nanoTime() / 1000000L;
	}

	private static boolean hasReached(long milliseconds) {
		return getCurrentMS() - lastMS >= milliseconds;
	}

	public void reset() {
		lastMS = getCurrentMS();
	}

	private boolean isValidTarget(Entity e) {
		return ((!Friend.instance().readFriend(e.getCommandSenderName())) || (!(e instanceof EntityPlayer))) && ((e instanceof EntityLivingBase));
	}

	@Override
	public void onTicks() {
		try {
			if ((!Friend.instance().readFriend(Wrapper.INSTANCE.mc().objectMouseOver.entityHit.getCommandSenderName())) && (Wrapper.INSTANCE.mc().objectMouseOver != null) && (Wrapper.INSTANCE.mc().objectMouseOver.entityHit != null) && (isValidTarget(Wrapper.INSTANCE.mc().objectMouseOver.entityHit)) && (hasReached(150 + this.rand.nextInt(100)))) {
				if(Criticals.isActive && !Wrapper.INSTANCE.player().isInWater() && !Wrapper.INSTANCE.player().isInsideOfMaterial(Material.lava) && !Wrapper.INSTANCE.player().isInsideOfMaterial(Material.web) && Wrapper.INSTANCE.player().onGround) {
					Wrapper.INSTANCE.player().motionY = 0.1D;
					Wrapper.INSTANCE.player().fallDistance = 0.1F;
					Wrapper.INSTANCE.player().onGround = false;
				}

				if ((AutoBlock.isActive) && (Wrapper.INSTANCE.player().getCurrentEquippedItem() != null) && (Wrapper.INSTANCE.player().getCurrentEquippedItem().getItem() instanceof ItemSword)) {
					ItemStack lel = Wrapper.INSTANCE.player().getCurrentEquippedItem();
					lel.useItemRightClick(Wrapper.INSTANCE.world(), Wrapper.INSTANCE.player());
				}
				Wrapper.INSTANCE.player().swingItem();
				Wrapper.INSTANCE.mc().playerController.attackEntity(Wrapper.INSTANCE.player(), Wrapper.INSTANCE.mc().objectMouseOver.entityHit);
				reset();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}