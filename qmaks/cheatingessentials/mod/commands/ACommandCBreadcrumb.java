package qmaks.cheatingessentials.mod.commands;

import net.minecraft.client.Minecraft;
import qmaks.cheatingessentials.api.command.Command;
import qmaks.cheatingessentials.mod.modulesystem.classes.Breadcrumb;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class ACommandCBreadcrumb extends Command {

	public ACommandCBreadcrumb() {
		super("bc");
	}

	@Override
	public void runCommand(String s, String[] subcommands) {
		Breadcrumb.positionsList.clear();
		Wrapper.INSTANCE.addChatMessage("&9[&bCE Console&9] &fCleared Breadcrumbs.");
	}

	@Override
	public String getDescription() {
		return " - Clear breadcrumbs.";
	}

	@Override
	public String getSyntax() {
		return "&9[&bCE Console&9] &cUsage: " + this.getCommand();
	}
}
