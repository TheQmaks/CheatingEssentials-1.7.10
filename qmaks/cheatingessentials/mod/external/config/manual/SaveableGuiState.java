package qmaks.cheatingessentials.mod.external.config.manual;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import net.minecraft.client.Minecraft;
import qmaks.cheatingessentials.mod.gui.reeszrbteam.YouAlwaysWinClickGui;
import qmaks.cheatingessentials.mod.gui.reeszrbteam.element.YAWWindow;

public class SaveableGuiState {

	private File guiConfig = new File(Minecraft.getMinecraft().mcDataDir, "/config/Cheating Essentials/TGC.txt");
	private volatile static SaveableGuiState inst = new SaveableGuiState();
	
	public SaveableGuiState()
	{
		write();
	}

	public void writeToFile()
	{
		try
		{
			FileWriter filewriter = new FileWriter(guiConfig);
			BufferedWriter buffered = new BufferedWriter(filewriter);
			for(YAWWindow window : YouAlwaysWinClickGui.unFocusedWindows)
			{
				int x = window.lastDragX;
				int y = window.lastDragY;
				boolean open = window.isOpen();
				boolean extended = window.isExtended();
				
				buffered.write(window.getTitle().toLowerCase().replaceAll(" ", "")+":"+x+":"+y+":"+open+":"+extended+"\r\n");
			}
			
			buffered.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void read()
	{
		try
		{
			FileInputStream input = new FileInputStream(guiConfig.getAbsolutePath());
			DataInputStream data = new DataInputStream(input);
			@SuppressWarnings("resource")
			BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(data));
			String x;
			while((x = bufferedreader.readLine()) != null)
			{
				
				String line = x.trim();
				String[] array = x.split(":");
				//List them all!
				String window = array[0];
				String xPos = array[1];
				String yPos = array[2];
				String open = array[3];
				String extended = array[4];
				
				for(YAWWindow windows : YouAlwaysWinClickGui.unFocusedWindows)
				{
					
					List<String> windowsN = Arrays.asList(windows.getTitle());
					
					for(int i = 0; i < windowsN.size(); ++i)
					{
						if(window.equalsIgnoreCase(windowsN.get(i).toLowerCase().replaceAll(" ", "")))
						{
							//windows.xPos = Integer.parseInt(xPos);
							//windows.yPos = Integer.parseInt(yPos);
							windows.setOpen(Boolean.parseBoolean(open));
							windows.setExtended(Boolean.parseBoolean(extended));
						}
					}
				}
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void write()
	{
		if(!guiConfig.exists())
		{
			guiConfig.getParentFile().mkdirs();
			try
			{
				guiConfig.createNewFile();
				this.writeToFile();
			}
			catch(IOException ex)
			{}
		}
	}
	
	public static SaveableGuiState instance()
	{
		return inst;
	}
}
