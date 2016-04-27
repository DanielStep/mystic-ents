package model.skills;

import java.io.Serializable;

import model.piece.Piece;
import utils.GameConfig;

/**
 * Class that increases the piece's range trait by constant multiplier
 * 
 * @author Daniel skh
 *
 */

public class RangeSkill extends Skill implements IPerformTraitSkill, Serializable {

	public RangeSkill() {
		super.setName("Range");
	}

	@Override
	public void performSkill(Piece skillOwner) {
		skillOwner.getTraitSet().getRangeTrait().modifyValue(GameConfig.getRangemultiplier());
	}

}
