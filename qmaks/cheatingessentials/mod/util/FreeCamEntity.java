package qmaks.cheatingessentials.mod.util;

import com.mojang.authlib.GameProfile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.MovementInput;
import net.minecraft.world.World;

public class FreeCamEntity extends EntityOtherPlayerMP
{
	private static MovementInput movementInput = null;

	public FreeCamEntity(World world, GameProfile gameProfile)
	{
		super(world, gameProfile);
	}

	public void setMovementInput(MovementInput movementInput)
	{
		this.movementInput = movementInput;

		if(movementInput.jump && super.onGround)
		{
			super.jump();
		}

		super.moveEntityWithHeading(movementInput.moveStrafe, movementInput.moveForward);
	}

	public void moveEntity(double x, double y, double z)
	{
		onGround = true;
		super.moveEntity(x, y, z);
		onGround = true;
	}

	public boolean isSneaking()
	{
		return this.movementInput.sneak;
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		noClip = true;
		motionX = 0;
		motionY = 0;
		motionZ = 0;
		noClip = false;
	}
}