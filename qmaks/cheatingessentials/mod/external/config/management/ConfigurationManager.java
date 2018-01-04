package qmaks.cheatingessentials.mod.external.config.management;

import qmaks.cheatingessentials.mod.external.config.manual.KeybindConfiguration;
import qmaks.cheatingessentials.mod.external.config.manual.ModuleStateConfiguration;
import qmaks.cheatingessentials.mod.external.config.manual.SaveableGuiState;
import qmaks.cheatingessentials.mod.main.CheatingEssentials;
import qmaks.cheatingessentials.mod.relationsystem.Friend;

/**
 * Configuration Management for AGCE System (I don't remember why I called it AGCE but anyway). This should
 * call any configuration that needs to be written/readed in the constructor. The current categories of AGCE
 * Configs are {@link AGCEGeneric}, {@link AGCEIntegerList} and {@link AGCEStringList}. This can handle manual and more
 * complex configurations like the Module State or the Keybinding one.
 * @author Kodehawa
 */
public class ConfigurationManager 
{
	private static volatile ConfigurationManager INSTANCE = new ConfigurationManager();
	
	public ConfigurationManager()
	{
		CheatingEssentials.INSTANCE.logger.info("Automated Cheating Essentials Configuration System initializing...");
		
		new KeybindConfiguration();
		new ModuleStateConfiguration();
		new SaveableGuiState();
		
		CheatingEssentials.INSTANCE.logger.info("Automated Cheating Essentials Configuration System initialized.");
	}
	
	public static ConfigurationManager instance()
	{
		return INSTANCE;
	}

}
