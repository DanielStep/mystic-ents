package utils;

import model.board.Square;
import model.piece.Piece;

public class BoardUtils {
	
	private static BoardUtils instance;
	
	private BoardUtils(){}
	
	public static synchronized BoardUtils getInstance() {
		if (instance == null) {
			instance = new BoardUtils();
		}
		return instance;
	}
	
	public Square[][] clearRangeCells(Square[][] boardData) {
		Square[][] updateData = boardData;
		for(int i = 0; i < updateData.length; i++) {
			for(int j = 0; j < updateData.length; j++) {
				updateData[i][j].setInrange(false);
			}
		}
		return updateData;
	}
	
	public Square[][] getRangeCells(int x, int y, Square[][] boardData) {
		Piece ocpt = boardData[x][y].getOccupant();
		Square[][] updateData = boardData;
		
		
		Piece pce = updateData[x][y].getOccupant();		
		int range = pce.getTraitSet().getRangeTrait().getTraitValue();
		
		/*if (pce.getSkillSet().getCurrentSkill().getName() == "range") {
			
			//pce.getSkillSet().getCurrentSkill(). //applyModifier(pce);			
			
		};*/
		
		for(int i = (x-range); i < (x+(1+range)); i++) {
			if (i >= 0 && i < updateData.length) {
				for(int j = (y-range); j < (y+(1+range)); j++) {
					if (j >= 0 && j < updateData[i].length) {
						updateData[i][j].setInrange(checkRangeCriteria(updateData[i][j], ocpt));
					}
				}				
			}
		}
		return updateData;
	}
	
	private Boolean checkRangeCriteria(Square data, Piece ocpt) {
		if (data.getOccupant() != null) {
			if (data.getOccupant().getTeam() == ocpt.getTeam()){
				return false;
			}			
		}
		if (!data.getAccessible() || data.getTeamTower()) {
			return false;
		}
		return true;
	}
	
}
