package qmaks.cheatingessentials.mod.commands;

import org.lwjgl.input.Keyboard;

import qmaks.cheatingessentials.api.command.Command;
import qmaks.cheatingessentials.api.module.APICEMod;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.external.config.manual.KeybindConfiguration;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class ACommandBind extends Command {

	public ACommandBind() {
		super("bind");
	}

	@Override
	public void runCommand(String s, String[] subcommands) {
		boolean successful = false;
		if(subcommands[0].equalsIgnoreCase("add")){
			for(Mod m : APICEMod.INSTANCE.mods){
				if(subcommands[1].equalsIgnoreCase(m.getName().replaceAll(" ", ""))){
					int i = Keyboard.getKeyIndex(subcommands[2].toUpperCase());
					if(i != Keyboard.KEY_NONE){
						successful = true;
						m.setKeybinding(i);
						Wrapper.INSTANCE.addChatMessage("&9[&bCE Console&9] &fKey &c" + m.getKeybind() + "[" + subcommands[2].toUpperCase()+ "]" + " &fbinds to &c" + m.getName());
						KeybindConfiguration.instance().writeKeybindConfig(); 
					}
				}
			}
		}

		if(subcommands[0].equalsIgnoreCase("del")){
			for(Mod module : APICEMod.INSTANCE.mods){
				if(subcommands[1].equalsIgnoreCase(module.getName().replaceAll(" ", ""))){
					successful = true;
					module.setKeybinding(0);
					Wrapper.INSTANCE.addChatMessage("&9[&bCE Console&9] &fBind removed from &c" + module.getName() + " module.");
					KeybindConfiguration.instance().writeKeybindConfig();
				}
			}
		}
		if(!successful){
			Wrapper.INSTANCE.addChatMessage(getSyntax());
		}
	}

	@Override
	public String getDescription() {
		return " <add/del> <module> <key> - Change module keys.";
	}

	@Override
	public String getSyntax() {
		return "&9[&bCE Console&9] &cUsage: " + this.getCommand() + " <add/del> <module> <key>";
	}

}
