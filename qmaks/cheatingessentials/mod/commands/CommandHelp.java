package qmaks.cheatingessentials.mod.commands;

import net.minecraft.client.Minecraft;
import qmaks.cheatingessentials.api.command.Command;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class CommandHelp extends Command {

	public CommandHelp() {
		super("help");
	}

	@Override
	public void runCommand(String s, String[] subcommands) {
		int i = 0;
		Wrapper.INSTANCE.addChatMessage("&9█▀▀▀▀▀▀▀▀▀▀▀▀▀█ &bCE Help &9█▀▀▀▀▀▀▀▀▀▀▀▀▀█");
		for(Command commands : CommandManager.commands){
			i++;
			Wrapper.INSTANCE.addChatMessage("&7" + i + ". "+ commands.getCommand() + commands.getDescription());
		}
		Wrapper.INSTANCE.addChatMessage("&9█▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█");
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return " - Show help.";
	}

	@Override
	public String getSyntax() {
		return "&9[&bCE Console&9] &cUsage: " + this.getCommand();
	}
}
