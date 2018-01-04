package qmaks.cheatingessentials.mod.modulesystem.classes;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.commands.ACommandAuraRange;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class MobAura extends Mod {

	public static boolean isActive = false;

	private long currentMS = 0L;
	private long lastMS = -1L;

	public MobAura()
	{
		super(ModuleCategories.COMBAT);
	}

	@Override
	public String getName(){
		return "MobAura";
	}

	@Override
	public void onEnableMod() {
		isActive = true;
	}

	@Override
	public void onDisableMod() {
		isActive = false;
	}

	private boolean hasDelayRun(long time) {
		return this.currentMS - this.lastMS >= time;
	}

	@Override
	public void onTicks() {
		try {
			this.currentMS = (System.nanoTime() / 980000L);
			if (hasDelayRun(133L))
			{
				for (int i = 0; i < Wrapper.INSTANCE.mc().theWorld.loadedEntityList.size(); i++)
				{
					Entity e = (Entity)Wrapper.INSTANCE.mc().theWorld.getLoadedEntityList().get(i);

					if ((e == Wrapper.INSTANCE.player()) || (e.isDead) || (Wrapper.INSTANCE.player().getDistanceToEntity(e) >= ACommandAuraRange.aurarange) || (!(e instanceof EntityLiving)))
					{
						continue;
					}

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
					this.lastMS = (System.nanoTime() / 980000L);
					break;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();    	 
		}
	}
}
