package qmaks.cheatingessentials.mod.modulesystem.classes;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class WaterWalk extends Mod {

	private static boolean overwater;
	private int delay;

	public WaterWalk()
	{
		super(ModuleCategories.NOCHEATPLUS);
	}

	@Override
	public String getName(){
		return "WaterWalk";
	}

	public static boolean isOnLiquid(AxisAlignedBB boundingBox)
	{
		boundingBox = boundingBox.contract(0.01D, 0.0D, 0.01D).offset(0.0D, -0.01D, 0.0D);
		boolean onLiquid = false;
		int y = (int)boundingBox.minY;
		for (int x = MathHelper.floor_double(boundingBox.minX); x < MathHelper.floor_double(boundingBox.maxX + 1.0D); x++) {
			for (int z = MathHelper.floor_double(boundingBox.minZ); z < MathHelper.floor_double(boundingBox.maxZ + 1.0D); z++) {
				Block block = Wrapper.INSTANCE.world().getBlock(x, y, z);
				if (block == Blocks.air)
				{
					continue;
				}
				if (!(block instanceof BlockLiquid)) {
					return false;
				}

				onLiquid = true;
			}
		}

		return onLiquid;
	}

	private static boolean isInLiquid()
	{
		AxisAlignedBB par1AxisAlignedBB = Wrapper.INSTANCE.player().boundingBox.contract(0.001D, 0.001D, 0.001D);
		int minX = MathHelper.floor_double(par1AxisAlignedBB.minX);
		int maxX = MathHelper.floor_double(par1AxisAlignedBB.maxX + 1.0D);
		int minY = MathHelper.floor_double(par1AxisAlignedBB.minY);
		int maxY = MathHelper.floor_double(par1AxisAlignedBB.maxY + 1.0D);
		int minZ = MathHelper.floor_double(par1AxisAlignedBB.minZ);
		int maxZ = MathHelper.floor_double(par1AxisAlignedBB.maxZ + 1.0D);

		if (!Wrapper.INSTANCE.world().checkChunksExist(minX, minY, minZ, maxX, maxY, maxZ)) {
			return false;
		}
		Vec3 vec = Vec3.createVectorHelper(0.0D, 0.0D, 0.0D);
		for (int X = minX; X < maxX; X++) {
			for (int Y = minY; Y < maxY; Y++) {
				for (int Z = minZ; Z < maxZ; Z++) {
					Block block = Wrapper.INSTANCE.world().getBlock(X, Y, Z);
					if ((block instanceof BlockLiquid)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	@Override
	public void onTicks(){
		if (isOnLiquid(Wrapper.INSTANCE.player().boundingBox))
		{
			this.delay += 1;

			if (this.delay == 4)
			{
				Wrapper.INSTANCE.player().sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(Wrapper.INSTANCE.player().posX, Wrapper.INSTANCE.player().boundingBox.minY - 0.02D, Wrapper.INSTANCE.player().posY - 0.02D, Wrapper.INSTANCE.player().posZ, false));
				this.delay = 0;
			}

		}

		if (isInLiquid())
		{
			Wrapper.INSTANCE.player().motionY = 0.08500000000000001D;
		}

		Block Blocks = Wrapper.INSTANCE.world().getBlock((int)Wrapper.INSTANCE.player().posX, (int)Wrapper.INSTANCE.player().posY - 2, (int)Wrapper.INSTANCE.player().posZ);
		Block lel = Wrapper.INSTANCE.world().getBlock((int)Wrapper.INSTANCE.player().posX, (int)Wrapper.INSTANCE.player().posY + 1, (int)Wrapper.INSTANCE.player().posZ);
		if ((Blocks instanceof BlockLiquid))
			overwater = true;
		else if (!(Blocks instanceof BlockLiquid))
		{
			overwater = false;
		} else if (((lel instanceof BlockLiquid)) || 
				((Blocks instanceof BlockLiquid)) || (!(Blocks instanceof BlockLiquid)));
	}
}
