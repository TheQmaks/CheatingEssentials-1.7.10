package qmaks.cheatingessentials.mod.modulesystem.classes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class AutoBlock extends Mod {
	
	public static boolean isActive = false;
	
	public AutoBlock()
	{
		super(ModuleCategories.COMBAT);
	}
	
	@Override
	public String getName(){
		return "AutoBlock";
	}
	
	@Override
	public String getDescription(){
		return "AutoBlock";
	}
	
	@Override
	public void onEnableMod() {
		isActive = true;
	}
	
	@Override
	public void onDisableMod() {
		isActive = false;
	}
	
	@Override
	public void onTicks() {
		if((!KillAura.isActive && !MobAura.isActive && !ProphuntAura.isActive && !Forcefield.isActive && !TriggerBot.isActive) && Wrapper.INSTANCE.mc().gameSettings.keyBindAttack.getIsKeyPressed() && (Wrapper.INSTANCE.player().getCurrentEquippedItem() != null) && (Wrapper.INSTANCE.player().getCurrentEquippedItem().getItem() instanceof ItemSword)) {
            ItemStack lel = Wrapper.INSTANCE.player().getCurrentEquippedItem();
            lel.useItemRightClick(Wrapper.INSTANCE.world(), Wrapper.INSTANCE.player());
        }
	}
}
