package vexatos.beespecific.precision;

import forestry.api.apiculture.IBeeRoot;
import forestry.api.arboriculture.ITreeRoot;
import forestry.api.lepidopterology.IButterflyRoot;
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

	public SpeciesSpecificPrecisionType(String tag, PrecisionModes.Type type, PrecisionModes.Alleles allele) {
		super(tag);
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
			if (!typesEqual(root, stack1, stack2)) {
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

	// forestry didn't have 1c9fb5e23ff45adc488f3db8436c5a862112e44d yet.
	private boolean typesEqual(ISpeciesRoot root, ItemStack stack1, ItemStack stack2) {
		if (root instanceof IBeeRoot) {
			IBeeRoot beeRoot = (IBeeRoot)root;
			return beeRoot.getType(stack1) == beeRoot.getType(stack2);
		}

		if (root instanceof ITreeRoot) {
			ITreeRoot treeRoot = (ITreeRoot)root;
			return treeRoot.getType(stack1) == treeRoot.getType(stack2);
		}

		if (root instanceof IButterflyRoot) {
			IButterflyRoot butterflyRoot = (IButterflyRoot)root;
			return butterflyRoot.getType(stack1) == butterflyRoot.getType(stack2);
		}

		return false;
	}
}
