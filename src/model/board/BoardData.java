package model.board;

import java.io.Serializable;
import java.util.Observable;

import model.piece.Team;
import utils.GameConfig;

public class BoardData extends Observable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3623588786142287356L;

	private Square[][] boardArray = new Square[GameConfig.getROW_COL()][GameConfig.getROW_COL()];
	
	private static BoardData instance;
	private Team currentTeam = null;
	
	private BoardData() {}	
	
	public static synchronized BoardData getInstance() {
		if (instance == null) {
			instance = new BoardData();
		}
		return instance;
	}

	public void setBoardArray(Square[][] data) {
		boardArray = data;
	}
	
	public Square[][] getBoardArray() {
		return boardArray;
	}	
	
	public void setCurrentTeam(Team team) {
		this.currentTeam = team;
	}
	
	public Team getCurrentTeam() {
		return currentTeam;
	}
	
	public void doCellsUpdate() {
		setChanged();
	    notifyObservers();
	}	
	
	public BoardMemento saveToMemento() {
		BoardMemento boardMemento = new BoardMemento(getBoardArray());
		return boardMemento;
	}
	
	public void undoFromMemento(BoardMemento boardMemento){
		this.boardArray = boardMemento.getBoardData();
		doCellsUpdate();
	}
	
	public void resetPieceTraitValueToBase(){
		for (int row = 0; row < boardArray.length; row++){ 
			for (int col = 0; col < boardArray[row].length; col++){ 
				if (boardArray[row][col].getOccupant() != null){
					boardArray[row][col].getOccupant().getTraitSet().getDamageTrait().setTraitValueToBase();
					boardArray[row][col].getOccupant().getTraitSet().getRangeTrait().setTraitValueToBase();
				}
			}
		}
	}
	
}
