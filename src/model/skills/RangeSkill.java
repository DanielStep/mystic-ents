package model.skills;

import model.game.GameConfig;
import model.piece.Piece;

/**
 * Class that increases the piece's range trait by constant multiplier
 * 
 * @author Daniel skh
 *
 */
public class RangeSkill extends Skill implements Modifier {

	public RangeSkill() {
		super.setName("Range");
	}

	@Override
	public void applyModifier(Piece skillOwner) {
		skillOwner.getTraitSet().getRangeTrait().modifyValue(GameConfig.RANGEMULTIPLIER);
	}

}
