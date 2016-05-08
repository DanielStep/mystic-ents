package controller;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import model.board.BoardCareTaker;
import model.board.BoardData;
import model.board.BoardMemento;
import model.board.BoardState;
import model.board.Square;
import utils.BoardUtils;
import utils.GameConfig;
import view.BoardFrame;

/**
 * Controller for board state Generates the Board Model Generates the Board View
 * Observes Board Model changes and calls View update
 * 
 * @author Mark
 *
 */

public class BoardController implements Observer {

	// PIECE CONTROLLER
	private ActionController pieceController;

	// VIEW
	private BoardFrame boardFrame;

	// MODEL
	private BoardState boardState;
	private BoardData boardData;

	// CareTaker for board data
	BoardCareTaker careTaker = BoardCareTaker.getInstance();

	private BoardUtils boardUtils = BoardUtils.getInstance();

	public BoardController() {
		System.out.println("New board state...");
		boardState = new BoardState();
		boardFrame = new BoardFrame();
		boardData = BoardData.getInstance();
		observe(boardData);
	}

	public void init() {
		boardState.init();
		// this needs to change - get pieces process is
		// causing a loss of saved properties
	}

	public void buildBoard() {
		System.out.println("Building board...");
		boardFrame.pack();
		boardFrame.getBoardPanel().setLayout(new GridLayout(GameConfig.getROW_COL(), GameConfig.getROW_COL()));
	}

	public void clearRangeCells() {
		boardData.setBoardArray(boardUtils.clearRangeCells(boardData.getBoardArray()));
		updateBoard();
	}

	public void getRangeCells(Square origin) {
		boardData.setBoardArray(boardUtils.getRangeCells(origin.getID()[0], origin.getID()[1], boardData.getBoardArray()));
		updateBoard();
	}
	
	public void updateBoard() {
		boardData.doCellsUpdate();
	}

	@Override
	public void update(Observable o, Object arg) {
		Square[][] data = ((BoardData) o).getBoardArray();
		if (data == null)
			return;
		//System.out.println("Updating Board...");
		boardFrame.getBoardPanel().refreshBoard(data);

		// add game state to memento after each turn
		saveToMemento();

		// set game piece list to board data for save file

	}

	public BoardData getBoardData() {
		return boardData;
	}

	public BoardFrame getBoardFrame() {
		return boardFrame;
	}

	public void setPieceActionController(ActionController pieceController) {
		this.pieceController = pieceController;
	}

	public ActionController getPieceActionController() {
		return pieceController;
	}

	public void observe(Observable o) {
		o.addObserver(this);
	}

	/** UNDO functionality **/
	// Saving game state
	public void saveToMemento() {
		boardData.saveToMemento();
	}

	// Undo from game state
	public void undo(int undoNumber) {
		for (int i = 0; i < undoNumber - 1; i++) {
			careTaker.getMemento();
		}
		BoardMemento boardMemento = careTaker.getMemento();
		boardData.undoFromMemento(boardMemento);
	}

}