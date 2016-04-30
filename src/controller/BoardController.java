package controller;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import model.board.BoardData;
import model.board.BoardState;
import model.board.Square;
import utils.BoardUtils;
import utils.GameConfig;
import view.BoardFrame;

/**
 * Controller for board state
 * Generates the Board Model
 * Generates the Board View
 * Observes Board Model changes and calls View update
 * @author Mark
 *
 */

public class BoardController implements Observer {

	//VIEW
	private BoardFrame boardFrame;
	
	//MODEL
	private BoardState boardState;
	private BoardData boardData;	
	
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
	}
	
	public void buildBoard() {
		System.out.println("Building board...");
		boardFrame.pack();
		boardFrame.getBoardPanel().setLayout(new GridLayout
				(GameConfig.getROW_COL(), GameConfig.getROW_COL()));
	}
	
	public void clearRangeCells() {
		boardData.setBoardArray(boardUtils.clearRangeCells(boardData.getBoardArray()));
		boardData.doCellsUpdate();
	}
	
	public void getRangeCells(int x, int y) {
		boardData.setBoardArray(boardUtils.getRangeCells(x, y, boardData.getBoardArray()));
		boardData.doCellsUpdate();
	}

	public void saveToMemento() {
		boardData.saveToMemento();
	}
	
	public void observe(Observable o) {
		o.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		Square[][] data = ((BoardData) o).getBoardArray();
		if (data == null) return;
		System.out.println("Updating Board...");
		boardFrame.getBoardPanel().refreshBoard(data);
	}
	
	public BoardData getBoardData() {
		return boardData;
	}
	
	public BoardFrame getBoardFrame() {
		return boardFrame;
	}

}