package qmaks.cheatingessentials.api.module;

import org.lwjgl.input.Keyboard;

import qmaks.cheatingessentials.mod.main.CheatingEssentials;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;

public abstract class Mod 
{
	
	public Mod(ModuleCategories category)
	{
		this.category = category;
		CheatingEssentials.INSTANCE.logger.info("Loaded: " + getName() + " ("+getAlias()+")" + " Category: "+ getCategory());
	}
	
	protected String name = "unknown";
	protected String p = "cheatingessentials:";
	protected String description = "unknown";
	protected int keybind = Keyboard.KEY_NONE;
	protected boolean enabled;
	protected ModuleCategories category;

	public void setKeybinding(int key)
	{
		this.keybind = key;
	}
	
	public void setCategory(ModuleCategories category)
	{
		this.category = category;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getAlias()
	{
		return p+getName().toLowerCase().replaceAll(" ", "");
	}
	
	public int getKeybind()
	{
		return keybind;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public ModuleCategories getCategory()
	{
		return category;
	}
	
	public boolean isActive()
	{
		return enabled;
	}

	public void onAttackEntity(){}
	public void onPlayerUpdate(){}
	public void onWorldUpdate(){}
	public void onTick(){}
	public void onTicks(){}
	public void onWorldRender(){}
	public void onEnableMod(){}
	public void onDisableMod(){}
	
	public void reset()
	{
		onEnableMod();
		onDisableMod();
	}
	
	public void on()
	{
		enabled = true;
		onEnableMod();
		APICEMod.INSTANCE.addActive(this);
	}
	
	public void off()
	{
		enabled = false;
		onDisableMod();
		APICEMod.INSTANCE.removeActive(this);
	}
	
	/**
	 * Toggles the specified module
	 */
	public void toggle()
	{
		enabled = !enabled;
		
		if(isActive())
		{
			onEnableMod();
			APICEMod.INSTANCE.addActive(this);
		}
		else
		{
			onDisableMod();
			APICEMod.INSTANCE.removeActive(this);
		}
	}
}
