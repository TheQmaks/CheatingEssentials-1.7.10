package qmaks.cheatingessentials.mod.modulesystem.classes;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import qmaks.cheatingessentials.api.module.Mod;
import qmaks.cheatingessentials.mod.wrapper.ModuleCategories;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

public class BlockSmash extends Mod {

	public BlockSmash()
	{
		super(ModuleCategories.RENDER);
	}

	@Override
	public String getName(){
		return "BlockSmash";
	}

	@Override
	public String getDescription(){
		return "BlockSmash";
	}

	@Override
	public void onTicks(){   	
		Wrapper.INSTANCE.player().fallDistance = 100.0F;
		int x = MathHelper.floor_double(Wrapper.INSTANCE.player().posX);
		int y = MathHelper.floor_double(Wrapper.INSTANCE.player().posY - 0.20000000298023224D - (double)Wrapper.INSTANCE.player().yOffset);
		int z = MathHelper.floor_double(Wrapper.INSTANCE.player().posZ);
		Block block = Wrapper.INSTANCE.player().worldObj.getBlock(x, y - 1, z);
		Wrapper.INSTANCE.player().worldObj.playAuxSFX(2006, x, y, z, MathHelper.ceiling_float_int(Wrapper.INSTANCE.player().fallDistance - 3.0F));
		block.onFallenUpon(Wrapper.INSTANCE.player().worldObj, x, y, z, Wrapper.INSTANCE.player(), Wrapper.INSTANCE.player().fallDistance);
	}
}
