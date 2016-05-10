package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import model.board.Square;
import model.piece.Piece;
import model.piece.Team;
import model.state.StateAttack;
import utils.GameUtils;

/**
 * GHame AI
 * 
 * Game AI --- need to add notes...
 *
 * @author mark
 *
 */

public class AIController {
	
	private ArrayList <Team> teamList;
	private ArrayList <Piece> piecesList;
	private ArrayList <Square> towersList;
	
	private GameUtils gameUtils;
	private ActionController _ac;
	
	private Boolean pAttack = false;
	private Random rN = new Random();
	
	public AIController() {
		_ac = ActionController.getInstance();
		gameUtils = GameUtils.getInstance();
		piecesList = _ac.getGameController().getGamePiecesList();
		towersList = _ac.getGameController().getTowerList();
		updateTeamList();
		
		if (teamList.get(0).toString() == "BLUE") {
			teamList.get(0).setAI(true);
		}
	}
	
	public void handleGameTurn(Team ct) {
		Piece p = getNextPiece();
		pAttack = false;
		_ac.startAction(_ac, p.getParentSquare());

		SelectNextAction(p);
		
		Square t = getOpponentTower();
		//Square v = getOpponentPiece();
		
		Square s = getNextSquare(t);
		_ac.endAction(_ac, s);		
	}
	
	private void SelectNextAction(Piece p) {
		float chance = rN.nextFloat();
		if (chance < 0.50f) {
		    return;
		} else if (chance < 0.80f) {
			_ac.changeState(StateAttack.getInstance(_ac));
		} else {
			_ac.setActionButton(3);
		}
	}	
	
	private Square getNextSquare(Square t) {
		ArrayList<Square> rangeList = new ArrayList<Square>(_ac.getGameController().getRangeList());
		ArrayList<Integer> distanceList = new ArrayList<Integer>();
		for (Square r : rangeList) {
			distanceList.add(getDistance(r, t));
		}		
		int minIndex = distanceList.indexOf(Collections.min(distanceList));
		return rangeList.get(minIndex);
	}	

	private int getDistance(Square a, Square t) {
		int dx = Math.abs(t.getID()[0] - a.getID()[0]);
	    int dy = Math.abs(t.getID()[1] - a.getID()[1]);
	    int min = Math.min(dx, dy);
	    int max = Math.max(dx, dy);
	    int diagonalSteps = min;
	    int straightSteps = max - min;	    
	    Double r = Math.sqrt(2) * diagonalSteps + straightSteps;
	    return (int) Math.abs(r);		
	}
	
	private Piece getNextPiece() {
		ArrayList<Piece> aP = new ArrayList<Piece>();
		for (Piece p : piecesList) {
			if (p.getInPlay() && p.getTeam() == _ac.getGameController().getCurrentTeam()) {
				aP.add(p);
			}
		}
		int rI = rN.nextInt(aP.size());
		Piece rP = aP.get(rI);
		return rP;
	}

	private Square getOpponentTower() {
		for (Square s : towersList) {
			if (s.getTeamTower() != _ac.getGameController().getCurrentTeam()) {
				return s;
			}
		}
		return null;
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
