package controller;

import java.util.ArrayList;

import model.board.Square;
import model.piece.Piece;
import model.piece.Team;
import utils.GameUtils;

public class AIController {
	
	private ArrayList <Team> teamList;
	private ArrayList <Piece> piecesList;
	
	private GameUtils gameUtils;
	
	public AIController(ArrayList <Piece> pieces) {
		gameUtils = GameUtils.getInstance();
		piecesList = pieces;
		updateTeamList();
		
		if (teamList.get(0).toString() == "BLUE") {
			teamList.get(0).setAI(true);
		}
	}
	
	public Square[] getTargetSquare() {
		Square[] ts = new Square[2];
		return ts;
	}
			
	private Piece getNextPiece() {
		
		//Need to sort or divide teams
		updateTeamList();
		
		
		for (Piece p : piecesList) {
			//System.out.println(p);
		}
		
		Piece p = piecesList.get(0);
		
		
		return p;
	}
	
	public Boolean checkAIStatus(Team ct) {
		updateTeamList();
		for (Team t : teamList) {
			if (t == ct) {
				return ct.getAI();
			}
		}		
		return false;
	}

	
	private void updateTeamList() {
		teamList = gameUtils.getTeamList(piecesList);
	}

}
