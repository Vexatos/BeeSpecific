package vexatos.beespecific;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import hardcorequesting.quests.ItemPrecision;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vexatos.beespecific.precision.SpeciesSpecificPrecisionType;
import vexatos.beespecific.precision.PrecisionModes.Alleles;
import vexatos.beespecific.precision.PrecisionModes.Type;
import vexatos.beespecific.reference.Mods;

/**
 * @author Vexatos
 */
@Mod(modid = Mods.BeeSpecific, name = Mods.BeeSpecific_NAME, version = "@VERSION@",
	dependencies = "required-after:" + Mods.HQM + "@[4.4.4,);required-after:" + Mods.Forestry + "@[4.0.9,)")
public class BeeSpecific {

	@Instance(Mods.BeeSpecific)
	public static BeeSpecific instance;

	public static Logger log;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		log = LogManager.getLogger(Mods.BeeSpecific);
		registerSpeciesSpecificPrecisionType("speciesBoth", Type.ANY, Alleles.BOTH);
		registerSpeciesSpecificPrecisionType("typeSpeciesBoth", Type.MATCH, Alleles.BOTH);
		registerSpeciesSpecificPrecisionType("speciesActive", Type.ANY, Alleles.ACTIVE);
		registerSpeciesSpecificPrecisionType("typeSpeciesActive", Type.MATCH, Alleles.ACTIVE);
	}

	public void registerSpeciesSpecificPrecisionType(String name, Type type, Alleles allele) {
		ItemPrecision.registerPrecisionType(String.format("SPECIES_%s_%s", type, allele), new SpeciesSpecificPrecisionType(name, type, allele));
	}
}
