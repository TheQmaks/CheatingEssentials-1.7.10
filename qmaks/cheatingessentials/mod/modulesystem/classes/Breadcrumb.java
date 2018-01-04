package qmaks.cheatingessentials.mod.modulesystem.classes;

import java.util.concurrent.CopyOnWriteArrayList;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

import org.lwjgl.opengl.GL11;

public class Breadcrumb extends Mod {

	public static CopyOnWriteArrayList<double[]> positionsList = new CopyOnWriteArrayList<double[]>();

	public Breadcrumb()
	{
		super(ModuleCategories.RENDER);
	}

	public String getName(){
		return "Breadcrumb";
	}

	static int count = 0;

	@Override
	public void onTicks() {
		if(this.isActive()) {
			count++;
			if(count >= 50) {
				count = 0;
				if(positionsList.size() > 5) {
					positionsList.remove(0);
				}
			}
			for(Object o : Wrapper.INSTANCE.mc().theWorld.playerEntities){
				if(o instanceof EntityPlayer) {
					EntityPlayer player1 = (EntityPlayer)o;

					boolean shouldBreadCrumb = (player1 == Wrapper.INSTANCE.mc().thePlayer) && ((Wrapper.INSTANCE.mc().thePlayer.movementInput.moveForward != 0) || (Wrapper.INSTANCE.mc().thePlayer.movementInput.moveStrafe != 0));

					if(shouldBreadCrumb) {
						double x =  (RenderManager.renderPosX);
						double y =  (RenderManager.renderPosY);
						double z =  (RenderManager.renderPosZ);

						positionsList.add(new double[] {x, y - player1.height, z});
					}
				}
			}
		}
	}

	private static double posit(double val) {
		return (val == 0) ? (val) : (val < 0 ? val * -1 : val);
	}

	@Override
	public void onWorldRender(){
		GL11.glPushMatrix();
		GL11.glLineWidth(2F);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_DEPTH_TEST);

		GL11.glBegin(GL11.GL_LINE_STRIP);
		for(double[] pos : positionsList) {
			double distance = posit(Math.hypot(pos[0] - RenderManager.renderPosX, pos[1] - RenderManager.renderPosY));
			if(distance > 100) continue;
			GL11.glColor4f(0.0F, 1.0F, 0.0F, 1.0F - (float)(distance / 100D));
			GL11.glVertex3d(pos[0] - RenderManager.renderPosX, pos[1]  - RenderManager.renderPosY, pos[2] - RenderManager.renderPosZ);
		}
		GL11.glEnd();
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
}
