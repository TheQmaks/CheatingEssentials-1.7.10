package qmaks.cheatingessentials.mod.modulesystem.classes;

import java.util.Random;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.Container;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class ChestStealer extends Mod {
	
	private int delay = 0;
	
	public ChestStealer()
	{
		super(ModuleCategories.PLAYER);
	}
	
	@Override
	public String getName(){
		return "ChestStealer";
	}
	
    @Override
	public void onTicks(){
       if (!Wrapper.INSTANCE.mc().inGameHasFocus && (Wrapper.INSTANCE.mc().currentScreen instanceof GuiChest)) {
        	if (!this.isContainerEmpty(Wrapper.INSTANCE.player().openContainer)) {
        	   int slotId = this.getNextSlotInContainer(Wrapper.INSTANCE.player().openContainer);
        	   if (this.delay >= 5) {
        		 Wrapper.INSTANCE.mc().playerController.windowClick(Wrapper.INSTANCE.player().openContainer.windowId, slotId, 0, 1, Wrapper.INSTANCE.player());
        		 this.delay = 0;
        	   }
        				
        	  this.delay++;
            } else {
        	  Wrapper.INSTANCE.player().closeScreen();
           }
        }
	}
    
	private int getNextSlotInContainer(Container container) {
		for (int i = 0, slotAmount = container.inventorySlots.size() == 90 ? 54 : 27; i < slotAmount; i++) {
			if (container.getInventory().get(i) != null) {
				return i;
			}
		}
		
		return -1;
	}
	
	private boolean isContainerEmpty(Container container) {
		for (int i = 0, slotAmount = container.inventorySlots.size() == 90 ? 54 : 27; i < slotAmount; i++) {
			if (container.getSlot(i).getHasStack()) {
				return false;
			}
		}
		
		return true;
	}
}
