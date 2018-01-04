package qmaks.cheatingessentials.mod.gui.xraysettings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import qmaks.cheatingessentials.mod.gui.xraysettings.xrayBlocks;
import qmaks.cheatingessentials.mod.gui.xraysettings.xrayGuiSlider;
import qmaks.cheatingessentials.mod.gui.xraysettings.xray_Gui;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class xray_AddGui extends GuiScreen {

   String id;
   xrayGuiSlider colorR;
   xrayGuiSlider colorG;
   xrayGuiSlider colorB;
   xrayGuiSlider colorA;
   private GuiButton add;
   private GuiButton cancel;
   private GuiButton matterMeta;
   private GuiButton isEnabled;
   private int selectedIndex;
   Minecraft field_146297_k;
   private int r;
   private int g;
   private int b;
   private int a;
   private boolean enabled;
   private boolean bmeta;
   private int meta;
   private int sliderpos;
   private GuiTextField searchbar;
   private ArrayList blocks;


   public xray_AddGui(int r, int g, int b, int a, int meta, String id, boolean enabled, int index) {
      this();
      this.r = r;
      this.g = g;
      this.b = b;
      this.a = a;
      this.meta = meta;
      this.bmeta = meta != -1;
      this.id = id;
      this.enabled = enabled;
      this.selectedIndex = index;
   }

   public xray_AddGui() {
      this.selectedIndex = -1;
      this.r = 128;
      this.g = 128;
      this.b = 128;
      this.a = 255;
      this.enabled = true;
      this.bmeta = false;
      this.blocks = new ArrayList();
      this.field_146297_k = Minecraft.getMinecraft();
   }

   public xray_AddGui(xrayBlocks xrayBlocks, int index) {
      this(xrayBlocks.r, xrayBlocks.g, xrayBlocks.b, xrayBlocks.a, xrayBlocks.meta, xrayBlocks.id, xrayBlocks.enabled, index);
   }

   protected void actionPerformed(GuiButton par1GuiButton) {
      if(par1GuiButton.id == 0) {
         if(this.selectedIndex != -1) {
            xrayBlocks.blocks.remove(this.selectedIndex);
         }

         xrayBlocks.blocks.add(new xrayBlocks((int)(this.colorR.percent * 255.0F), (int)(this.colorG.percent * 255.0F), (int)(this.colorB.percent * 255.0F), (int)(this.colorA.percent * 255.0F), this.bmeta?this.meta:-1, this.id, this.enabled));

         try {
            xrayBlocks.save();
         } catch (IOException var3) {
            var3.printStackTrace();
         }

         this.field_146297_k.displayGuiScreen(new xray_Gui());
      } else if(par1GuiButton.id == 1) {
         this.field_146297_k.displayGuiScreen(new xray_Gui());
      } else if(par1GuiButton.id == 6) {
         this.enabled = !this.enabled;
         this.isEnabled.displayString = this.enabled?"Enabled":"Disabled";
      } else if(par1GuiButton.id == 7) {
         this.bmeta = !this.bmeta;
         this.matterMeta.displayString = this.bmeta?"Meta-Check Enabled":"Meta-Check Disabled";
      }

      super.actionPerformed(par1GuiButton);
   }

   public void initGui() {
      super.initGui();
      this.add = new GuiButton(0, super.width / 2 - 42, super.height - 22, 40, 20, "Add");
      super.buttonList.add(this.add);
      this.cancel = new GuiButton(1, super.width / 2 + 42, super.height - 22, 40, 20, "Cancel");
      super.buttonList.add(this.cancel);
      this.colorR = new xrayGuiSlider(2, super.width - 160, super.height / 10 * 5, "Red-Value", (float)this.r / 255.0F);
      super.buttonList.add(this.colorR);
      this.colorG = new xrayGuiSlider(3, super.width - 160, super.height / 10 * 6, "Green-Value", (float)this.g / 255.0F);
      super.buttonList.add(this.colorG);
      this.colorB = new xrayGuiSlider(4, super.width - 160, super.height / 10 * 7, "Blue-Value", (float)this.b / 255.0F);
      super.buttonList.add(this.colorB);
      this.colorA = new xrayGuiSlider(5, super.width - 160, super.height / 10 * 8, "Alpha-Value", (float)this.a / 255.0F);
      super.buttonList.add(this.colorA);
      this.isEnabled = new GuiButton(6, super.width - 160, super.height / 10 * 4, 70, 20, this.enabled?"Enabled":"Disabled");
      super.buttonList.add(this.isEnabled);
      this.matterMeta = new GuiButton(7, super.width - 90, super.height / 10 * 4, 80, 20, this.bmeta?"Meta-Check Enabled":"Meta-Check Disabled");
      super.buttonList.add(this.matterMeta);
      Keyboard.enableRepeatEvents(true);
      this.searchbar = new GuiTextField(super.fontRendererObj, 60, 45, 120, super.fontRendererObj.FONT_HEIGHT);
      this.searchbar.setMaxStringLength(30);
      this.searchbar.setCanLoseFocus(false);
      this.searchbar.setFocused(true);
      this.searchbar.setTextColor(16777215);
      this.blocks.addAll(Block.blockRegistry.getKeys());
   }

   public void drawScreen(int x, int y, float par3) {
      super.drawScreen(x, y, par3);
      this.drawBackground(0);
      this.drawString(super.fontRendererObj, "Search for Blocks by their name ", 5, 10, 16777215);
      this.drawString(super.fontRendererObj, "or their ID and meta using @ (e.g. @12:0 or @12:1) ", 7, 20, 16777215);
      String text = this.searchbar.getText();
      int si;
      if(text.startsWith("@")) {
         try {
            text = text.substring(1);
            String[] blockstodraw = text.split(":");
            int s = -1;
            if(blockstodraw.length > 1) {
               s = Integer.parseInt(blockstodraw[1]);
            }

            if(blockstodraw.length > 0) {
               si = Integer.parseInt(blockstodraw[0]);
               if(Block.blockRegistry.containsId(si)) {
                  this.id = Block.blockRegistry.getNameForObject(Block.blockRegistry.getObjectById(si));
                  this.meta = s;
                  if(s != 1) {
                     this.bmeta = true;
                  }
               }
            }
         } catch (Exception var12) {
            ;
         }
      }

      this.add.enabled = this.id != null;
      this.drawInfo();
      this.searchbar.drawTextBox();
      super.drawScreen(x, y, par3);
      ArrayList var13 = this.getItemStackList();
      byte var14 = 9;

      for(si = 0; si < var13.size(); ++si) {
         int ni = si + this.sliderpos * var14;
         if(ni < var13.size()) {
            ItemStack b = (ItemStack)var13.get(ni);
            if(si == var14 * 7) {
               break;
            }

            try {
               RenderHelper.enableGUIStandardItemLighting();
               drawRect(10 + si % var14 * 20, 60 + si / var14 * 20, 10 + si % var14 * 20 + 16, 60 + si / var14 * 20 + 16, -2130706433);
               RenderHelper.disableStandardItemLighting();
               this.drawItem(b, 10 + si % var14 * 20, 60 + si / var14 * 20, "");
            } catch (Exception var11) {
               ;
            }
         }
      }

      RenderHelper.enableGUIStandardItemLighting();
      drawRect(super.width / 3 * 2, super.height / 6, super.width - 30, super.height / 6 * 2, (((int)(this.colorA.percent * 255.0F) << 8 | (int)(this.colorR.percent * 255.0F)) << 8 | (int)(this.colorG.percent * 255.0F)) << 8 | (int)(this.colorB.percent * 255.0F));
      GL11.glDisable(2929);
      xray_AddGui.stringint var15 = this.getClickedBlock(x, y);
      if(var15 != null) {
         this.drawString(super.fontRendererObj, ((Block)Block.blockRegistry.getObject(var15.id)).getLocalizedName(), x - 5, y - 10, 16777215);
      }

      GL11.glEnable(2929);
   }

   public void updateScreen() {
      this.searchbar.updateCursorCounter();
   }

   private void drawInfo() {
      this.drawString(super.fontRendererObj, "Search", 15, 45, 16777215);
      this.drawString(super.fontRendererObj, this.id == null?"No Block selected":((Block)Block.blockRegistry.getObject(this.id)).getLocalizedName(), super.width / 3 * 2 + 20, 20, 16777215);
   }

   private void drawItem(ItemStack itemstack, int x, int y, String name) {
      GL11.glColor3ub((byte)-1, (byte)-1, (byte)-1);
      GL11.glDisable(2896);
      super.zLevel = 200.0F;
      GuiScreen.itemRender.zLevel = 200.0F;
      FontRenderer font = null;
      if(itemstack != null) {
         font = itemstack.getItem().getFontRenderer(itemstack);
      }

      if(font == null) {
         font = super.fontRendererObj;
      }

      GuiScreen.itemRender.renderItemAndEffectIntoGUI(font, this.field_146297_k.getTextureManager(), itemstack, x, y);
      GuiScreen.itemRender.renderItemOverlayIntoGUI(font, this.field_146297_k.getTextureManager(), itemstack, x, y, name);
      super.zLevel = 0.0F;
      GuiScreen.itemRender.zLevel = 0.0F;
      GL11.glEnable(2896);
   }

   protected void keyTyped(char par1, int par2) {
      this.searchbar.textboxKeyTyped(par1, par2);
      this.blocks.clear();
      Set s = Block.blockRegistry.getKeys();
      Iterator var4 = s.iterator();

      while(var4.hasNext()) {
         String string = (String)var4.next();
         String sb = this.searchbar.getText();
         Block b = (Block)Block.blockRegistry.getObject(string);
         if(b.getLocalizedName().toLowerCase().contains(sb.toLowerCase())) {
            this.blocks.add(string);
         }
      }

      this.sliderpos = 0;
      super.keyTyped(par1, par2);
   }

   protected void mouseClicked(int x, int y, int mouseButton) {
      super.mouseClicked(x, y, mouseButton);
      this.searchbar.mouseClicked(x, y, mouseButton);
      if(mouseButton == 0) {
         xray_AddGui.stringint s = this.getClickedBlock(x, y);
         if(s != null) {
            this.id = s.id;
            this.meta = s.meta;
         }

      }
   }

   public void handleMouseInput() {
      super.handleMouseInput();
      int x = Mouse.getEventDWheel();
      ArrayList blockstodraw = this.getItemStackList();
      int xmax = blockstodraw.size() / 9;
      byte xmin = 0;
      if(x < 0) {
         if(this.sliderpos < xmax) {
            ++this.sliderpos;
         }
      } else if(x > 0 && this.sliderpos > xmin) {
         --this.sliderpos;
      }

   }

   private ArrayList getItemStackList() {
      ArrayList blockstodraw = new ArrayList();

      for(int i = 0; i < this.blocks.size(); ++i) {
         Block b = (Block)Block.blockRegistry.getObject((String)this.blocks.get(i));
         b.getSubBlocks((new ItemStack(b)).getItem(), (CreativeTabs)null, blockstodraw);
      }

      return blockstodraw;
   }

   private xray_AddGui.stringint getClickedBlock(int x, int y) {
      int index = 0;
      ArrayList z = new ArrayList();

      int i;
      Block b;
      for(i = 0; i < this.blocks.size(); ++i) {
         b = (Block)Block.blockRegistry.getObject((String)this.blocks.get(i));
         b.getSubBlocks((new ItemStack(b)).getItem(), (CreativeTabs)null, z);
      }

      for(i = 0; i < this.blocks.size(); ++i) {
         b = (Block)Block.blockRegistry.getObject((String)this.blocks.get(i));
         ArrayList blockstodraw = new ArrayList();
         b.getSubBlocks((new ItemStack(b)).getItem(), (CreativeTabs)null, blockstodraw);

         for(int j = 0; j < blockstodraw.size(); ++j) {
            if((index + j) % 9 <= 9 && (index + j - this.sliderpos * 9) / 9 <= 7 && index + j - this.sliderpos * 9 >= 0) {
               int c = (index + j) % 9;
               int v = (index + j - this.sliderpos * 9) / 9;
               if(x > 10 + c * 20 && x < 26 + c * 20 && y > v * 20 + 60 && y < v * 20 + 76) {
                  boolean smeta = false;

                  int var14;
                  try {
                     var14 = ((ItemStack)blockstodraw.get(j)).getItemDamage();
                  } catch (Exception var13) {
                     var14 = -1;
                  }

                  return new xray_AddGui.stringint((String)this.blocks.get(i), var14);
               }
            }
         }

         index += blockstodraw.size();
      }

      return null;
   }

   private class stringint {

      public int meta;
      public String id;


      public stringint(String id, int meta) {
         this.id = id;
         this.meta = meta;
      }
   }
}
