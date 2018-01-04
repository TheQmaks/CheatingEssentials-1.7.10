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
import qmaks.cheatingessentials.api.module.APICEMod;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.main.CheatingEssentials;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;

public class ModuleStateConfiguration {

	private volatile static ModuleStateConfiguration instance = new ModuleStateConfiguration();
	private File moduleConfig = new File(Minecraft.getMinecraft().mcDataDir, "/config/Cheating Essentials/CEModuleStatus.txt");

	public ModuleStateConfiguration()
	{
		this.write();
	}
	
	public void writeToFile()
	{
		try
		{
			FileWriter filewriter = new FileWriter(moduleConfig);
			BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
			for(Mod module : APICEMod.INSTANCE.mods){
				Boolean s = module.isActive();
				if(module.getCategory() != ModuleCategories.NONE){
				    bufferedwriter.write(module.getName().toLowerCase().replaceAll(" ", "") + ":" + s +"\r\n");
				}
		    }
			bufferedwriter.close();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
	
	@SuppressWarnings("resource")
	public void read()
	{
		CheatingEssentials.INSTANCE.logger.info("Reading module config file...");
		try
		{
			FileInputStream inputstream = new FileInputStream(moduleConfig.getAbsolutePath());
			DataInputStream datastream = new DataInputStream(inputstream);
			BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
			String string;
			while((string = bufferedreader.readLine()) != null)
			{
				String line = string.trim();
				String[] string2 = string.split(":");
				String moduleName = string2[0];
				String booleanState = string2[1];
				for(Mod module : APICEMod.INSTANCE.mods)
				{
					if(module.getCategory() != ModuleCategories.NONE)
					{
						List<String> modules = Arrays.asList(module.getName());
						for(int i = 0; i < modules.size(); ++i){
							if(moduleName.equalsIgnoreCase(modules.get(i).toLowerCase().replaceAll(" ", "")))
							{
								if(booleanState.equalsIgnoreCase("true"))
								{
									try
									{
										module.on();
									}
									catch(Exception e)
									{
										e.printStackTrace();
									}
								}
							}
						}
					}
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	private void write()
	{
		if(!moduleConfig.exists())
		{
			moduleConfig.getParentFile().mkdirs();
			try
			{
				moduleConfig.createNewFile();
				this.writeToFile();
			}
			catch(IOException e){}
		}
	}
	
	public static ModuleStateConfiguration instance(){
		return instance;
	}
}
