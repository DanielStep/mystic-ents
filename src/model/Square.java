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
	private boolean accessable;

	public Piece getOccupyingPiece() {
		return occupyingPiece;
	}

	public void setOccupyingPiece(Piece occupyingPiece) {
		this.occupyingPiece = occupyingPiece;
	}

	public boolean isAccessable() {
		return accessable;
	}

	public void setAccessable(boolean accessable) {
		this.accessable = accessable;
	}

}
