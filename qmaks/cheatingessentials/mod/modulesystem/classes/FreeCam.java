package qmaks.cheatingessentials.mod.modulesystem.classes;

import org.lwjgl.input.Keyboard;

import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.util.FreeCamEntity;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class FreeCam extends Mod {

	public FreeCamEntity freecamEnt = null;
	private double posX, posY, posZ;

	public FreeCam()
	{
		super(ModuleCategories.PLAYER);
	}

	@Override
	public String getName(){
		return "FreeCam";
	}

	@Override
	public void onEnableMod(){
		if(Wrapper.INSTANCE.player() != null && Wrapper.INSTANCE.mc().theWorld != null)
		{
			posX = Wrapper.INSTANCE.player().posX;
			posY = Wrapper.INSTANCE.player().posY;
			posZ = Wrapper.INSTANCE.player().posZ;
			freecamEnt = new FreeCamEntity(Wrapper.INSTANCE.mc().theWorld, Wrapper.INSTANCE.player().getGameProfile());
			freecamEnt.setPosition(posX, Wrapper.INSTANCE.player().boundingBox.minY, posZ);
			Wrapper.INSTANCE.world().spawnEntityInWorld(freecamEnt);
		}
		
		Wrapper.INSTANCE.mc().renderViewEntity = freecamEnt;
	}

	@Override
	public void onDisableMod(){
		if(freecamEnt != null && Wrapper.INSTANCE.world() != null)
		{
			Wrapper.INSTANCE.world().removeEntity(freecamEnt);
			freecamEnt = null;
		}
	
		Wrapper.INSTANCE.mc().renderViewEntity = Wrapper.INSTANCE.player();
	}

	@Override
	public void onTicks() {
		Wrapper.INSTANCE.player().posX = posX;
		Wrapper.INSTANCE.player().posY = posY;
		Wrapper.INSTANCE.player().posZ = posZ;
		Wrapper.INSTANCE.player().motionX = 0.0D;
		Wrapper.INSTANCE.player().motionY = 0.0D;
		Wrapper.INSTANCE.player().motionZ = 0.0D;
		freecamEnt.inventory = Wrapper.INSTANCE.player().inventory;
		freecamEnt.yOffset = Wrapper.INSTANCE.player().yOffset;
		freecamEnt.ySize = Wrapper.INSTANCE.player().ySize;
		freecamEnt.setMovementInput(Wrapper.INSTANCE.player().movementInput);
		freecamEnt.rotationPitch = Wrapper.INSTANCE.player().rotationPitch;
		freecamEnt.rotationYaw = Wrapper.INSTANCE.player().rotationYaw;
		freecamEnt.rotationYawHead = Wrapper.INSTANCE.player().rotationYawHead;
		freecamEnt.setSprinting(Wrapper.INSTANCE.player().isSprinting());
		
		if(Wrapper.INSTANCE.mc().renderViewEntity != freecamEnt)
		{
			Wrapper.INSTANCE.mc().renderViewEntity = freecamEnt;
		}
	}
}
