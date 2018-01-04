package qmaks.cheatingessentials.mod.modulesystem.classes;

import java.util.Iterator;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.gui.xraysettings.xrayBlocks;
import qmaks.cheatingessentials.mod.util.GLUtils;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class XRay extends Mod {

	public static int radius = 45;
	public static int displayListid = 0;
	public static int cooldownTicks = 0;

	public XRay()
	{
		super(ModuleCategories.RENDER);
		this.setKeybinding(Keyboard.KEY_X);
	}

	@Override
	public String getName(){
		return "X-Ray";
	}

	@Override
	public String getDescription(){
		return "XRay";
	}

	@Override
	public void onEnableMod(){
		cooldownTicks = 0;
	}

	@Override
	public void onDisableMod(){
		GL11.glDeleteLists(displayListid, 1);
	}

	@Override
	public void onWorldRender() {
		if(Wrapper.INSTANCE.world() != null) {
			double doubleX = Wrapper.INSTANCE.player().lastTickPosX + (Wrapper.INSTANCE.player().posX - Wrapper.INSTANCE.player().lastTickPosX);/* * (double)this.partialTicks;*/
			double doubleY = Wrapper.INSTANCE.player().lastTickPosY + (Wrapper.INSTANCE.player().posY - Wrapper.INSTANCE.player().lastTickPosY);/* * (double)this.partialTicks;*/
			double doubleZ = Wrapper.INSTANCE.player().lastTickPosZ + (Wrapper.INSTANCE.player().posZ - Wrapper.INSTANCE.player().lastTickPosZ);/* * (double)this.partialTicks;*/
			GL11.glPushMatrix();
			GL11.glTranslated(-doubleX, -doubleY, -doubleZ);
			GL11.glCallList(displayListid);
			GL11.glPopMatrix();
		}
	}

	@Override
	public void onTicks() {
		if(cooldownTicks < 1) {
			this.compileDL();
			cooldownTicks = 80;
		}

		--cooldownTicks;
	}

	private void compileDL() {
		GL11.glNewList(displayListid, 4864);
		GL11.glDisable(3553);
		GL11.glDisable(2929);
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		GL11.glBegin(1);
		if(Wrapper.INSTANCE.world() != null && Wrapper.INSTANCE.player() != null) {
			for(int i = (int)Wrapper.INSTANCE.player().posX - radius; i <= (int)Wrapper.INSTANCE.player().posX + radius; ++i) {
				for(int j = (int)Wrapper.INSTANCE.player().posZ - radius; j <= (int)Wrapper.INSTANCE.player().posZ + radius; ++j) {
					int k = 0;

					for(int height = Wrapper.INSTANCE.world().getHeightValue(i, j); k <= height; ++k) {
						Block bId = Wrapper.INSTANCE.world().getBlock(i, k, j);
						if(bId != Blocks.air && bId != Blocks.stone) {
							Iterator blocks = xrayBlocks.blocks.iterator();

							while(blocks.hasNext()) {
								xrayBlocks block = (xrayBlocks)blocks.next();
								if(block.enabled) {
									Block blocki = (Block)Block.blockRegistry.getObject(block.id);
									if(blocki == bId && (block.meta == -1 || block.meta == Wrapper.INSTANCE.world().getBlockMetadata(i, k, j))) {
										GLUtils.renderBlock(i, k, j, block);
										break;
									}
								}
							}
						}
					}
				}
			}

			GL11.glEnd();
			GL11.glEnable(2929);
			GL11.glDisable(3042);
			GL11.glEnable(3553);
			GL11.glEndList();
		}
	}
}