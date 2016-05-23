package utils.subsystem;

import java.awt.Component;
import java.util.ArrayList;

import model.board.BoardData;
import model.board.Square;
import model.piece.Piece;
import utils.CFacade;
import view.BoardPanel;
import view.SquareView;

public class BoardSystem {
	
	private SquareView[][] squareViewArray;

	public BoardSystem () {}
	
	public void updateBoard(ArrayList<Square> rangeList) {
		for(int i = 0; i < rangeList.size(); i++) {
			squareViewArray[ rangeList.get(i).getID()[0] ][ rangeList.get(i).getID()[1] ].buildView(rangeList.get(i));
		}
	}
	
	public ArrayList<Square> buildFullBoard(BoardPanel boardPanel, Square[][] data) {
		ArrayList<Square> rangeList = new ArrayList<Square>();
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
		return rangeList;
	}
	
	public ArrayList<Piece> setUpGameFromLoad(BoardData data) {
		Square[][] sqrs = data.getBoardArray();
		ArrayList<Piece> p = new ArrayList<Piece>();
		for (int i = 0; i < sqrs.length; i++) {
			for (int j = 0; j < sqrs[i].length; j++) {
				if (sqrs[i][j].getOccupant() != null) {
					p.add(sqrs[i][j].getOccupant());
				}
			}
		}
		return p;		
	}	
	
	public void disableBoard(BoardPanel boardPanel) {
		//BoardPanel boardPanel = boardController.getBoardFrame().getBoardPanel();
		for (Component com : boardPanel.getComponents()) {
			if (com instanceof SquareView) {
				SquareView sv = (SquareView)com;
				sv.removeMouseListener(sv);
			}
		}
	}
	

}
