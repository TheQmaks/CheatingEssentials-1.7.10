package qmaks.cheatingessentials.mod.modulesystem.classes;

import org.lwjgl.input.Mouse;

import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.potion.Potion;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class AntiPotion extends Mod {
	
	private Potion[] badEffects = { Potion.moveSlowdown, Potion.digSlowdown, Potion.harm, Potion.confusion, Potion.blindness, Potion.hunger, Potion.weakness, Potion.poison, Potion.wither };

	private Potion[] goodEffects = { Potion.moveSpeed, Potion.digSpeed, Potion.damageBoost, Potion.heal, Potion.jump, Potion.regeneration, Potion.resistance, Potion.fireResistance, Potion.waterBreathing, Potion.invisibility, Potion.nightVision, Potion.field_76434_w, Potion.field_76444_x, Potion.field_76443_y };

	
	public AntiPotion()
	{
		super(ModuleCategories.PLAYER);
	}
	
	@Override
	public String getName(){
		return "AntiPotion";
	}
	
    @Override
	public void onTicks(){
            if (Wrapper.INSTANCE.player().isPotionActive(Potion.blindness)) {
              Wrapper.INSTANCE.player().removePotionEffect(Potion.blindness.id);
            }

            if (Wrapper.INSTANCE.player().isPotionActive(Potion.confusion)) {
              Wrapper.INSTANCE.player().removePotionEffect(Potion.confusion.id);
            }

            if (Wrapper.INSTANCE.player().isPotionActive(Potion.digSlowdown)) {
              Wrapper.INSTANCE.player().removePotionEffect(Potion.digSlowdown.id);
            }

            if (Wrapper.INSTANCE.player().onGround) {
              for (Potion effect : this.badEffects)
              {
                if (Wrapper.INSTANCE.player().isPotionActive(effect)) {
                  for (int a = 0; a <= 20; a++) {
                	  Wrapper.INSTANCE.player().sendQueue.addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(Wrapper.INSTANCE.player().rotationYaw, Wrapper.INSTANCE.player().rotationPitch, Wrapper.INSTANCE.player().onGround));
                  }
                }
              }

              if ((Wrapper.INSTANCE.player().getHealth() <= 15.0F) && (Wrapper.INSTANCE.player().isPotionActive(Potion.regeneration)))
                for (int a = 0; a <= 10; a++)
        	      Wrapper.INSTANCE.player().sendQueue.addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(Wrapper.INSTANCE.player().rotationYaw, Wrapper.INSTANCE.player().rotationPitch, Wrapper.INSTANCE.player().onGround));
            }
    }
}
