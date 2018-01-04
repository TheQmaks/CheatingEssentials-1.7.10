package qmaks.cheatingessentials.mod.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CELogger 
{
	private Logger log;
	private String pre = "[Cheating Essentials] ";
	
	public CELogger(String s)
	{
	    log = LogManager.getLogger();
	}
	
	public void info(String s)
	{
		log.info(pre+s);
	}

	public void warning(String s)
	{
		log.warn(pre+s);
	}
}
