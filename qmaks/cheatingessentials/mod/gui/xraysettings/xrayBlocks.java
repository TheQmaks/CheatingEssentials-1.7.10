package qmaks.cheatingessentials.mod.gui.xraysettings;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;

public class xrayBlocks {

   public static ArrayList blocks = new ArrayList();
   public int r;
   public int g;
   public int b;
   public int a;
   public int meta;
   public String id = "";
   public boolean enabled = true;


   public xrayBlocks() {}

   public xrayBlocks(int r, int g, int b, int a, int meta, String id, boolean enabled) {
      this.r = r;
      this.g = g;
      this.b = b;
      this.a = a;
      this.id = id;
      this.meta = meta;
      this.enabled = enabled;
   }

   public String toString() {
      return this.r + " " + this.g + " " + this.b + " " + this.a + " " + this.meta + " " + this.id + " " + this.enabled;
   }

   public static xrayBlocks fromString(String s) {
      xrayBlocks result = new xrayBlocks();
      String[] info = s.split(" ");
      result.r = Integer.parseInt(info[0]);
      result.g = Integer.parseInt(info[1]);
      result.b = Integer.parseInt(info[2]);
      result.a = Integer.parseInt(info[3]);
      result.meta = Integer.parseInt(info[4]);
      result.id = info[5];
      result.enabled = Boolean.parseBoolean(info[6]);
      return result;
   }

   public static void setStandardList() {
      ArrayList block = new ArrayList();
      block.add(new xrayBlocks(0, 0, 128, 200, -1, "minecraft:lapis_ore", true));
      block.add(new xrayBlocks(255, 0, 0, 200, -1, "minecraft:redstone_ore", true));
      block.add(new xrayBlocks(255, 255, 0, 200, -1, "minecraft:gold_ore", true));
      block.add(new xrayBlocks(0, 255, 0, 200, -1, "minecraft:emerald_ore", true));
      block.add(new xrayBlocks(0, 191, 255, 200, -1, "minecraft:diamond_ore", true));
      blocks = block;

      try {
         save();
      } catch (IOException var2) {
         var2.printStackTrace();
      }

   }

   public static void removeInvalidBlocks() {
      for(int i = 0; i < blocks.size(); ++i) {
         xrayBlocks block = (xrayBlocks)blocks.get(i);
         if(!Block.blockRegistry.containsKey(block.id)) {
            blocks.remove(block);
         }
      }

   }

   public static void init() {
      try {
         load();
      } catch (Exception var1) {
         var1.printStackTrace();
      }

      removeInvalidBlocks();
      if(blocks.size() == 0) {
         setStandardList();
      }

   }

   private static void load() throws Exception {
      File toLoad = new File(Minecraft.getMinecraft().mcDataDir, "xrayBlocks.dat");
      if(toLoad.exists() && !toLoad.isDirectory()) {
         ArrayList block = new ArrayList();
         BufferedReader br = new BufferedReader(new FileReader(toLoad));

         String s;
         while((s = br.readLine()) != null) {
            block.add(fromString(s));
         }

         br.close();
         blocks = block;
      }

   }

   static void save() throws IOException {
      File toSave = new File(Minecraft.getMinecraft().mcDataDir, "xrayBlocks.dat");
      if(toSave.exists()) {
         toSave.delete();
      }

      BufferedWriter bw = new BufferedWriter(new FileWriter(toSave));

      for(int i = 0; i < blocks.size(); ++i) {
         bw.write(((xrayBlocks)blocks.get(i)).toString());
         bw.newLine();
      }

      bw.flush();
      bw.close();
   }

}
