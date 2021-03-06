package qmaks.cheatingessentials.mod.commands;

import net.minecraft.command.ICommandSender;
import qmaks.cheatingessentials.api.command.Command;
import qmaks.cheatingessentials.mod.external.config.forge.GeneralConfiguration;
import qmaks.cheatingessentials.mod.modulesystem.classes.Speed;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ACommandSkypeResolver extends Command {

	private static String username;

	public ACommandSkypeResolver() {
		super("sr");
	}

	@Override
	public void runCommand(String s, String[] subcommands) {
		username = subcommands[0];

		new Thread()
		{
			public void run() {
				try {
					URL url = new URL("http://apionly.com/skype.php?username=" + username);
					HttpURLConnection e = (HttpURLConnection)url.openConnection();
					e.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:19.0) Gecko/20100101 Firefox/19.0");
					e.setRequestMethod("GET");
					e.setConnectTimeout(5000);
					e.connect();
					BufferedReader reader = new BufferedReader(new InputStreamReader(e.getInputStream()));
					String line = reader.readLine();
					if(line.equals("That Skype username could not be resolved.")) {
						Wrapper.INSTANCE.addChatMessage("&9[&bCE Console&9] &fFailed to get IP of the user.");
					} else {
						Wrapper.INSTANCE.addChatMessage("&9[&bCE Console&9] &fUser IP: " + line);
						Wrapper.INSTANCE.copy(line);
						Wrapper.INSTANCE.addChatMessage("&9[&bCE Console&9] &fIP is copied to the clipboard.");
					}
				} catch (MalformedURLException ex) {
					Wrapper.INSTANCE.addChatMessage("&9[&bCE Console&9] &cUnknown error..");
					ex.printStackTrace();
				} catch (IOException ex) {
					Wrapper.INSTANCE.addChatMessage("&9[&bCE Console&9] &cUnknown error..");
					ex.printStackTrace();
				}
			}
		}.start();
	}

	@Override
	public String getDescription() {
		return " <username> - Getting the IP Skype user.";
	}

	@Override
	public String getSyntax() {
		return "&9[&bCE Console&9] &cUsage: " + this.getCommand() + " <username>";
	}
}
