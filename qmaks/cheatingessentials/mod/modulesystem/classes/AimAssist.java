package qmaks.cheatingessentials.mod.modulesystem.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.relationsystem.Friend;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class AimAssist extends Mod {

	public static boolean isActive = false;

	private double range = 3.8D;
	private EntityPlayer curtarget;
	private List<EntityPlayer> targetlist = new ArrayList();

	public AimAssist()
	{
		super(ModuleCategories.COMBAT);
	}

	@Override
	public String getName(){
		return "AimAssist";
	}

	@Override
	public String getDescription(){
		return "AimAssist";
	}

	@Override
	public void onEnableMod() {
		this.curtarget = null;
	}
	
	@Override
	public void onTicks()   {
		this.targetlist.clear();
		for (EntityPlayer e : (List<EntityPlayer>) Wrapper.INSTANCE.world().playerEntities) {
			if (isAttackable(e)) {
				this.targetlist.add(e);
			}
		}
		if (Wrapper.INSTANCE.mc().objectMouseOver.typeOfHit == MovingObjectType.ENTITY) {
			Entity entity = Wrapper.INSTANCE.mc().objectMouseOver.entityHit;
			if ((entity instanceof EntityPlayer)) {
				this.curtarget = ((EntityPlayer)entity);
				return;
			}
		}
		if ((!this.targetlist.contains(this.curtarget)) && (this.curtarget != null)) {
			this.curtarget = null;
			return;
		}
		Random r = new Random();
		if (this.curtarget == null) return;
		float diff = Wrapper.INSTANCE.player().rotationYaw - getAngles(this.curtarget)[0] % 180.0F;
		Wrapper.INSTANCE.player().rotationYaw = (float)(Wrapper.INSTANCE.player().rotationYaw - (Wrapper.INSTANCE.player().rotationYaw - getAngles(this.curtarget)[0]) * 0.5D);
		Wrapper.INSTANCE.player().rotationPitch = (float)(Wrapper.INSTANCE.player().rotationPitch - (Wrapper.INSTANCE.player().rotationPitch - getAngles(this.curtarget)[1]) * 0.5D);
	}

	private float[] getAngles(Entity entity)
	{
		float xDiff = (float)(entity.posX - Wrapper.INSTANCE.player().posX);
		float yDiff = (float)(entity.boundingBox.minY + entity.getEyeHeight() - Wrapper.INSTANCE.player().boundingBox.maxY);
		float zDiff = (float)(entity.posZ - Wrapper.INSTANCE.player().posZ);
		float yaw = (float)(Math.atan2(zDiff, xDiff) * 180.0D / Math.PI - 90.0D);
		float pitch = (float)(-Math.toDegrees(Math.atan(yDiff / Math.sqrt(zDiff * zDiff + xDiff * xDiff))));
		return new float[] {yaw, pitch};
	}

	private boolean isAttackable(Entity e) {
		if (e == null)
			return false;
		if ((e instanceof EntityPlayer)) {
			EntityPlayer p = (EntityPlayer)e;
			if (Friend.instance().readFriend(e.getCommandSenderName())) return false;
			if ((!p.isDead) && (!p.isInvisible()) && (Wrapper.INSTANCE.player().getDistanceToEntity(p) <= this.range) && (Wrapper.INSTANCE.player().canEntityBeSeen(p)) && (p != Wrapper.INSTANCE.player())) {
				return true;
			}
		}

		return false;
	}
}
