package qmaks.cheatingessentials.mod.modulesystem.classes;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.relationsystem.Friend;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class Tracers extends Mod 
{

	public Tracers()
	{
		super(ModuleCategories.RENDER);
	}

	@Override
	public String getName()
	{
		return "Tracers";
	}

	@Override
	public String getDescription(){
		return "Traces a line to the players in MP";
	}

	@Override
	public void onWorldRender()
	{
		try{
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_LINE_SMOOTH);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDepthMask(false);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glLineWidth(1.5F);
			for(Object entities: Wrapper.INSTANCE.mc().theWorld.loadedEntityList){
				if (entities != Wrapper.INSTANCE.mc().thePlayer && entities != null){
					if (entities instanceof EntityPlayer && !((EntityPlayer) entities).isDead && !((EntityPlayer) entities).isInvisible()){ //Add EntityMob also if you want to lol.
						EntityPlayer entity = (EntityPlayer)entities;
						float distance = Wrapper.INSTANCE.mc().renderViewEntity.getDistanceToEntity(entity);
						double posX = ((entity.lastTickPosX + (entity.posX - entity.lastTickPosX) - RenderManager.renderPosX));
						double posY = ((entity.lastTickPosY + 1.4 + (entity.posY - entity.lastTickPosY) - RenderManager.renderPosY));
						double posZ = ((entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) - RenderManager.renderPosZ));
						String playerName = Wrapper.INSTANCE.player().getGameProfile().getName();
						if(Friend.instance().readFriend(playerName)){
							GL11.glColor3f(0.0F, 1.0F, 0.0F);
						}
						else{
							if (distance <= 6F){
								GL11.glColor3f(1.0F, 0.0F, 0.0F);
							}
							else if (distance <= 96F){
								GL11.glColor3f(1.0F, (distance / 100F), 0.0F);
							}
							else if (distance > 96F){
								GL11.glColor3f(0.1F, 0.6F, 255.0F);
							}
						}

						GL11.glBegin(GL11.GL_LINE_LOOP);
						GL11.glVertex3d(0, 0, 0);
						GL11.glVertex3d(posX, posY, posZ);
						GL11.glEnd();
					}
				}
			}

			GL11.glDisable(GL11.GL_BLEND);
			GL11.glDepthMask(true);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glDisable(GL11.GL_LINE_SMOOTH);
			GL11.glPopMatrix();

		}
		catch(Exception e){}
	}
}