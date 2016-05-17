package utils;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.ArrayList;

import model.board.Square;
import model.piece.Piece;

public class BoardUtils {

	private static BoardUtils instance;
	private ArrayList<Square> rangeList = new ArrayList<Square>();

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

	public Square[][] clearRangeCells(Square[][] boardData) {
		Square[][] updateData = boardData;
		ArrayList<Square> rangeList = new ArrayList<Square>(getRangeList());
		for (int i = 0; i < rangeList.size(); i++) {
			updateData[rangeList.get(i).getID()[0]][rangeList.get(i).getID()[1]].setInRange(false);
		}
		return updateData;
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
	
	/**
	 * Converts a given string into a color.
	 * 
	 * @param value
	 *            the team name corresponding to a color.
	 * @param dft
	 *            is sent as a fallback (default) if the parsing fails.
	 * @return the color.
	 */
	public Color stringToColor(final String value, Color dft) {
		// null value is handled by returning default;
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

	public ArrayList<Square> getRangeList() {
		return rangeList;
	}

	public void setRangeList(ArrayList<Square> r) {
		rangeList = r;
	}

}
