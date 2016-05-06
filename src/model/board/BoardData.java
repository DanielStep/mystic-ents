package model.board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

import model.piece.Piece;
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

	private BoardData() {
	}

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

	public void undoFromMemento(BoardMemento boardMemento) {
		// TODO: Directly set the squares, because they are old states
		this.boardArray[boardMemento.getFromSquare().getID()[0]][boardMemento.getFromSquare().getID()[1]] = boardMemento
				.getFromSquare();
		this.boardArray[boardMemento.getToSquare().getID()[0]][boardMemento.getToSquare().getID()[1]] = boardMemento
				.getToSquare().getOccupant() == null ? new Square() : boardMemento.getToSquare();
		this.print();
	}

	// For debugging
	public void print() {
		for (int i = 0; i < GameConfig.getROW_COL(); i++) {
			for (int j = 0; j < GameConfig.getROW_COL(); j++) {
				System.out.print(boardArray[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void resetPieceTraitValueToBase(ArrayList<Piece> pieceList) {

		for (int i = 0; i < pieceList.size(); i++) {
			pieceList.get(i).getTraitSet().getDamageTrait().setTraitValueToBase();
			pieceList.get(i).getTraitSet().getRangeTrait().setTraitValueToBase();
		}

		/*
		 * for (int row = 0; row < boardArray.length; row++){ for (int col = 0;
		 * col < boardArray[row].length; col++){ if
		 * (boardArray[row][col].getOccupant() != null){
		 * boardArray[row][col].getOccupant().getTraitSet().getDamageTrait().
		 * setTraitValueToBase();
		 * boardArray[row][col].getOccupant().getTraitSet().getRangeTrait().
		 * setTraitValueToBase(); } } }
		 */
	}

}
