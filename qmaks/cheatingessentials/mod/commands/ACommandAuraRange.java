package qmaks.cheatingessentials.mod.commands;

import net.minecraft.command.ICommandSender;
import qmaks.cheatingessentials.api.command.Command;
import qmaks.cheatingessentials.mod.external.config.forge.GeneralConfiguration;
import qmaks.cheatingessentials.mod.modulesystem.classes.Speed;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class ACommandAuraRange extends Command {

	public static float aurarange = 4.6F;

	public ACommandAuraRange() {
		super("aurarange");
	}

	@Override
	public void runCommand(String s, String[] subcommands) {
		aurarange = Integer.parseInt(subcommands[0]);
		GeneralConfiguration.instance().configuration.save();
		GeneralConfiguration.instance().configuration.load();
		Wrapper.INSTANCE.addChatMessage("&9[&bCE Console&9] &fThe range is set to &a" + aurarange);
	}

	@Override
	public String getDescription() {
		return " <range> - Sets aura range.";
	}

	@Override
	public String getSyntax() {
		return "&9[&bCE Console&9] &cUsage: " + this.getCommand() + " <range>";
	}

}
