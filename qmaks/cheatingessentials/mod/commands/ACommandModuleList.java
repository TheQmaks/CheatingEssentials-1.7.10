package qmaks.cheatingessentials.mod.commands;

import net.minecraft.client.Minecraft;
import qmaks.cheatingessentials.api.command.Command;
import qmaks.cheatingessentials.api.module.APICEMod;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

import org.lwjgl.input.Keyboard;

public class ACommandModuleList extends Command {

	public ACommandModuleList() {
		super("ml");
	}

	@Override
	public void runCommand(String s, String[] subcommands) {
		int i = 0;
		Wrapper.INSTANCE.addChatMessage("&9█▀▀▀▀▀▀▀█ &bModule List &9█▀▀▀▀▀▀▀█");
		for(Mod module : APICEMod.INSTANCE.mods){
			i++;
			Wrapper.INSTANCE.addChatMessage("&7" + i + ". " + module.getName() + " - Key: ["+Keyboard.getKeyName(module.getKeybind())+"]");
		}
		Wrapper.INSTANCE.addChatMessage("&9█▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█");
	}

	@Override
	public String getDescription() {
		return " - List all modules.";
	}

	@Override
	public String getSyntax() {
		return "&9[&bCE Console&9] &cUsage: " + this.getCommand();
	}

}
