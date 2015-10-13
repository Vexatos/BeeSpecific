package vexatos.beespecific.reference;

import cpw.mods.fml.common.Loader;

/**
 * @author Vexatos
 */
public class Mods {

	//The mod itself
	public static final String
		BeeSpecific = "BeeSpecific",
		BeeSpecific_NAME = "Factum Opus";

	public static final String
		Forestry = "Forestry",
		HQM = "HardcoreQuesting";

	public static boolean isLoaded(String name) {
		return Loader.isModLoaded(name);
	}
}
