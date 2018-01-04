package qmaks.cheatingessentials.mod.modulesystem.classes;

import java.lang.reflect.Method;

import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.Minecraft;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class FastPlace extends Mod {
	
	public FastPlace()
	{
		super(ModuleCategories.PLAYER);
	}
	
	@Override
	public String getName(){
		return "FastPlace";
	}
	
    @Override
	public void onTicks() {
      if (Wrapper.INSTANCE.mc().gameSettings.keyBindUseItem.getIsKeyPressed()) {
		try {
			Method m = ReflectionHelper.findMethod(Minecraft.class, Wrapper.INSTANCE.mc(), new String[]{"func_147121_ag"}, null);
			m.invoke(Minecraft.getMinecraft());
    	} catch (Exception e) {
		  e.printStackTrace();
       }
    }
  }
}
