package qmaks.cheatingessentials.mod.commands;

import java.io.File;

import net.minecraft.client.Minecraft;
import qmaks.cheatingessentials.api.command.Command;
import qmaks.cheatingessentials.mod.external.config.agce.AGCEConfiguration;
import qmaks.cheatingessentials.mod.relationsystem.Friend;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class ACommandFriend extends Command {

	public ACommandFriend() {
		super("friend");
	}

	@Override
	public void runCommand(String s, String[] subcommands) {
		if(subcommands[0].equalsIgnoreCase("add")){
			Friend.instance().addFriend(subcommands[1]);
		}
		if(subcommands[0].equalsIgnoreCase("del")){
			Friend.instance().removeFriend(subcommands[1]);
		}
	}

	@Override
	public String getDescription() {
		return " <add/del> <nickname> - Manage list of friends.";
	}

	@Override
	public String getSyntax() {
		return "&9[&bCE Console&9] &cUsage: " + this.getCommand() + " <add/del> <nickname>";
	}

}
