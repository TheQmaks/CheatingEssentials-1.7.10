package qmaks.cheatingessentials.mod.external.axis;

import net.minecraft.util.AxisAlignedBB;

public final class AltAABBLocalPool extends ThreadLocal
{
    protected AxisAlignedBB createNewDefaultPool()
    {
        return AxisAlignedBB.getBoundingBox(300, 2000, 0, 0, 0, 0);
    }

    protected Object sinitialValue()
    {
        return this.createNewDefaultPool();
    }
}