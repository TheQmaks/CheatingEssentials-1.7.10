package qmaks.cheatingessentials.mod.modulesystem.classes;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemSnowball;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class Projectiles extends Mod {

	public Projectiles()
	{
		super(ModuleCategories.RENDER);
	}

	@Override
	public String getName(){
		return "Projectiles";
	}

	@Override
	public void onWorldRender(){
		boolean bow = false;
		EntityPlayer player = Wrapper.INSTANCE.player();
		if (player.getCurrentEquippedItem() != null) {
			Item item = player.getCurrentEquippedItem().getItem();
			if ((!(item instanceof ItemBow)) && (!(item instanceof ItemSnowball)) && (!(item instanceof ItemEnderPearl)) && (!(item instanceof ItemEgg)))
				return;
			if ((item instanceof ItemBow))
				bow = true;
		}
		else {
			return;
		}

		double posX = RenderManager.renderPosX - MathHelper.cos(player.rotationYaw / 180.0F * 3.141593F) * 0.16F;
		double posY = RenderManager.renderPosY + player.getEyeHeight() - 0.1000000014901161D;
		double posZ = RenderManager.renderPosZ - MathHelper.sin(player.rotationYaw / 180.0F * 3.141593F) * 0.16F;
		double motionX = -MathHelper.sin(player.rotationYaw / 180.0F * 3.141593F) * MathHelper.cos(player.rotationPitch / 180.0F * 3.141593F) * (bow ? 1.0D : 0.4D);
		double motionY = -MathHelper.sin(player.rotationPitch / 180.0F * 3.141593F) * (bow ? 1.0D : 0.4D);
		double motionZ = MathHelper.cos(player.rotationYaw / 180.0F * 3.141593F) * MathHelper.cos(player.rotationPitch / 180.0F * 3.141593F) * (bow ? 1.0D : 0.4D);

		if ((player.getItemInUseCount() <= 0) && (bow)) {
			return;
		}
		int var6 = 72000 - player.getItemInUseCount();
		float power = var6 / 20.0F;
		power = (power * power + power * 2.0F) / 3.0F;
		if (power < 0.1D)
			return;
		if (power > 1.0F)
			power = 1.0F;
		GL11.glColor3f(1.0F - power, power, 0.0F);

		float distance = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
		motionX /= distance;
		motionY /= distance;
		motionZ /= distance;
		motionX *= (bow ? power * 2.0F : 1.0F) * 1.5D;
		motionY *= (bow ? power * 2.0F : 1.0F) * 1.5D;
		motionZ *= (bow ? power * 2.0F : 1.0F) * 1.5D;

		GL11.glLineWidth(2.0F);
		GL11.glBegin(3);
		boolean hasLanded = false; boolean isEntity = false;
		MovingObjectPosition landingPosition = null;
		float size = (float)(bow ? 0.3D : 0.25D);
		while (!hasLanded) {
			Vec3 present = Vec3.createVectorHelper(posX, posY, posZ);
			Vec3 future = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
			MovingObjectPosition possibleLandingStrip = Wrapper.INSTANCE.world().func_147447_a(present, future, false, true, true);
			present = Vec3.createVectorHelper(posX, posY, posZ);
			future = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
			if (possibleLandingStrip != null) {
				hasLanded = true;
				landingPosition = possibleLandingStrip;
			}
			Entity hitEntity = null;
			AxisAlignedBB arrowBox = AxisAlignedBB.getBoundingBox(posX - size, posY - size, posZ - size, posX + size, posY + size, posZ + size);
			List entities = getEntitiesWithinAABB(arrowBox.addCoord(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));

			for (int index = 0; index < entities.size(); index++) {
				Entity entity = (Entity)entities.get(index);

				if ((entity.canBeCollidedWith()) && (entity != player)) {
					float var11 = 0.3F;
					AxisAlignedBB var12 = entity.boundingBox.expand(var11, var11, var11);
					MovingObjectPosition possibleEntityLanding = var12.calculateIntercept(present, future);
					if (possibleEntityLanding != null) {
						hasLanded = true;
						isEntity = true;
						landingPosition = possibleEntityLanding;
					}
				}
			}
			posX += motionX;
			posY += motionY;
			posZ += motionZ;
			float motionAdjustment = 0.99F;
			AxisAlignedBB boundingBox = AxisAlignedBB.getBoundingBox(posX - size, posY - size, posZ - size, posX + size, posY + size, posZ + size);
			if (isInMaterial(boundingBox, Material.water)) {
				motionAdjustment = 0.8F;
			}
			motionX *= motionAdjustment;
			motionY *= motionAdjustment;
			motionZ *= motionAdjustment;
			motionY -= (bow ? 0.05D : 0.03D);
			GL11.glVertex3d(posX - RenderManager.renderPosX, posY - RenderManager.renderPosY, posZ - RenderManager.renderPosZ);
		}
		GL11.glEnd();
		GL11.glPushMatrix();
		GL11.glTranslated(posX - RenderManager.renderPosX, posY - RenderManager.renderPosY, posZ - RenderManager.renderPosZ);
		if (landingPosition != null) {
			switch (landingPosition.sideHit) {
			case 2:
				GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
				break;
			case 3:
				GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
				break;
			case 4:
				GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
				break;
			case 5:
				GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
				break;
			}

			if (isEntity)
				GL11.glColor3f(1.0F, 0.0F, 0.0F);
		}
		renderPoint();
		GL11.glPopMatrix();
	}

	private void renderPoint() {
		GL11.glBegin(1);
		GL11.glVertex3d(-0.5D, 0.0D, 0.0D);
		GL11.glVertex3d(0.0D, 0.0D, 0.0D);
		GL11.glVertex3d(0.0D, 0.0D, -0.5D);
		GL11.glVertex3d(0.0D, 0.0D, 0.0D);
		GL11.glVertex3d(0.5D, 0.0D, 0.0D);
		GL11.glVertex3d(0.0D, 0.0D, 0.0D);
		GL11.glVertex3d(0.0D, 0.0D, 0.5D);
		GL11.glVertex3d(0.0D, 0.0D, 0.0D);
		GL11.glEnd();
		Cylinder c = new Cylinder();
		GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
		c.setDrawStyle(100011);
		c.draw(0.5F, 0.5F, 0.1F, 24, 1);
	}

	private boolean isInMaterial(AxisAlignedBB axisalignedBB, Material material) {
		int chunkMinX = MathHelper.floor_double(axisalignedBB.minX);
		int chunkMaxX = MathHelper.floor_double(axisalignedBB.maxX + 1.0D);
		int chunkMinY = MathHelper.floor_double(axisalignedBB.minY);
		int chunkMaxY = MathHelper.floor_double(axisalignedBB.maxY + 1.0D);
		int chunkMinZ = MathHelper.floor_double(axisalignedBB.minZ);
		int chunkMaxZ = MathHelper.floor_double(axisalignedBB.maxZ + 1.0D);

		if (!Wrapper.INSTANCE.world().checkChunksExist(chunkMinX, chunkMinY, chunkMinZ, chunkMaxX, chunkMaxY, chunkMaxZ)) {
			return false;
		}
		boolean isWithin = false;
		Vec3 vector = Vec3.createVectorHelper(0.0D, 0.0D, 0.0D);

		for (int x = chunkMinX; x < chunkMaxX; x++) {
			for (int y = chunkMinY; y < chunkMaxY; y++) {
				for (int z = chunkMinZ; z < chunkMaxZ; z++) {
					Block block = Block.getBlockById(Wrapper.INSTANCE.world().getBlockMetadata(x, y, z));

					if ((block != null) && (block.getMaterial() == material)) {
						double liquidHeight = y + 1 - BlockLiquid.getLiquidHeightPercent(Wrapper.INSTANCE.world().getBlockMetadata(x, y, z));

						if (chunkMaxY >= liquidHeight) {
							isWithin = true;
						}
					}
				}
			}
		}
		return isWithin;
	}

	private List getEntitiesWithinAABB(AxisAlignedBB axisalignedBB)
	{
		List list = new ArrayList();
		int chunkMinX = MathHelper.floor_double((axisalignedBB.minX - 2.0D) / 16.0D);
		int chunkMaxX = MathHelper.floor_double((axisalignedBB.maxX + 2.0D) / 16.0D);
		int chunkMinZ = MathHelper.floor_double((axisalignedBB.minZ - 2.0D) / 16.0D);
		int chunkMaxZ = MathHelper.floor_double((axisalignedBB.maxZ + 2.0D) / 16.0D);

		for (int x = chunkMinX; x <= chunkMaxX; x++) {
			for (int z = chunkMinZ; z <= chunkMaxZ; z++) {
				if (Wrapper.INSTANCE.world().getChunkProvider().chunkExists(x, z)) {
					Wrapper.INSTANCE.world().getChunkFromChunkCoords(x, z).getEntitiesWithinAABBForEntity(Wrapper.INSTANCE.player(), axisalignedBB, list, (IEntitySelector)null);
				}
			}
		}

		return list;
	}
}