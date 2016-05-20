package controller;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.board.BoardData;
import model.board.BoardState;
import model.board.Square;
import model.piece.Piece;
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
	private ActionController actionController;

	// VIEW
	private BoardFrame boardFrame;

	// MODEL
	private BoardState boardState;
	private BoardData boardData;

	private BoardUtils boardUtils = BoardUtils.getInstance();

	public BoardController() {
		System.out.println("New board state...");
		boardState = new BoardState();
		boardFrame = new BoardFrame(this);
		boardData = BoardData.getInstance();
		observe(boardData);
	}

	public void init() {
		boardState.init();
	}

	public void buildBoard() {
		System.out.println("Building board...");
		boardFrame.pack();
		boardFrame.getBoardPanel().setLayout(new GridLayout(GameConfig.getROW_COL(), GameConfig.getROW_COL()));
		BoardUtils.getInstance().buildFullBoard(boardFrame.getBoardPanel(), boardData.getBoardArray());
	}

	public void clearRangeCells() {
		boardData.setBoardArray(boardUtils.clearRangeCells(boardData.getBoardArray()));
		updateBoard();
	}

	public void getRangeCells(Square origin) {
		clearRangeCells();
		boardData.setBoardArray(
				boardUtils.getRangeCells(origin.getID()[0], origin.getID()[1], boardData.getBoardArray()));
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
		boardFrame.getBoardPanel().refreshBoard(data);
	}
	
	public void observe(Observable o) {
		o.addObserver(this);
	}
	
	/** UNDO functionality **/
	public boolean undo(int i) {
		return BoardUtils.getInstance().undoMove(i, this);
	}

	public BoardData getBoardData() {
		return boardData;
	}

	public BoardFrame getBoardFrame() {
		return boardFrame;
	}

	public void setPieceActionController(ActionController a) {
		this.actionController = a;
	}

	public ActionController getPieceActionController() {
		return actionController;
	}


}