package qmaks.cheatingessentials.api.command;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class APICommand 
{

	public static ArrayList<Command> commands = new ArrayList<Command>();
	private static volatile APICommand instance = new APICommand();
	
	private APICommand(){}

	public void addCommandToCE(Command command)
	{
		if(!commands.contains(command))
		{
			commands.add(command);
		}
	}
	
	public List getCommandList()
	{
		return Collections.unmodifiableList(commands);
	}
	
	public static APICommand instance()
	{
		return instance;
	}
}
