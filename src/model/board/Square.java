package model.board;

import java.awt.Color;
import java.io.Serializable;

import model.piece.Piece;
import model.piece.Team;

/**
 * Contained inside board, can contain piece or become wall
 * 
 * @author skh
 *
 */
// TODO: Couple with board
public class Square implements Serializable {

	private Piece occupyingPiece = null;
	private int[] ID = new int[2];

	private Boolean inrange = false;
	private Boolean accessible = true;	
	private Boolean teamPiece = false;
	private Team teamTower = null;
	private Color bgColor = Color.WHITE;
	
	public Square () {	}
	
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

	public Team getTeamTower() {
		// TODO Auto-generated method stub
		return this.teamTower;
	}
	public void setTeamTower(Team team) {
		this.teamTower = team;
	}

	public Color getBgColor() {
		return bgColor;
	}

	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
	}

	public Boolean getInRange() {
		return inrange;
	}

	public void setInRange(Boolean inrange) {
		this.inrange = inrange;
	}


}
