package model;

import java.awt.Color;

/**
 * Contained inside board, can contain piece or become wall
 * 
 * @author skh
 *
 */
// TODO: Couple with board
public class Square {

	private Piece occupyingPiece = null;
	private int[] ID = new int[2];

	private Boolean accessible = true;
	private Boolean teamPiece = false;
	private Boolean teamTower = false;
	private Color bgColor = Color.WHITE;
	
	public Square (int i, int j) {
		this.ID[0] = i;
		this.ID[1] = j;
	}
	
	public int[] getID() {
		return ID;
	}

	public void setID(int[] iD) {
		ID = iD;
	}
	
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

	public Boolean getTeamPiece() {
		return teamPiece;
	}

	public void setTeamPiece(Boolean teamPiece) {
		this.teamPiece = teamPiece;
	}

	public Boolean getTeamTower() {
		return teamTower;
	}

	public void setTeamTower(Boolean teamTower) {
		this.teamTower = teamTower;
	}

	public Color getBgColor() {
		return bgColor;
	}

	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
	}


}
