package qmaks.cheatingessentials.mod.commands;

import qmaks.cheatingessentials.api.command.Command;
import qmaks.cheatingessentials.mod.external.config.forge.GeneralConfiguration;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class ACommandStepHeight extends Command {

	public static float STEP_HEIGHT_VALUE = 1.0F;
	
	public ACommandStepHeight() {
		super("step");
	}

	@Override
	public void runCommand(String s, String[] subcommands) {
		STEP_HEIGHT_VALUE = Float.parseFloat(subcommands[0]);
		GeneralConfiguration.instance().configuration.save();
		GeneralConfiguration.instance().configuration.load();
		Wrapper.INSTANCE.addChatMessage("&9[&bCE Console&9] &fThe step height is set to &a" + STEP_HEIGHT_VALUE);
	}

	@Override
	public String getDescription() {
		return " <height> - Sets step height.";
	}

	@Override
	public String getSyntax() {
		return "&9[&bCE Console&9] &cUsage: " + this.getCommand() + " <height>";
	}
}
