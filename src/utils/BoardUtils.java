package utils;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.ArrayList;

import model.board.Square;
import model.piece.Piece;

public class BoardUtils {

	private static BoardUtils instance;
	
	private RangeChecker checker = new RangeChecker();
	
	private BoardUtils(){}
	
	public static synchronized BoardUtils getInstance() {
		if (instance == null) {
			instance = new BoardUtils();
		}
		return instance;
	}
	
	public Square[][] getRangeCells(int x, int y, Square[][] boardData) {

		boardData = clearRangeCells(boardData);

		/*if (pce.getSkillSet().getCurrentSkill().getName() == "range") {	
			//pce.getSkillSet().getCurrentSkill(). //applyModifier(pce);		
		};*/		
		

		Piece pce = boardData[x][y].getOccupant();		
		int range = pce.getTraitSet().getRangeTrait().getTraitValue();

		checker.setBoardData(boardData);
		checker.setRange(range);
		checker.setX(x);
		checker.setY(y);
		boardData = checker.gatherCheckSquaresByRange();
		return boardData;
	}
	
	public Square[][] clearRangeCells(Square[][] boardData) {
		Square[][] updateData = boardData;
		for(int i = 0; i < updateData.length; i++) {
			for(int j = 0; j < updateData.length; j++) {
				updateData[i][j].setInRange(false);
			}
		}
		return updateData;
	}
	
	class RangeChecker {
		
		private int x;
		private int y;
		private Square[][] boardData;
		private int range;
		private ArrayList <Square> rangeList = new ArrayList <Square>();

		public Square[][] gatherCheckSquaresByRange() {
			ArrayList<Square> getSquares = new ArrayList<Square>();
			int crange = 0-range;
			for(int h = (x-range); h < (x+(range+1)); h++) {
				for(int i = x-crange; i < x+(crange+1); i++) {
					for(int j = y-crange; j < y+(crange+1); j++) {
						if (i > -1 && j > -1 && i < this.boardData.length && j < this.boardData.length) {
							boardData[i][j].setInRange(checkRangeCriteria(boardData[i][j]));
							rangeList.add(boardData[i][j]);
						}
					}
				}			
				crange++;			
			}
			return boardData;			
		}

		private Boolean checkRangeCriteria(Square check) {

			if (check.getOccupant() != null) {
				if (check.getOccupant().getTeam() == boardData[x][y].getOccupant().getTeam() ) {
					return false;
				}			
			}
			
			if (!check.getAccessible()) {
				return false;
			}
			
			return true;
		}		
		
		public ArrayList<Square> getRangeList() {
			return rangeList;
		}
		
		public void setRangeList(ArrayList<Square> rangeList) {
			rangeList = rangeList;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public int getRange() {
			return range;
		}
		public void setRange(int range) {
			this.range = range;
		}
		public Square[][] getBoardData() {
			return boardData;
		}
		public void setBoardData(Square[][] boardData) {
			this.boardData = boardData;
		}
		
	}  
	
	/**
	* Converts a given string into a color.
	* 
	* @param value
	* 	the team name corresponding to a color.	
	* @param dft
	* 	is sent as a fallback (default) if the parsing fails.
	* @return the color.
	*/
	public Color stringToColor(final String value, Color dft) {
		//null value is handled by returning default; 
		if (value == null) {
			return dft;
		}
		try {
			// try to get a color by name using reflection
			final Field f = Color.class.getField(value);
			return (Color) f.get(null);
		} catch (Exception ce) {
			// if we can't get any color return default
			return dft;
		}
	}
	
}
