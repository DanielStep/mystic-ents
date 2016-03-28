package model.skills;

import model.Piece;
import model.traits.Trait;

public class RangeSkill extends Skill implements IPerformTraitSkill {

	@Override
	public void performSkill(Piece skillOwner) {

		skillOwner.getTraitSet().getRangeTrait().modifyValue(2);

	}

}
