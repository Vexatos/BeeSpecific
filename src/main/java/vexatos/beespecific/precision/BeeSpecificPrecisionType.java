package vexatos.beespecific.precision;

import forestry.api.apiculture.BeeManager;
import forestry.api.apiculture.EnumBeeType;
import forestry.api.apiculture.IBee;
import hardcorequesting.quests.ItemPrecision;
import net.minecraft.item.ItemStack;

import static forestry.api.apiculture.EnumBeeType.PRINCESS;
import static forestry.api.apiculture.EnumBeeType.QUEEN;

/**
 * @author Vexatos
 */
public class BeeSpecificPrecisionType extends ItemPrecision {
	protected final PrecisionModes.BeeType type;
	protected final PrecisionModes.Alleles allele;

	public BeeSpecificPrecisionType(String name, PrecisionModes.BeeType type, PrecisionModes.Alleles allele) {
		super(name);
		this.type = type;
		this.allele = allele;
	}

	@Override
	protected boolean same(ItemStack stack1, ItemStack stack2) {
		IBee bee1 = BeeManager.beeRoot.getMember(stack1);
		IBee bee2 = BeeManager.beeRoot.getMember(stack2);
		if(bee1 == null || bee2 == null) {
			return false;
		}
		try {
			if(type == PrecisionModes.BeeType.PRINCESS) {
				EnumBeeType type1 = BeeManager.beeRoot.getType(stack1);
				EnumBeeType type2 = BeeManager.beeRoot.getType(stack2);
				if((type1 != PRINCESS && type1 != QUEEN) || (type2 != PRINCESS && type2 != QUEEN)) {
					return false;
				}
			}
			return same(bee1, bee2);
		} catch(NullPointerException e) {
			return false;
		}
	}

	protected boolean same(IBee bee1, IBee bee2) {
		if(allele == PrecisionModes.Alleles.BOTH) {
			if(!bee1.getGenome().getSecondary().getUID().equals(bee2.getGenome().getSecondary().getUID())) {
				return false;
			}
		}
		return bee1.getGenome().getPrimary().getUID().equals(bee2.getGenome().getPrimary().getUID());
	}

}
