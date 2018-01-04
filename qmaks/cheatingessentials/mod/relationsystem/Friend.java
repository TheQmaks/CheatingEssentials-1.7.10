package qmaks.cheatingessentials.mod.relationsystem;

import java.util.concurrent.CopyOnWriteArrayList;

import qmaks.cheatingessentials.mod.wrapper.Wrapper;

/**
 * Friend category class. The {@link #friendList} should be readed by AGCEList (Due to it's state
 * as a String list that saves the player names). The names needs to be get from {@link GameProfile}
 * class since "username" in Player classes doesn't longer exists and {@link Minecraft#getMinecraft()#session}
 * is private and can't be accessed without using reflection.
 */
public class Friend
{
	private static volatile Friend INSTANCE = new Friend();
	public static CopyOnWriteArrayList<String> friendList = new CopyOnWriteArrayList<String>();
	
	public void addFriend(String string)
	{
		if(!friendList.contains(string))
		{
			friendList.add(string);
			Wrapper.INSTANCE.addChatMessage("&9[&bCE Friend&9] &cPlayer &a" + string + " &chas &cbeen &cadded &cto &cyour &cfriends &clist.");
		} else {
			Wrapper.INSTANCE.addChatMessage("&9[&bCE Friend&9] &cPlayer &a" + string + " &cit &cis &calready &cyour &cfriend.");
		}
	}
	
	public void removeFriend(String string)
	{
		friendList.remove(string);
		Wrapper.INSTANCE.addChatMessage("&9[&bCE Friend&9] &cPlayer &a" + string + " &chas &cbeen &cremoved &cfrom &cyour &cfriends &clist.");
	}
	
	public boolean readFriend(String string)
	{
		if(friendList.contains(string))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static Friend instance()
	{
		return INSTANCE;
	}
}
