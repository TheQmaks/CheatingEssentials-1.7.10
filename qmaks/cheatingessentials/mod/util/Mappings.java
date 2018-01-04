package qmaks.cheatingessentials.mod.util;

import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.Minecraft;

public class Mappings {
	public static String timer = isMCP() ? "timer" : "field_71428_T";
	public static String isInWeb = isMCP() ? "isInWeb" : "field_70134_J";
	public static String registerReloadListener = isMCP() ? "registerReloadListener" : "func_110542_a";

	private static boolean isMCP() {
		try { 
			if(ReflectionHelper.findField(Minecraft.class, new String[] {"theMinecraft"}) != null) {
				return true;
			}
			return false;
		} catch(Exception ex) {
            return false;
		}
	}
}