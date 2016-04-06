package model.skills;

import model.Piece;

public class RangeSkill extends Skill implements IPerformTraitSkill {

	private final int RANGEMULTIPLIER = 2;

	public RangeSkill() {
		// TODO Auto-generated constructor stub
		super.setName("<html>Range <br>amplifier</html>");
	}
	
	@Override
	public void performSkill(Piece skillOwner) {

		skillOwner.getTraitSet().getRangeTrait().modifyValue(RANGEMULTIPLIER);

	}

}
