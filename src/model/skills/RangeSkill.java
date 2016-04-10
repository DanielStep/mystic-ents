package model.skills;

import model.GameConfig;
import model.Piece;
import model.Square;

/**
 * Class that increases the piece's range trait by constant multiplier
 * 
 * @author Daniel skh
 *
 */
public class RangeSkill extends Skill implements IPerformTraitSkill {

	public RangeSkill() {
		super.setName("Range");
	}

	@Override
	public void performSkill(Square aSqr, Square tSqr) {

	}

	@Override
	public void applyModifier(Piece skillOwner) {
		skillOwner.getTraitSet().getRangeTrait().modifyValue(GameConfig.RANGEMULTIPLIER);
	}

}
