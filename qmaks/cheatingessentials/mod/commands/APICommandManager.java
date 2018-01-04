package qmaks.cheatingessentials.mod.commands;

public class APICommandManager {

	public static void addCommands(){
		CommandManager.instance();
		CommandManager.instance().addCommand(new ACommandModuleList());
		CommandManager.instance().addCommand(new ACommandCBreadcrumb());
		CommandManager.instance().addCommand(new ACommandStepHeight());
		CommandManager.instance().addCommand(new ACommandSpeedValue());
		CommandManager.instance().addCommand(new ACommandServerIP());
		CommandManager.instance().addCommand(new ACommandModuleToggle());
		CommandManager.instance().addCommand(new ACommandAuraRange());
		CommandManager.instance().addCommand(new ACommandSkypeResolver());
		CommandManager.instance().addCommand(new ACommandBind());
		CommandManager.instance().addCommand(new ACommandFriend());
	}
}
