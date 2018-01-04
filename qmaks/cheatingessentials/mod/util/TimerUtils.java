package qmaks.cheatingessentials.mod.util;

import net.minecraft.util.Timer;
import qmaks.cheatingessentials.mod.commands.ACommandSpeedValue;

public class TimerUtils extends Timer {
	
	private static Timer timer;
	
	public TimerUtils(float p_i1018_1_) {
		super(p_i1018_1_);
		timer = this;
	}
	
	public static Timer getTimer() {
		return timer;
	}
}
