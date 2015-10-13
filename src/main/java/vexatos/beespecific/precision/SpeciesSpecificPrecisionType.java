package vexatos.beespecific.precision;

import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IIndividual;
import forestry.api.genetics.ISpeciesRoot;

import hardcorequesting.quests.ItemPrecision;
import net.minecraft.item.ItemStack;

/**
 * @author Vexatos
 */
public class SpeciesSpecificPrecisionType extends ItemPrecision {
	protected final PrecisionModes.Type type;
	protected final PrecisionModes.Alleles allele;

	public SpeciesSpecificPrecisionType(String name, PrecisionModes.Type type, PrecisionModes.Alleles allele) {
		super(name);
		this.type = type;
		this.allele = allele;
	}

	@Override
	protected boolean same(ItemStack stack1, ItemStack stack2) {
		ISpeciesRoot root = AlleleManager.alleleRegistry.getSpeciesRoot(stack1);
		if (root == null || !root.isMember(stack2)) {
			return false;
		}

		if (type == PrecisionModes.Type.MATCH) {
			if (root.getType(stack1) != root.getType(stack2)) {
				return false;
			}
		}

		IIndividual individual1 = root.getMember(stack1);
		IIndividual individual2 = root.getMember(stack2);

		try {
			return same(individual1, individual2);
		} catch (NullPointerException e) {
			return false;
		}
	}

	protected boolean same(IIndividual individual1, IIndividual individual2) {
		if (allele == PrecisionModes.Alleles.BOTH) {
			if (!individual1.getGenome().getSecondary().getUID().equals(individual2.getGenome().getSecondary().getUID())) {
				return false;
			}
		}
		return individual1.getGenome().getPrimary().getUID().equals(individual2.getGenome().getPrimary().getUID());
	}

}
