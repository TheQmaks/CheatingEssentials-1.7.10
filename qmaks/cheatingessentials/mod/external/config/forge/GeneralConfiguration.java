package qmaks.cheatingessentials.mod.external.config.forge;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import qmaks.cheatingessentials.mod.commands.ACommandAuraRange;
import qmaks.cheatingessentials.mod.commands.ACommandSpeedValue;
import qmaks.cheatingessentials.mod.commands.ACommandStepHeight;
import qmaks.cheatingessentials.mod.modulesystem.classes.Step;
import qmaks.cheatingessentials.mod.wrapper.Wrapper;

/**
 * Forge configuration file manager. This create the file that manages all the general values in Cheating Essentials.
 * @author Kodehawa
 */
public class GeneralConfiguration 
{
	private volatile static GeneralConfiguration INSTANCE = new GeneralConfiguration();

	public Configuration configuration;

	public float flySpeedValue;
	public float stepHeightValue;
	public float speedValue;
	public float aurarange;
	public float nukerRange;
	public float nukerSpeed;
	public int bfrValue;

	public GeneralConfiguration()
	{
		File path = new File(Wrapper.INSTANCE.mc().mcDataDir, "/config/Cheating-Essentials/General-Config.cfg");

		configuration = new Configuration(path);

		configuration.load();

		configuration.addCustomCategoryComment(Configuration.CATEGORY_GENERAL, "General Cheating Essentials values. This change when you use commands but you can change them here too");

		Property stepHeight;
		Property speed;
		Property AuraRange;

		stepHeight = configuration.get(Configuration.CATEGORY_GENERAL, "stepHeight", ACommandStepHeight.STEP_HEIGHT_VALUE);
		speed = configuration.get(Configuration.CATEGORY_GENERAL, "speedValue", ACommandSpeedValue.SPEED_VALUE);
		AuraRange = configuration.get(Configuration.CATEGORY_GENERAL, "AuraRange", ACommandAuraRange.aurarange);

		stepHeight.comment = "Player step height when <Step> module is enabled.";
		speed.comment = "Player speed when <Speed> module is enabled.";
		AuraRange.comment = "KillAura fight range when <KillAura/MobAura/Forcefield> module is enabled.";

		stepHeightValue = (float) stepHeight.getDouble(ACommandStepHeight.STEP_HEIGHT_VALUE);
		speedValue = (float) speed.getDouble(ACommandSpeedValue.SPEED_VALUE);
		aurarange = (float) AuraRange.getDouble(ACommandAuraRange.aurarange);

		this.apply();

		configuration.save();
	}

	private void apply()
	{
		ACommandStepHeight.STEP_HEIGHT_VALUE = stepHeightValue;
		ACommandSpeedValue.SPEED_VALUE = speedValue;
		ACommandAuraRange.aurarange = aurarange;
	}

	public static GeneralConfiguration instance()
	{
		return INSTANCE;
	}

}
