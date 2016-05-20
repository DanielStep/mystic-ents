package utils;

import java.util.ArrayList;

import controller.BoardController;

import model.board.BoardCareTaker;
import model.board.BoardMemento;
import model.board.Square;
import model.piece.Piece;

import view.BoardPanel;
import view.SquareView;

public class BoardUtils {

	private static BoardUtils instance;
	private ArrayList<Square> rangeList = new ArrayList<Square>();
	private SquareView[][] squareViewArray;

	private BoardUtils() {
	}

	public static synchronized BoardUtils getInstance() {
		if (instance == null) {
			instance = new BoardUtils();
		}
		return instance;
	}

	public Square[][] getRangeCells(int x, int y, Square[][] boardData) {
		Piece pce = boardData[x][y].getOccupant();
		int range = pce.getTraitSet().getRangeTrait().getTraitValue();
		int bSize = (GameConfig.getROW_COL());		
		rangeList.clear();
		rangeList.add(boardData[x][y]);
		for (int i = (x - range > -1 ? x - range : 0); i < (x + (range + 1) < bSize ? x + (range + 1) : bSize); i++) {
			for (int j = (y - range > -1 ? y - range : 0); j < (y + (range + 1) < bSize ? y + (range + 1)
					: bSize); j++) {
				boardData[i][j].setInRange(checkRangeCriteria(boardData[i][j]));
				rangeList.add(boardData[i][j]);
			}
		}
		return boardData;
	}

	private Boolean checkRangeCriteria(Square check) {
		Boolean setRange = true;
		if (!check.getAccessible()) {
			setRange = false;
		}
		return setRange;
	}

	public Square[][] clearRangeCells(Square[][] data) {
		ArrayList<Square> rangeList = new ArrayList<Square>(getRangeList());
		for (int i = 0; i < rangeList.size(); i++) {
			data[rangeList.get(i).getID()[0]][rangeList.get(i).getID()[1]].setInRange(false);
		}
		return data;
	}

	public ArrayList <Square> getRangeList(Square[][] data) {
		ArrayList <Square> s = new ArrayList <Square>();
		for (int i=0; i<data.length; i++) {
			for (int j=0; j<data[i].length; j++) {
				if (data[i][j].getInRange()) {
					s.add(data[i][j]);
				}				
			}
		}
		return s;
	}
	
	public void updateBoard() {
		for(int i = 0; i < rangeList.size(); i++) {
			squareViewArray[ rangeList.get(i).getID()[0] ][ rangeList.get(i).getID()[1] ].buildView(rangeList.get(i));
		}
	}
	
	public void buildFullBoard(BoardPanel boardPanel, Square[][] data) {
		boardPanel.removeAll();
		squareViewArray = new SquareView[data.length][data.length];
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				SquareView sqr = new SquareView(data[i][j]);
				squareViewArray[i][j] = sqr;
				rangeList.add(sqr.getSqrObj());
				boardPanel.add(sqr);
			}
		}
		
	}

	public Boolean undoMove(int undos, BoardController b) {
		if (BoardCareTaker.getInstance().getMementosSize() >= undos * 4) {
			BoardMemento boardMemento = null;
			for (int i = 0; i < undos * 4; i++) {
				boardMemento = BoardCareTaker.getInstance().getMemento();
				b.getBoardData().setBoardArray(undoFromMemento(boardMemento, b.getBoardData().getBoardArray()));
			}
			b.getBoardData().getCurrentTeam().decreaseUndoNum();			
			buildFullBoard(b.getBoardFrame().getBoardPanel(), b.getBoardData().getBoardArray());
			clearRangeCells(b.getBoardData().getBoardArray());			
			b.getBoardData().doCellsUpdate();
			return true;
		}
		return false;
	}
	
	public Square[][] undoFromMemento(BoardMemento boardMemento, Square[][] data) {
		data[boardMemento.getToSquare().getID()[0]][boardMemento.getToSquare().getID()[1]] = boardMemento
				.getToSquare();
		data[boardMemento.getFromSquare().getID()[0]][boardMemento.getFromSquare().getID()[1]] = boardMemento
				.getFromSquare();
		return data;
	}

	public ArrayList<Square> getRangeList() {
		return rangeList;
	}
	public void setRangeList(ArrayList<Square> r) {
		rangeList = r;
	}
	public SquareView[][] getSquareViewArray() {
		return squareViewArray;
	}
	public void setSquareViewArray(SquareView[][] squareViewArray) {
		this.squareViewArray = squareViewArray;
	}

}
