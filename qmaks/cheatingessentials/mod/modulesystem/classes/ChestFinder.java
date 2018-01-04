package qmaks.cheatingessentials.mod.modulesystem.classes;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.external.axis.AltAxisAlignedBB;
import qmaks.cheatingessentials.mod.util.GLUtils;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import scala.Console;

public class ChestFinder extends Mod {
	
	public ChestFinder()
	{
		super(ModuleCategories.RENDER);
		this.setKeybinding(Keyboard.KEY_N);
	}
	
	@Override
	public String getName(){
		return "Chest Finder";
	}
	
	@Override
	public void onWorldRender(){
		
		/**
		 * @author Halalaboos / Huzuni Client
		 * @recoder Qmaks
		 */
		for (Object o : Wrapper.INSTANCE.world().loadedTileEntityList) {
            if (o instanceof TileEntityChest) {
            	
                final TileEntityChest chest = (TileEntityChest) o;
                
                final double renderX = chest.xCoord - RenderManager.renderPosX;
                final double renderY = chest.yCoord - RenderManager.renderPosY;
                final double renderZ = chest.zCoord - RenderManager.renderPosZ;
                GL11.glPushMatrix();
                GL11.glTranslated(renderX, renderY, renderZ);
                GL11.glColor3f(1, 1, 0);

                if (chest.adjacentChestXPos != null) {
                	AltAxisAlignedBB boundingBox = AltAxisAlignedBB.getBoundingBox(0, 0, 0, 2, 1, 1);
                    GL11.glColor4f(1, 1, 0, 0.1F);
                	GLUtils.startDrawingESPs(boundingBox, 0.3F, 0.8F, 1.0F);
                } else if (chest.adjacentChestZPos != null) {
                    AltAxisAlignedBB boundingBox = AltAxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 2);
                	GL11.glColor4f(1, 1, 0, 0.1F);
                	GLUtils.startDrawingESPs(boundingBox, 0.3F, 0.8F, 1.0F);
                } else if (chest.adjacentChestXPos == null && chest.adjacentChestZPos == null && chest.adjacentChestXNeg == null && chest.adjacentChestZNeg == null) {
                    AltAxisAlignedBB boundingBox = AltAxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1);
                	GL11.glColor4f(1, 1, 0, 0.1F);
                	GLUtils.startDrawingESPs(boundingBox, 0.3F, 0.8F, 1.0F);
                }

                GL11.glPopMatrix();
            } else if(o instanceof TileEntityEnderChest) {
            	
                final TileEntityEnderChest chest = (TileEntityEnderChest) o;
                
                final double renderX = chest.xCoord - RenderManager.renderPosX;
                final double renderY = chest.yCoord - RenderManager.renderPosY;
                final double renderZ = chest.zCoord - RenderManager.renderPosZ;
                GL11.glPushMatrix();
                GL11.glTranslated(renderX, renderY, renderZ);
                GL11.glColor3f(1, 1, 0);
                
            	AltAxisAlignedBB boundingBox;

                boundingBox = AltAxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1);
                GL11.glColor4f(1, 1, 0, 0.1F);
                GLUtils.startDrawingESPs(boundingBox, 0.3F, 0.0F, 0.5F);

                GL11.glPopMatrix();
            }
        }
	}
}
