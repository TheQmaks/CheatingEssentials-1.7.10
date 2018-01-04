package qmaks.cheatingessentials.mod.modulesystem.classes;

import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FastClick extends Mod {

	public FastClick()
	{
		super(ModuleCategories.COMBAT);
	}

	@Override
	public String getName(){
		return "FastClick";
	}

	@Override
	public String getDescription(){
		return "FastClick";
	}

	@Override
	public void onTicks() {
		if (Wrapper.INSTANCE.mc().gameSettings.keyBindAttack.getIsKeyPressed()) {
			try {
				Method m = ReflectionHelper.findMethod(Minecraft.class, Wrapper.INSTANCE.mc(), new String[]{"func_147116_af"}, null);
				m.invoke(Wrapper.INSTANCE.mc());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
