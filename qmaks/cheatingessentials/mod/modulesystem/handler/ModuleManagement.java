package qmaks.cheatingessentials.mod.modulesystem.handler;

import qmaks.cheatingessentials.api.module.APICEMod;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.modulesystem.classes.AimAssist;
import qmaks.cheatingessentials.mod.modulesystem.classes.AimBot;
import qmaks.cheatingessentials.mod.modulesystem.classes.AntiFire;
import qmaks.cheatingessentials.mod.modulesystem.classes.AntiKnockBack;
import qmaks.cheatingessentials.mod.modulesystem.classes.AntiPotion;
import qmaks.cheatingessentials.mod.modulesystem.classes.AutoBlock;
import qmaks.cheatingessentials.mod.modulesystem.classes.AutoTool;
import qmaks.cheatingessentials.mod.modulesystem.classes.BlockOverlay;
import qmaks.cheatingessentials.mod.modulesystem.classes.BlockSmash;
import qmaks.cheatingessentials.mod.modulesystem.classes.Breadcrumb;
import qmaks.cheatingessentials.mod.modulesystem.classes.ChestFinder;
import qmaks.cheatingessentials.mod.modulesystem.classes.ChestStealer;
import qmaks.cheatingessentials.mod.modulesystem.classes.Criticals;
import qmaks.cheatingessentials.mod.modulesystem.classes.DynamicFly;
import qmaks.cheatingessentials.mod.modulesystem.classes.FastBow;
import qmaks.cheatingessentials.mod.modulesystem.classes.FastClick;
import qmaks.cheatingessentials.mod.modulesystem.classes.FastEat;
import qmaks.cheatingessentials.mod.modulesystem.classes.FastPlace;
import qmaks.cheatingessentials.mod.modulesystem.classes.Forcefield;
import qmaks.cheatingessentials.mod.modulesystem.classes.FreeCam;
import qmaks.cheatingessentials.mod.modulesystem.classes.Fullbright;
import qmaks.cheatingessentials.mod.modulesystem.classes.Gui;
import qmaks.cheatingessentials.mod.modulesystem.classes.GuiXRaySettings;
import qmaks.cheatingessentials.mod.modulesystem.classes.KillAura;
import qmaks.cheatingessentials.mod.modulesystem.classes.MobAura;
import qmaks.cheatingessentials.mod.modulesystem.classes.MobESP;
import qmaks.cheatingessentials.mod.modulesystem.classes.NCPFly;
import qmaks.cheatingessentials.mod.modulesystem.classes.NCPSpeed;
import qmaks.cheatingessentials.mod.modulesystem.classes.NCPStep;
import qmaks.cheatingessentials.mod.modulesystem.classes.NameProtect;
import qmaks.cheatingessentials.mod.modulesystem.classes.NoFall;
import qmaks.cheatingessentials.mod.modulesystem.classes.NoWeather;
import qmaks.cheatingessentials.mod.modulesystem.classes.NoWeb;
import qmaks.cheatingessentials.mod.modulesystem.classes.Nuker;
import qmaks.cheatingessentials.mod.modulesystem.classes.PlayerESP;
import qmaks.cheatingessentials.mod.modulesystem.classes.Projectiles;
import qmaks.cheatingessentials.mod.modulesystem.classes.ProphuntAura;
import qmaks.cheatingessentials.mod.modulesystem.classes.ProphuntESP;
import qmaks.cheatingessentials.mod.modulesystem.classes.Regen;
import qmaks.cheatingessentials.mod.modulesystem.classes.SeeHealth;
import qmaks.cheatingessentials.mod.modulesystem.classes.Speed;
import qmaks.cheatingessentials.mod.modulesystem.classes.Sprint;
import qmaks.cheatingessentials.mod.modulesystem.classes.Step;
import qmaks.cheatingessentials.mod.modulesystem.classes.Tracers;
import qmaks.cheatingessentials.mod.modulesystem.classes.TriggerBot;
import qmaks.cheatingessentials.mod.modulesystem.classes.WaterFall;
import qmaks.cheatingessentials.mod.modulesystem.classes.WaterWalk;
import qmaks.cheatingessentials.mod.modulesystem.classes.XRay;

public class ModuleManagement 
{
	public volatile static ModuleManagement INSTANCE = new ModuleManagement();
	
	public ModuleManagement()
	{
		initModules();
	}
	
	private void add(Mod mod)
	{
		APICEMod.INSTANCE.enable(mod);
	}
	
	public void initModules()
	{
		//Player
		add(new Step());
		add(new Speed());
		add(new NoWeb());
		add(new Regen());
		add(new Nuker());
		add(new Sprint());
		add(new NoFall());
		add(new FreeCam());
		add(new FastEat());
		add(new AntiFire());
		add(new AutoTool());
		add(new FastPlace());
		add(new AntiPotion());
		add(new DynamicFly());
		add(new ChestStealer());
		add(new AntiKnockBack());
		
		//Render
		add(new XRay());
		add(new MobESP());
		add(new Tracers());
		add(new PlayerESP());
		add(new NoWeather());
		add(new BlockSmash());
		add(new Fullbright());
		add(new Breadcrumb());
		add(new NameProtect());
		add(new Projectiles());
		add(new ChestFinder());
		add(new BlockOverlay());
		
		//Combat
		add(new AimBot());
		add(new FastBow());
		add(new MobAura());
		add(new KillAura());
		add(new AimAssist());
		add(new Criticals());
		add(new FastClick());
		add(new AutoBlock());
		add(new SeeHealth());
		add(new TriggerBot());
		add(new Forcefield());
		
		//Minigames
		add(new ProphuntESP());
		add(new ProphuntAura());
		
		//NoCheatPlus
		add(new NCPFly());
		add(new NCPStep());
		add(new NCPSpeed());
		add(new WaterFall());
		add(new WaterWalk());
		
		//GUI
		add(new Gui());
		add(new GuiXRaySettings());
	}
	
	public static ModuleManagement instance()
	{
		return INSTANCE;
	}
}
