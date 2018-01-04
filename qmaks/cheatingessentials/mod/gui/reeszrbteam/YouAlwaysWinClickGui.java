package qmaks.cheatingessentials.mod.gui.reeszrbteam;

import java.util.ArrayList;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ChatAllowedCharacters;
import qmaks.cheatingessentials.api.command.Command;
import qmaks.cheatingessentials.mod.commands.CommandManager;
import qmaks.cheatingessentials.mod.external.config.manual.ModuleStateConfiguration;
import qmaks.cheatingessentials.mod.external.config.manual.SaveableGuiState;
import qmaks.cheatingessentials.mod.gui.reeszrbteam.element.YAWWindow;
import qmaks.cheatingessentials.mod.gui.reeszrbteam.window.WindowActives;
import qmaks.cheatingessentials.mod.gui.reeszrbteam.window.WindowCombat;
import qmaks.cheatingessentials.mod.gui.reeszrbteam.window.WindowHub;
import qmaks.cheatingessentials.mod.gui.reeszrbteam.window.WindowInfo;
import qmaks.cheatingessentials.mod.gui.reeszrbteam.window.WindowMinigames;
import qmaks.cheatingessentials.mod.gui.reeszrbteam.window.WindowNoCheatPlus;
import qmaks.cheatingessentials.mod.gui.reeszrbteam.window.WindowPlayer;
import qmaks.cheatingessentials.mod.gui.reeszrbteam.window.WindowRadar;
import qmaks.cheatingessentials.mod.gui.reeszrbteam.window.WindowRender;
import qmaks.cheatingessentials.mod.util.GLUtils;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import scala.Console;

public class YouAlwaysWinClickGui extends GuiScreen
{
	public static ArrayList<YAWWindow> windows = new ArrayList<YAWWindow>();
	public static ArrayList<YAWWindow> unFocusedWindows = new ArrayList<YAWWindow>();
	public static boolean isDark = false;
	ArrayList cmds = new ArrayList();
	public static ArrayList OrgName = new ArrayList();
	public static ArrayList NameP = new ArrayList();
	protected String message = "";
	private int updateCounter = 0;
	private static final char[] allowedCharacters = ChatAllowedCharacters.allowedCharacters;
    private boolean var6 = false;
    
	public YAWWindow guiHub = new WindowHub();
	
    public YouAlwaysWinClickGui()
    {
    	this.initWindows();
    	this.cmds.clear();
	    for(Command c : CommandManager.commands)
	    {
	    	this.cmds.add(c.getCommand() + " - " + c.getDescription());
	    }
    }
    
    /**
     * If here you can start recognizing the fucking modules I'll be happy.
     */
    public void initWindows()
    {
    	new WindowPlayer().init();
    	new WindowCombat().init();
    	new WindowRender().init();
    	new WindowMinigames().init();
    	new WindowNoCheatPlus().init();
    	new WindowInfo();
    	new WindowRadar();
    	new WindowActives();
    }
    
	public void initGui()
	{
		super.initGui();
		SaveableGuiState.instance().read();
        Keyboard.enableRepeatEvents(true);
		guiHub.setOpen(true);
	}
	
	public void onGuiClosed()
	{
		super.onGuiClosed();
    	this.message = "";
        Keyboard.enableRepeatEvents(false);
		ModuleStateConfiguration.instance().writeToFile();
		SaveableGuiState.instance().writeToFile();
	}
	
	public static void sendPanelToFront(YAWWindow window)
	{
		if(windows.contains(window))
		{
			int panelIndex = windows.indexOf(window);
			windows.remove(panelIndex);
			windows.add(windows.size(), window);
		}
	}
	
	public static YAWWindow getFocusedPanel()
	{
		return windows.get(windows.size() - 1);
	}
	
	/**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char var1, int var2)
    {
        String var3;
 
        if (var1 == 22)
        {
            var3 = GuiScreen.getClipboardString();
 
            if (var3 != null)
            {
            	this.message = this.message + var3;
            }
        }
        
        if (var2 == 1)
        {
             Wrapper.INSTANCE.mc().setIngameFocus();
             //Wrapper.INSTANCE.minecraft().displayGuiScreen(this);
        }
        else if (var2 == 28)
        {
            var3 = this.message.trim();
            String[] var4;
            String var5;
            
            try
            {
                CommandManager.instance().runCommands("." + var3);
            }
            catch(Exception e)
            {
            	for(Command command : CommandManager.commands)
            	{
            		if(message.contains(command.getCommand()))
            		{
                		Wrapper.INSTANCE.addChatMessage(command.getSyntax());
            		}
            	}
            }
            
            message = "";
        }
        else
        {
            if (var2 == 14 && this.message.length() > 0)
            {
                this.message = this.message.substring(0, this.message.length() - 1);
            }
 
            if (String.valueOf(allowedCharacters).indexOf(var1) >= 0 || this.message.length() < 100 && var2 != 14)
            {                
                this.message = this.message + var1;
            }
        }
    }

	
	public void drawScreen(int x, int y, float f)
	{
		super.drawScreen(x, y, f);
        int var4 = 24;
        
        for (int var5 = 0; var5 < this.cmds.size(); ++var5)
        {
            String var6 = (String)this.cmds.get(var5);
 
            if (var6.startsWith(this.message) && this.message.length() > 0)
            {
                GLUtils.drawBorderedRect(83 + 4, var4 - 6,83 + Wrapper.INSTANCE.fontRenderer().getStringWidth(var6) + 8, var4 + 6, 1, -15066598, -14145496);
                
                this.drawString(Wrapper.INSTANCE.fontRenderer(), var6,83 + 6, var4 - 4, 16777215);
                var4 += 14;
            }
        }
 
        GLUtils.drawBorderedRect(83 + 4, 0, this.width - 60, 12, 1, -15066598, -14145496);
		Wrapper.INSTANCE.fontRenderer().drawStringWithShadow("Made by Qmaks", 2, 2, 16777215);
		Wrapper.INSTANCE.fontRenderer().drawString("CE Console ",  83 + 10, 2, 0x55FFFF);
        
        this.drawString(Wrapper.INSTANCE.fontRenderer(), "" + (this.updateCounter / 12 % 2 != 0 ? "\u00a7b>\u00a77 " : "\u00a73>\u00a77 ") + this.message + (this.updateCounter / 12 % 2 != 0 ? " \u00a7b_" : " \u00a73_"), Wrapper.INSTANCE.fontRenderer().getStringWidth("CE Console ") + 83 + 10, 2, 14737632);
		
        for(YAWWindow window: windows)
		{
			window.draw(x, y);
		}
	}
		
    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        ++this.updateCounter;
        super.updateScreen();
    }
    
	public void mouseClicked(int x, int y, int button)
	{
		try
		{
			for(YAWWindow window: windows)
			{
				window.mouseClicked(x, y, button);
			}
		}
		catch(Exception e) {}
	}
	
    protected void func_146286_b(int p_146286_1_, int p_146286_2_, int p_146286_3_)
	{
		for(YAWWindow window: windows)
		{
			window.mouseMovedOrUp(p_146286_1_, p_146286_2_, p_146286_3_);
		}
	}
	
	public boolean doesGuiPauseGame()
	{
		return false;
	}
}
