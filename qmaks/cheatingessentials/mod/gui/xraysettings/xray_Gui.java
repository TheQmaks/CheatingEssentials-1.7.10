package qmaks.cheatingessentials.mod.gui.xraysettings;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import qmaks.cheatingessentials.mod.gui.xraysettings.xrayBlock_Slot;
import qmaks.cheatingessentials.mod.gui.xraysettings.xrayBlocks;
import qmaks.cheatingessentials.mod.gui.xraysettings.xray_AddGui;
import qmaks.cheatingessentials.mod.main.CheatingEssentials;
import qmaks.cheatingessentials.mod.modulesystem.classes.XRay;

public class xray_Gui extends GuiScreen {

   xrayBlock_Slot slot;
   GuiButton add;
   GuiButton del;
   GuiButton edit;
   GuiButton exit;
   FontRenderer render;

   public void initGui() {
      super.initGui();
      this.render = super.fontRendererObj;
      this.slot = new xrayBlock_Slot(Minecraft.getMinecraft(), super.width, super.height, 25, super.height - 25, 20, this);
      this.add = new GuiButton(0, super.width / 9, super.height - 22, 70, 20, "Add Block");
      this.del = new GuiButton(1, super.width / 9 * 3, super.height - 22, 70, 20, "Delete Block");
      this.del.enabled = false;
      this.edit = new GuiButton(2, super.width / 9 * 5, super.height - 22, 70, 20, "Edit Block");
      this.edit.enabled = false;
      this.exit = new GuiButton(3, super.width / 9 * 7, super.height - 22, 70, 20, "Exit");
      super.buttonList.add(this.add);
      super.buttonList.add(this.del);
      super.buttonList.add(this.edit);
      super.buttonList.add(this.exit);
   }

   public void drawScreen(int par1, int par2, float par3) {
      this.slot.drawScreen(par1, par2, par3);
      super.drawScreen(par1, par2, par3);
      if(this.slot.selectedIndex != -1) {
         this.del.enabled = true;
         this.edit.enabled = true;
      } else {
         this.del.enabled = false;
         this.edit.enabled = false;
      }

   }

   protected void actionPerformed(GuiButton par1GuiButton) {
      switch(par1GuiButton.id) {
      case 0:
         Minecraft.getMinecraft().displayGuiScreen(new xray_AddGui());
         break;
      case 1:
         xrayBlocks.blocks.remove(this.slot.selectedIndex);
         this.slot.selectedIndex = -1;

         try {
            xrayBlocks.save();
         } catch (IOException var3) {
            var3.printStackTrace();
         }
         break;
      case 2:
         Minecraft.getMinecraft().displayGuiScreen(new xray_AddGui((xrayBlocks)xrayBlocks.blocks.get(this.slot.selectedIndex), this.slot.selectedIndex));
         break;
      case 3:
         Minecraft.getMinecraft().displayGuiScreen((GuiScreen)null);
         XRay.cooldownTicks = 0;
         break;
      default:
         this.slot.actionPerformed(par1GuiButton);
      }

   }
}
