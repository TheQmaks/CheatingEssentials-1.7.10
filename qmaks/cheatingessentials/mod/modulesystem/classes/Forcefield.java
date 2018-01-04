package qmaks.cheatingessentials.mod.modulesystem.classes;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.commands.ACommandAuraRange;
import qmaks.cheatingessentials.mod.relationsystem.Friend;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class Forcefield extends Mod {

	public static boolean isActive = false;

	public Forcefield()
	{
		super(ModuleCategories.COMBAT);
	}

	@Override
	public String getName(){
		return "Forcefield";
	}

	@Override
	public void onEnableMod() {
		isActive = true;
	}

	@Override
	public void onDisableMod() {
		isActive = false;
	}

	private void hitEntity(Entity e, boolean block, boolean criticals, boolean aimbot, boolean auto)
	{
		if ((block) && ((Wrapper.INSTANCE.player().getCurrentEquippedItem().getItem() instanceof ItemSword))) {
			ItemStack lel = Wrapper.INSTANCE.player().getCurrentEquippedItem();
			lel.useItemRightClick(Wrapper.INSTANCE.world(), Wrapper.INSTANCE.player());
		}

		if(criticals && !Wrapper.INSTANCE.player().isInWater() && !Wrapper.INSTANCE.player().isInsideOfMaterial(Material.lava) && !Wrapper.INSTANCE.player().isInsideOfMaterial(Material.web) && Wrapper.INSTANCE.player().onGround) {
			Wrapper.INSTANCE.player().motionY = 0.1000000014901161D;
			Wrapper.INSTANCE.player().fallDistance = 0.1F;
			Wrapper.INSTANCE.player().onGround = false;
		}

		if (aimbot) {
			AimBot.faceEntity(e);
		}

		Wrapper.INSTANCE.mc().playerController.attackEntity(Wrapper.INSTANCE.player(), e);
		Wrapper.INSTANCE.player().swingItem();
	}

	private float getDistanceToEntity(Entity from, Entity to)
	{
		return from.getDistanceToEntity(to);
	}

	private boolean isWithinRange(float range, Entity e)
	{
		return getDistanceToEntity(e, Wrapper.INSTANCE.player()) <= range;
	}

	@Override
	public void onTicks() {
		try
		{
			for (Object o : Wrapper.INSTANCE.world().loadedEntityList)
			{
				EntityLivingBase entity = null;
				if ((o instanceof EntityLivingBase)) {
					entity = (EntityLivingBase)o;
				}
				if ((entity != null) && (isWithinRange(ACommandAuraRange.aurarange, entity)) && (!Friend.instance().readFriend(entity.getCommandSenderName())) && (!entity.isDead) && (entity != Wrapper.INSTANCE.player())) {
					hitEntity(entity, AutoBlock.isActive, Criticals.isActive, AimBot.isActive, true);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
