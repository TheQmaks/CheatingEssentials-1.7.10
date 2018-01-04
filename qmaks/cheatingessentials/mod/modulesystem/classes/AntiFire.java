package qmaks.cheatingessentials.mod.modulesystem.classes;

import org.lwjgl.input.Keyboard;

import net.minecraft.network.play.client.C03PacketPlayer;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class AntiFire extends Mod {
	
	public AntiFire()
	{
		super(ModuleCategories.PLAYER);
	}
	
	@Override
	public String getName(){
		return "AntiFire";
	}
	
    @Override
	public void onTicks(){
       if(!WaterWalk.isOnLiquid(Wrapper.INSTANCE.player().boundingBox) && (Wrapper.INSTANCE.player().isBurning()) && (Wrapper.INSTANCE.player().onGround)) {
        for (int i = 0; i < 10; i++) {
          Wrapper.INSTANCE.player().sendQueue.addToSendQueue(new C03PacketPlayer(false));
        }
      }
   }
}
