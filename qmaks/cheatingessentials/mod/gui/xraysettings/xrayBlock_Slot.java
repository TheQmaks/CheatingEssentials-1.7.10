package qmaks.cheatingessentials.mod.gui.xraysettings;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.Tessellator;
import qmaks.cheatingessentials.mod.gui.xraysettings.xrayBlocks;
import qmaks.cheatingessentials.mod.gui.xraysettings.xray_Gui;

public class xrayBlock_Slot extends GuiSlot {

   int selectedIndex = -1;
   xray_Gui xrayGui;


   public xrayBlock_Slot(Minecraft par1Minecraft, int width, int height, int top, int bottom, int slotHeight, xray_Gui xrayGui) {
      super(par1Minecraft, width, height, top, bottom, slotHeight);
      this.xrayGui = xrayGui;
      xrayBlocks.init();
   }

   protected int getSize() {
      return xrayBlocks.blocks.size();
   }

   protected boolean isSelected(int i) {
      return i == this.selectedIndex;
   }

   protected void drawBackground() {}

   protected void elementClicked(int i, boolean var2, int var3, int var4) {
      this.selectedIndex = i;
   }

   protected void drawSlot(int i, int j, int k, int var4, Tessellator var5, int var6, int var7) {
      xrayBlocks xblock = (xrayBlocks)xrayBlocks.blocks.get(i);
      xray_Gui var10000 = this.xrayGui;
      xray_Gui.drawRect(175 + j, 1 + k, this.xrayGui.width - j - 20, 15 + k, (('\uc800' | xblock.r) << 8 | xblock.g) << 8 | xblock.b);
      if(xblock.id != null && Block.blockRegistry.containsKey(xblock.id)) {
         this.xrayGui.drawString(this.xrayGui.render, ((Block)Block.blockRegistry.getObject(xblock.id)).getLocalizedName(), j + 2, k + 1, 16777215);
      }

   }
}
