package qmaks.cheatingessentials.mod.commands;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import qmaks.cheatingessentials.api.command.Command;
import qmaks.cheatingessentials.api.module.APICEMod;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class ACommandModuleToggle extends Command
{

	public ACommandModuleToggle() 
	{
		super("mt");
	}

	@Override
	public void runCommand(String s, String[] subcommands)
	{
		for(Mod mod : APICEMod.INSTANCE.mods)
		{
			if(mod.getName().equalsIgnoreCase(subcommands[0].replace(" ", ""))) {
				mod.toggle();
				Wrapper.INSTANCE.addChatMessage("&9[&bCE Console&9] &e" + mod.getName() + " &fis " + (mod.isActive() ? "&aenabled" : "&cdisabled") + "&f.");
			}
	    }
	}

	@Override
	public String getDescription()
	{
		return " <modulename> - Toggles a module.";
	}

	@Override
	public String getSyntax() 
	{
		return "&9[&bCE Console&9] &cUsage: " + this.getCommand() + " <modulename>";
	}

}
