package qmaks.cheatingessentials.mod.modulesystem.classes;

import java.util.Iterator;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.commands.ACommandAuraRange;
import qmaks.cheatingessentials.mod.relationsystem.Friend;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class AimBot extends Mod {

	public static boolean isActive = false;

	public AimBot()
	{
		super(ModuleCategories.COMBAT);
	}

	@Override
	public String getName(){
		return "AimBot";
	}

	@Override
	public String getDescription(){
		return "AimBot";
	}

	private static long time = 0L;

	@Override
	public void onEnableMod() {
		isActive = true;
	}

	@Override
	public void onDisableMod() {
		isActive = false;
	}

	public static void faceEntity(Entity e) {
		double x = e.posX - Wrapper.INSTANCE.player().posX;
		double y = e.posY - Wrapper.INSTANCE.player().posY;
		double z = e.posZ - Wrapper.INSTANCE.player().posZ;
		double d1 = Wrapper.INSTANCE.player().posY + Wrapper.INSTANCE.player().getEyeHeight() - (e.posY + e.getEyeHeight());
		double d3 = MathHelper.sqrt_double(x * x + z * z);
		float f = (float)(Math.atan2(z, x) * 180.0D / 3.141592653589793D) - 90.0F;
		float f1 = (float)(-(Math.atan2(d1, d3) * 180.0D / 3.141592653589793D));
		Wrapper.INSTANCE.player().setPositionAndRotation(Wrapper.INSTANCE.player().posX, Wrapper.INSTANCE.player().posY, Wrapper.INSTANCE.player().posZ, f, -f1);
	}

	@Override
	public void onTicks() {
		try {
			if(!KillAura.isActive && !MobAura.isActive && !ProphuntAura.isActive)
				for (Iterator i = Wrapper.INSTANCE.world().loadedEntityList.iterator(); i.hasNext(); ) { Object o = i.next();
				if (o instanceof EntityPlayer) {
					EntityPlayer e = (EntityPlayer)o;
					if ((!(e instanceof EntityPlayerSP)) && (!Friend.instance().readFriend(e.getCommandSenderName())) && (Wrapper.INSTANCE.player().getDistanceToEntity(e) <= ACommandAuraRange.aurarange) && (!e.isDead) && (Wrapper.INSTANCE.player().canEntityBeSeen(e)) && (e.isEntityAlive()) && (!e.isDead)) {
						this.faceEntity(e);
						break;
					}
				}
				}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
