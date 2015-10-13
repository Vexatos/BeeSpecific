package vexatos.beespecific.precision;

import forestry.api.arboriculture.ITree;
import forestry.api.arboriculture.TreeManager;
import hardcorequesting.quests.ItemPrecision;
import net.minecraft.item.ItemStack;

/**
 * @author Vexatos
 */
public class TreeSpecificPrecisionType extends ItemPrecision {
	protected final PrecisionModes.Alleles allele;

	public TreeSpecificPrecisionType(String name, PrecisionModes.Alleles allele) {
		super(name);
		this.allele = allele;
	}

	@Override
	protected boolean same(ItemStack stack1, ItemStack stack2) {
		ITree tree1 = TreeManager.treeRoot.getMember(stack1);
		ITree tree2 = TreeManager.treeRoot.getMember(stack2);
		if(tree1 == null || tree2 == null) {
			return false;
		}
		try {
			return same(tree1, tree2);
		} catch(NullPointerException e) {
			return false;
		}
	}

	protected boolean same(ITree tree1, ITree tree2) {
		if(allele == PrecisionModes.Alleles.BOTH) {
			if(!tree1.getGenome().getSecondary().getUID().equals(tree2.getGenome().getSecondary().getUID())) {
				return false;
			}
		}
		return tree1.getGenome().getPrimary().getUID().equals(tree2.getGenome().getPrimary().getUID());
	}
}
