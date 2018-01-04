package qmaks.cheatingessentials.mod.gui.xraysettings;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import org.lwjgl.opengl.GL11;

public class xrayGuiSlider extends GuiButton {

   public float percent;
   public boolean isClicked;


   public xrayGuiSlider(int id, int x, int y, String name, float percentage) {
      super(id, x, y, 150, 20, name);
      this.percent = percentage;
   }

   public int getHoverState(boolean p_146114_1_) {
      return 0;
   }

   protected void mouseDragged(Minecraft p_146119_1_, int p_146119_2_, int p_146119_3_) {
      if(super.visible) {
         if(this.isClicked) {
            this.percent = (float)(p_146119_2_ - (super.xPosition + 4)) / (float)(super.width - 8);
            if(this.percent < 0.0F) {
               this.percent = 0.0F;
            }

            if(this.percent > 1.0F) {
               this.percent = 1.0F;
            }
         }

         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.drawTexturedModalRect(super.xPosition + (int)(this.percent * (float)(super.width - 8)), super.yPosition, 0, 66, 4, 20);
         this.drawTexturedModalRect(super.xPosition + (int)(this.percent * (float)(super.width - 8)) + 4, super.yPosition, 196, 66, 4, 20);
      }

   }

   public boolean mousePressed(Minecraft p_146116_1_, int x, int y) {
      if(super.mousePressed(p_146116_1_, x, y)) {
         this.percent = (float)(x - (super.xPosition + 4)) / (float)(super.width - 8);
         if(this.percent < 0.0F) {
            this.percent = 0.0F;
         }

         if(this.percent > 1.0F) {
            this.percent = 1.0F;
         }

         this.isClicked = true;
         return true;
      } else {
         return false;
      }
   }

   public void mouseReleased(int x, int y) {
      this.isClicked = false;
   }
}
