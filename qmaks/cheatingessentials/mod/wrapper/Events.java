package qmaks.cheatingessentials.mod.wrapper;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import qmaks.cheatingessentials.api.module.APICEMod;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.gui.reeszrbteam.YouAlwaysWinClickGui;
import qmaks.cheatingessentials.mod.gui.reeszrbteam.element.YAWWindow;
import qmaks.cheatingessentials.mod.main.CheatingEssentials;
import qmaks.cheatingessentials.mod.modulesystem.classes.AutoTool;
import qmaks.cheatingessentials.mod.modulesystem.classes.Criticals;
import qmaks.cheatingessentials.mod.modulesystem.classes.Forcefield;
import qmaks.cheatingessentials.mod.modulesystem.classes.KillAura;
import qmaks.cheatingessentials.mod.modulesystem.classes.MobAura;
import qmaks.cheatingessentials.mod.modulesystem.classes.NoFall;
import qmaks.cheatingessentials.mod.modulesystem.classes.Nuker;
import qmaks.cheatingessentials.mod.modulesystem.classes.ProphuntAura;
import qmaks.cheatingessentials.mod.modulesystem.classes.SeeHealth;
import qmaks.cheatingessentials.mod.modulesystem.classes.TriggerBot;
import qmaks.cheatingessentials.mod.relationsystem.Friend;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;

/**
 * Forge event subscriber
 * @author Qmaks
 */

public class Events 
{
	public static Block selectedBlock = null;

	public Events()
	{
		CheatingEssentials.INSTANCE.logger.info("Forge events initialization.");
	}

	/**
	 * Normal Tick
	 * @param event
	 */
	@SubscribeEvent
	public void onTick(TickEvent.ServerTickEvent event)
	{
		for(Mod mod : APICEMod.INSTANCE.mods)
		{
			if(mod.isActive() && Wrapper.INSTANCE.world() != null)
				mod.onTick();
		}
	}


	/**
	 * Client tick. Used for working modules on server's && Used for keybind check
	 * @param event
	 */
	@SubscribeEvent
	public void onTicks(TickEvent.ClientTickEvent event)
	{
		for(Mod mod : APICEMod.INSTANCE.mods)
		{
			if(mod.isActive() && Wrapper.INSTANCE.world() != null) {
				mod.onTicks();
			}

			if(checkAndSaveKeyState(mod.getKeybind()) && Wrapper.INSTANCE.world() != null) {
				mod.toggle();
				break;
			}
		}
	}

	/**
	 * Player Update tick. Faster than others
	 * @param event
	 */
	@SubscribeEvent
	public void onPlayerUpdate(TickEvent.PlayerTickEvent event)
	{
		for(Mod mod : APICEMod.INSTANCE.mods)
		{
			if(mod.isActive() && Wrapper.INSTANCE.world() != null)
				mod.onPlayerUpdate();
		}
		//Debugging: System.out.println("Is this called?");
	}

	/**
	 * World Tick.
	 * @param event
	 */
	@SubscribeEvent
	public void onWorldUpdate(TickEvent.WorldTickEvent event)
	{
		for(Mod mod : APICEMod.INSTANCE.mods)
		{
			if(mod.isActive() && Wrapper.INSTANCE.world() != null)
				mod.onWorldUpdate();
		}
	}

	/**
	 * This should draw things when world is rendering.
	 * @param event
	 */
	@SubscribeEvent
	public void onRenderWorld(RenderWorldLastEvent event)
	{
		for(Mod mod : APICEMod.INSTANCE.mods)
		{
			if(mod.isActive() && Wrapper.INSTANCE.world() != null)
				mod.onWorldRender();
		}
	}
	/**
	 * Left click event / For Nuker &&AutoTool
	 * @param event
	 */
	@SubscribeEvent
	public void onClick(PlayerInteractEvent event)
	{
		Block block = Wrapper.INSTANCE.world().getBlock(event.x, event.y, event.z);
		if(event.action == Action.RIGHT_CLICK_BLOCK && Nuker.isActive) {
			Block previous = selectedBlock;
			selectedBlock = block;

			if(previous == null || (previous != null && !previous.getLocalizedName().equalsIgnoreCase(selectedBlock.getLocalizedName()))) {
				Wrapper.INSTANCE.addChatMessage("&9[&bCE Console&9] &fYou have chosen the block: " + selectedBlock.getLocalizedName());
			}
		} else if(event.action == Action.LEFT_CLICK_BLOCK && AutoTool.isActive) {
			int blockId = Block.getIdFromBlock(block);
			int slot = 0;
			float fl = 0.1F;

			for (int looper = 36; looper < 45; looper++) {
				try {
					ItemStack currentSlotThatIsSelected = Wrapper.INSTANCE.player().inventoryContainer.getSlot(looper).getStack();
					if (currentSlotThatIsSelected.func_150997_a(Block.getBlockById(blockId)) > fl) {
						slot = looper - 36;
						fl = currentSlotThatIsSelected.func_150997_a(Block.getBlockById(blockId));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			Wrapper.INSTANCE.player().inventory.currentItem = slot;
		}
	}

	/**
	 * Render event / For GUI
	 * @param event
	 */
	@SubscribeEvent
	public void onGameOverlay(RenderGameOverlayEvent.Text event) {
		for(YAWWindow windows : YouAlwaysWinClickGui.windows){
			if(windows.isPinned()){
				windows.draw(0, 0);
			}
		}
	}

	/**
	 * Attack event / To view the nickname and health / Criticals
	 * @param event
	 */
	@SubscribeEvent
	public void onAttack(AttackEntityEvent event) {
		if ((!KillAura.isActive && !MobAura.isActive && !ProphuntAura.isActive && !Forcefield.isActive && !TriggerBot.isActive && Criticals.isActive) && (!Wrapper.INSTANCE.player().isInWater()) && (!Wrapper.INSTANCE.player().isInsideOfMaterial(Material.lava)) && (!Wrapper.INSTANCE.player().isInsideOfMaterial(Material.web)) && (Wrapper.INSTANCE.player().onGround) && (Wrapper.INSTANCE.mc().gameSettings.keyBindAttack.getIsKeyPressed()) && (Wrapper.INSTANCE.mc().objectMouseOver != null && Wrapper.INSTANCE.mc().objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY)) {
			event.setCanceled(true);
			Wrapper.INSTANCE.player().motionY = 0.1000000014901161D;
			Wrapper.INSTANCE.player().fallDistance = 0.1F;
			Wrapper.INSTANCE.player().onGround = false;
			event.setCanceled(false);
		}
		if(event.target instanceof EntityPlayer) {
			EntityPlayer e = (EntityPlayer)event.target;
			if(SeeHealth.isActive) {
				Wrapper.INSTANCE.addChatMessage("&9[&bCE Console&9] &fPlayer health &e" + e.getCommandSenderName() + "&f: &e" + e.getHealth());
			}
		}
	}

	/**
	 * Chat event
	 * @param event
	 */
	/*@SubscribeEvent
		public void onChat(ClientChatReceivedEvent event) {
			//Wrapper.INSTANCE.addChatMessage("[DEBUG] " + event.message.getUnformattedText());
		}*/

	public boolean checkAndSaveKeyState(int key) {
		if (Wrapper.INSTANCE.mc().currentScreen != null) 
		{
			return false;
		}

		if (Keyboard.isKeyDown(key) != keyStates [key]) 
		{
			return keyStates[key] = !keyStates[key];
		} 

		else
		{
			return false;
		}
	}

	private boolean keyStates[] = new boolean[256];

}
