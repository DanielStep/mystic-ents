package model;

/**
 * Contained inside board, can contain piece or become wall
 * 
 * @author skh
 *
 */
// TODO: Couple with board
public class Square {

	private Piece occupyingPiece;

	private Boolean accessible = true;

	public void setAccessible(Boolean pm) {
		accessible = pm;
	}

	public Boolean getAccessible() {
		return accessible;
	}

	public void setOccupant(Piece pm) {
		occupyingPiece = pm;
	}

	public Piece getOccupant() {
		return occupyingPiece;
	}

}
