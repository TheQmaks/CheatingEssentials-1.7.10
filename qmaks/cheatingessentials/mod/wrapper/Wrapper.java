package qmaks.cheatingessentials.mod.wrapper;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class Wrapper 
{
	public volatile static Wrapper INSTANCE = new Wrapper();
	
	public Minecraft mc()
	{
		return Minecraft.getMinecraft();
	}

	public EntityClientPlayerMP player()
	{
		return Minecraft.getMinecraft().thePlayer;
	}
	
	public WorldClient world()
	{
		return Minecraft.getMinecraft().theWorld;
	}
	
	public GameSettings mcSettings()
	{
		return Minecraft.getMinecraft().gameSettings;
	}
	
	public FontRenderer fontRenderer()
	{
		return Minecraft.getMinecraft().fontRenderer;
	}
	
	public void addChatMessage(String tosend)
	{
		IChatComponent chatcomponent = new ChatComponentText(tosend.replace("&", "\u00a7"));
		player().addChatMessage(chatcomponent);
	}
	
	public void copy(String str) {
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(str), null);
	}
	
	public boolean classExists(String className) {
	    try  {
	        Class.forName(className);
	        return true;
	    }  catch (final ClassNotFoundException e) {
	        return false;
	    }
	}
}
