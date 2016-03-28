package model.skills;

import model.Piece;
import model.Square;

public interface IPerformSquareSkill {

	public boolean performSkill(Square sqr, Piece skillOwner);
}
