package qmaks.cheatingessentials.mod.modulesystem.classes;

import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.external.axis.AltAxisAlignedBB;
import qmaks.cheatingessentials.mod.util.GLUtils;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityFallingBlock;

public class ProphuntESP extends Mod {

	public ProphuntESP()
	{
		super(ModuleCategories.MINIGAMES);
	}

	@Override
	public String getName(){
		return "ProphuntESP";
	}

	@Override
	public void onWorldRender()
	{
		if(isActive()){
			for (Object o : Wrapper.INSTANCE.world().loadedEntityList) {
				if (o instanceof EntityFallingBlock) 
				{
					final EntityFallingBlock e = (EntityFallingBlock)o;

					final float halfWidth = e.width / 2.0F;

					AltAxisAlignedBB aaabb = AltAxisAlignedBB.getBoundingBox(e.width - halfWidth, e.height, e.width - halfWidth, (e.width + halfWidth), (e.height + e.height), (e.width + halfWidth));

					double renderX = e.lastTickPosX + (e.posX - e.lastTickPosX) - RenderManager.renderPosX - (e.width);
					double renderY = e.lastTickPosY + (e.posY - e.lastTickPosY) - RenderManager.renderPosY - e.height;
					double renderZ = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) - RenderManager.renderPosZ - (e.width);

					GL11.glPushMatrix();
					GL11.glTranslated(renderX, renderY, renderZ);
					GL11.glColor4f(0.27F, 0.70F, 0.92F, 1.0F);
					GL11.glColor4f(0.92F, 0.20F, 0.20F, 1.0F);
					GLUtils.startDrawingESPs(aaabb, 0.27F, 0.70F, 0.50F);
					GL11.glPopMatrix();
				}
			}
		}
	}
}
