package model.skills;

import model.piece.Piece;

/**
 * Interface for modifying piece trait, skill values.
 * 
 * @author skh
 *
 */

public interface Modifier {

	public void applyModifier(Piece skillOwner);

}
