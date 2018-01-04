package qmaks.cheatingessentials.mod.modulesystem.classes;

import java.util.Iterator;
import java.util.Timer;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.material.Material;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.MathHelper;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.commands.ACommandAuraRange;
import qmaks.cheatingessentials.mod.relationsystem.Friend;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class KillAura extends Mod {

	public static boolean isActive = false;

	private long currentMS = 0L;
	private long lastMS = -1L;

	public KillAura()
	{
		super(ModuleCategories.COMBAT);
	}

	@Override
	public String getName(){
		return "KillAura";
	}

	@Override
	public void onEnableMod() {
		isActive = true;
	}

	@Override
	public void onDisableMod() {
		isActive = false;
	}

	public boolean hasDelayRun(long time) {
		return this.currentMS - this.lastMS >= time;
	}

	@Override
	public void onTicks() {
		try {
			this.currentMS = (System.nanoTime() / 900000L);
			if (hasDelayRun(133L))
			{
				for (Iterator i = Wrapper.INSTANCE.world().loadedEntityList.iterator(); i.hasNext();) { Object o = i.next();
				if ((o instanceof EntityPlayer))
				{
					EntityPlayer e = (EntityPlayer)o;
					if ((!(e instanceof EntityPlayerSP)) && (!Friend.instance().readFriend(e.getCommandSenderName())) && (Wrapper.INSTANCE.player().getDistanceToEntity(e) <= ACommandAuraRange.aurarange) && (!e.isDead))
					{
						if ((AutoBlock.isActive) && (Wrapper.INSTANCE.player().getCurrentEquippedItem() != null) && (Wrapper.INSTANCE.player().getCurrentEquippedItem().getItem() instanceof ItemSword)) {
							ItemStack lel = Wrapper.INSTANCE.player().getCurrentEquippedItem();
							lel.useItemRightClick(Wrapper.INSTANCE.world(), Wrapper.INSTANCE.player());
						}

						if(Criticals.isActive && !Wrapper.INSTANCE.player().isInWater() && !Wrapper.INSTANCE.player().isInsideOfMaterial(Material.lava) && !Wrapper.INSTANCE.player().isInsideOfMaterial(Material.web) && Wrapper.INSTANCE.player().onGround) {
							Wrapper.INSTANCE.player().motionY = 0.1000000014901161D;
							Wrapper.INSTANCE.player().fallDistance = 0.1F;
							Wrapper.INSTANCE.player().onGround = false;
						}

						if(AimBot.isActive) {
							AimBot.faceEntity(e);
						}

						Wrapper.INSTANCE.player().setSprinting(false);
						Wrapper.INSTANCE.player().swingItem();
						Wrapper.INSTANCE.mc().playerController.attackEntity(Wrapper.INSTANCE.player(), e);

						this.lastMS = (System.nanoTime() / 900000L);
						break;
					}
				}
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
