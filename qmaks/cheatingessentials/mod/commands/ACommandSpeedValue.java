package qmaks.cheatingessentials.mod.commands;

import net.minecraft.command.ICommandSender;
import qmaks.cheatingessentials.api.command.Command;
import qmaks.cheatingessentials.mod.external.config.forge.GeneralConfiguration;
import qmaks.cheatingessentials.mod.modulesystem.classes.Speed;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class ACommandSpeedValue extends Command {

	public static float SPEED_VALUE = 2.0F;
	
	public ACommandSpeedValue() {
		super("sh");
	}

	@Override
	public void runCommand(String s, String[] subcommands) {
		SPEED_VALUE = Float.parseFloat(subcommands[0]);
		GeneralConfiguration.instance().configuration.save();
		GeneralConfiguration.instance().configuration.load();
		Wrapper.INSTANCE.addChatMessage("&9[&bCE Console&9] &fThe speed is set to &a" + SPEED_VALUE);
	}

	@Override
	public String getDescription() {
		return " <speed> - Sets player speed.";
	}

	@Override
	public String getSyntax() {
		return "&9[&bCE Console&9] &cUsage: " + this.getCommand() + " <speed>";
	}
}
