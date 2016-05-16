package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import model.board.Square;
import model.piece.Piece;
import model.piece.Team;
import model.state.StateAttack;
import utils.GameConfig;
import utils.GameUtils;

/**
 * This is the main class which handles AI Team movement
 * It is coupled only with the ActionController and GameUtils Singletons.
 * Allows ActionController take orders from this class and send to State Machine.
 * Besides the methods required to inform decision making, no extra game functionality has been added 
 * 
 * @author Mark
 *
 */

public class AIController {
	
	private ArrayList <Team> teamList;
	private ArrayList <Piece> piecesList;
	private ArrayList <Square> towersList;
	
	private GameUtils gameUtils;
	private ActionController _ac;
	
	private Random rN = new Random();
	
	public AIController() {
		_ac = ActionController.getInstance();
		gameUtils = GameUtils.getInstance();
		piecesList = _ac.getGameController().getGamePiecesList();
		towersList = _ac.getGameController().getTowerList();
		updateTeamList();
		
		teamList.get(0).setAI(true);
		teamList.get(1).setAI(true);
		//teamList.get(2).setAI(true);

	}
	
	public void handleGameTurn(Team ct) {
		Piece p = getNextPiece();
		if (p != null) {			
			//System.out.println("      AI PIECE : " + p);
			_ac.startAction(_ac, p.getParentSquare());
			Square ts;
			ArrayList<Square> sqrs = new ArrayList<Square>();
			ArrayList<Square> rangeList = new ArrayList<Square>(_ac.getGameController().getRangeList());
			if (p.getIsUsurper()) {			
				sqrs = getOpponentTowers(p);
			} else {
				sqrs = getOpponentPieces(p);
				SelectNextAction(p);
			}
			if (sqrs.size() == 0) {
				_ac.endAction(_ac, p.getParentSquare());
			}
			ts = getNextSquare(sqrs, rangeList);
			_ac.endAction(_ac, ts);
		}
	}

	private void SelectNextAction(Piece p) {
		float chance = rN.nextFloat();
		if (chance < 0.80f) {
			_ac.setActionButton(1);
		    return;
		} else {
			_ac.setActionButton(3);
		}
	}	
	
	/** 
	 * Select a square from those currently in range 
	 * Piece will prioritize according to type
	 * 
	 * @author Mark
	 * 
	 * @param sqrs
	 * The assigned target squares
	 * based on the Piece's preference - e.g. Usurper should head for enemy Tower.
	 * 
	 * @param rangeList
	 * Is re-populated on every startAction State.
	 * Only squares which are in range may be selected as targets.
	 * 
	 * @return
	 * Returns move square based on selection criteria
	 * 
	 */
	private Square getNextSquare(ArrayList<Square> sqrs, ArrayList<Square> rangeList) {		
		Square s = getClosestInRange(sqrs, rangeList);
		return s;
	}
	
	private Square getRandomSquare(ArrayList<Square> sqrs, ArrayList<Square> rangeList) {		
		Square s = getClosestInRange(sqrs, rangeList);
		return s;
	}	
	
	private Square getClosestInRange(ArrayList<Square> sqrs, ArrayList<Square> rangeList) {
		Square cSquare = null;
		int shortestDistance = GameConfig.getROW_COL()*2;
		for (Square o : sqrs) {
			for (Square r : rangeList) {
				int d = getDistance(r, o);
				if (d < shortestDistance) {
					cSquare = r;
					shortestDistance = d;
				}
			}
		}
		return cSquare;
	}

	/** 
	 * Standard Euclidian Distance method
	 * Returns the (integer) distance between two points
	 * 
	 * @author Mark
	 * 
	 * @return Piece
	 */
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
	
	/**
	 * 
	 * Randomly select next piece from list
	 * Could be refined by some logic
	 * 
	 * @author Mark
	 * 
	 * @return Piece
	 */
	private Piece getNextPiece() {
		ArrayList<Piece> aP = new ArrayList<Piece>(getActivePieces(piecesList));
		if (aP.size() > 0) {
			int rI = rN.nextInt(aP.size());
			Piece rP = aP.get(rI);
			return rP;		
		}
		return null;
	}
	
	private ArrayList<Piece> getActivePieces(ArrayList<Piece> piecesList) {
		ArrayList<Piece> aP = new ArrayList<Piece>();
		for (Piece p : piecesList) {
			if (p.getInPlay() && p.getTeam() == _ac.getGameController().getCurrentTeam()) {
				aP.add(p);
			}
		}
		return aP;
	}

	private ArrayList<Square> getOpponentTowers(Piece p) {
		ArrayList<Square> sqrs = new ArrayList<Square>();
		for (Square s : towersList) {
			if (s.getTeamTower() != p.getTeam()) {
				sqrs.add(s);
			}
		}
		return sqrs;
	}	
	private ArrayList<Square> getOpponentPieces(Piece p) {
		ArrayList<Square> sqrs = new ArrayList<Square>();
		for (Piece s : piecesList) {
			if (p.getTeam() != s.getTeam()) {
				sqrs.add(s.getParentSquare());
			}
		}
		return sqrs;
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
