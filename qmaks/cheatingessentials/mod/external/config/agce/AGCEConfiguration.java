package qmaks.cheatingessentials.mod.external.config.agce;

import java.io.File;

import qmaks.cheatingessentials.mod.wrapper.Wrapper;

/**
 * Standard Cheating Essentials configuration file creator. This help the other AGCE classes to exist and
 * prevents set variables in each one of them.
 * @author Kodehawa
 */
public class AGCEConfiguration
{

	protected Object obj;
	protected Object newobj;
	protected String name;
	protected File file;
	protected String path;
	
	protected void createFile()
	{
		if(!file.exists())
		{
			file.getParentFile().mkdirs();
			try
			{
				file.createNewFile();
				create(file, obj);
			}
			catch(Exception e)
			{}
		}
	}
	
	public void modify(String fileName, Object o)
	{
		this.path = fileName;
		this.file = new File(Wrapper.INSTANCE.mc().mcDataDir, "/config/Cheating-Essentials/"+path);
		create(file, o);
	}
	
	protected void create(File file, Object obj){}
	
	protected void read(){}

}
