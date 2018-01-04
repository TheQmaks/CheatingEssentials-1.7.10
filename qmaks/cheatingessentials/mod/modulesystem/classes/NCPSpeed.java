package qmaks.cheatingessentials.mod.modulesystem.classes;

import java.util.Iterator;
import java.util.List;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockIce;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.AxisAlignedBB;
import qmaks.cheatingessentials.api.module.APICEMod;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.util.TimerUtils;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class NCPSpeed extends Mod {

	private double motionSpeed = 3.14D;
	private float timerSpeed = 1.1F;
	private boolean iceSpeed = true;
	private float ground = 0.0F;
	private int motionDelay;
	private boolean canStep;

	public NCPSpeed()
	{
		super(ModuleCategories.NOCHEATPLUS);
	}

	@Override
	public String getName(){
		return "Speed";
	}

	@Override
	public void onTicks() {
		boolean using = (Wrapper.INSTANCE.player().isUsingItem()) && 
				((Wrapper.INSTANCE.player().getCurrentEquippedItem().getItem() instanceof ItemFood));
		double speed = (Double)this.motionSpeed;
		double slow = 1.458D;
		double offset = 4.7D;
		float timer = (Float)this.timerSpeed;
		boolean shouldOffset = true;
		this.canStep = false;
		for (Iterator localIterator = Wrapper.INSTANCE.world().getCollidingBoundingBoxes(Wrapper.INSTANCE.player(), Wrapper.INSTANCE.player().boundingBox.copy().offset(Wrapper.INSTANCE.player().motionX / offset, 0.0D, Wrapper.INSTANCE.player().motionZ / offset)).iterator(); localIterator.hasNext(); ) { Object o = localIterator.next();
		if ((o == null) || 
				(!(o instanceof AxisAlignedBB))) continue;
		shouldOffset = false;
		break;
		}

		if ((Wrapper.INSTANCE.mc().gameSettings.keyBindForward.isPressed()) || (Wrapper.INSTANCE.mc().gameSettings.keyBindBack.isPressed()) || (Wrapper.INSTANCE.mc().gameSettings.keyBindLeft.isPressed()) || (Wrapper.INSTANCE.mc().gameSettings.keyBindRight.isPressed())) {
			if ((Wrapper.INSTANCE.player().onGround) && (this.ground < 1.0F)) {
				this.ground += 0.2F;
			}
			if (!Wrapper.INSTANCE.player().onGround) {
				this.ground = 0.0F;
			}
			if (this.ground == 1.0F) {
				if (!Wrapper.INSTANCE.player().isSprinting()) {
					offset += 0.8D;
				}
				if (Wrapper.INSTANCE.player().moveStrafing != 0.0F) {
					speed -= 0.1D;
					offset += 0.5D;
				}
				if (Wrapper.INSTANCE.player().isInWater()) {
					speed -= 0.1D;
				}

				this.motionDelay += 1;
				if (this.motionDelay == 1) {
					TimerUtils.getTimer().timerSpeed = timer;
					Wrapper.INSTANCE.player().motionX *= speed;
					Wrapper.INSTANCE.player().motionZ *= speed;
					this.canStep = false;
				} else if (this.motionDelay == 2) {
					Wrapper.INSTANCE.player().motionX /= slow;
					Wrapper.INSTANCE.player().motionZ /= slow;
					this.canStep = true;
				} else if (this.motionDelay == 3) {
					if (timer > 1.05D) {
						TimerUtils.getTimer().timerSpeed = 1.05F;
					}
					this.canStep = true;
				} else if (this.motionDelay == 4) {
					if (shouldOffset) {
						Wrapper.INSTANCE.player().setPosition(Wrapper.INSTANCE.player().posX + Wrapper.INSTANCE.player().motionX / offset, Wrapper.INSTANCE.player().posY, Wrapper.INSTANCE.player().posZ + Wrapper.INSTANCE.player().motionZ / offset);

						this.canStep = false;
					}
					this.motionDelay = 0;
				}
			}
		}
	}
}
