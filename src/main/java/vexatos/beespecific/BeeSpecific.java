package vexatos.beespecific;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import hardcorequesting.quests.ItemPrecision;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vexatos.beespecific.precision.BeeSpecificPrecisionType;
import vexatos.beespecific.precision.PrecisionModes.Alleles;
import vexatos.beespecific.precision.PrecisionModes.BeeType;
import vexatos.beespecific.precision.TreeSpecificPrecisionType;
import vexatos.beespecific.reference.Mods;

/**
 * @author Vexatos
 */
@Mod(modid = Mods.BeeSpecific, name = Mods.BeeSpecific_NAME, version = "@VERSION@",
	dependencies = "required-after:" + Mods.HQM + "@[4.3.0.1,);required-after:" + Mods.Forestry + "@[4.0.8,)")
public class BeeSpecific {

	@Instance(Mods.BeeSpecific)
	public static BeeSpecific instance;

	public static Logger log;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		log = LogManager.getLogger(Mods.BeeSpecific);
		registerBeeSpecificPrecisionType("Bee Species - both alleles same", BeeType.ANY, Alleles.BOTH);
		registerBeeSpecificPrecisionType("Princess Spec. - both alleles same", BeeType.PRINCESS, Alleles.BOTH);
		registerBeeSpecificPrecisionType("Bee Species - only active allele", BeeType.ANY, Alleles.ACTIVE);
		registerBeeSpecificPrecisionType("Princess Spec. - only active allele", BeeType.PRINCESS, Alleles.ACTIVE);
		registerTreeSpecificPrecisionType("Tree Species - both alleles same", Alleles.BOTH);
		registerTreeSpecificPrecisionType("Tree Species - only active allele", Alleles.ACTIVE);
	}

	public void registerBeeSpecificPrecisionType(String name, BeeType type, Alleles allele) {
		ItemPrecision.registerPrecisionType(String.format("BEE_%s_%s", type, allele), new BeeSpecificPrecisionType(name, type, allele));
	}

	public void registerTreeSpecificPrecisionType(String name, Alleles allele) {
		ItemPrecision.registerPrecisionType(String.format("TREE_%s", allele), new TreeSpecificPrecisionType(name, allele));
	}
}
