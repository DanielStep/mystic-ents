package controller;

import java.awt.event.MouseEvent;

import model.Board;
import model.Piece;
import model.Square;
import model.State;
import model.Team;
import view.ControlPanel;
import view.DialogView;
import view.PieceInfoPanel;
import view.SquareView;
import view.TeamColorPanel;

/**
 * This is main handler for selecting pieces and controlling their moves
 * Class acts as a bridge between the BoardController and the GameController
 * 
 * @author MS
 *
 */
public class PieceActionController {
		
	private Board board;
	private TeamColorPanel teamColorPanel;
	private GameController gameController;
	
	public PieceActionController(Board b)  {
		board = b;
	}	

	public void performAction(MouseEvent e, Square sqr) {
		
		//Minimize calls to sqr by getting occupant;
		Piece ocpt = sqr.getOccupant();
		
		//Ascertain if square is new occupant
		if (ocpt != gameController.getActivePiece() && 
			ocpt != null &&
			ocpt.getTeam() == teamColorPanel.getTeamColorEnum()) {
			gameController.setCurrentState(State.STARTMOVE);
		}
		
		//Fetch current state from gameController
		State currentState = gameController.getCurrentState();

		//(Mini) State Machine
		switch (currentState) {
	        case STARTMOVE:	        			        	
				if (e.getButton() == MouseEvent.BUTTON1) {
					if (ocpt != null) {
						//Check for the current team in turn
						if (ocpt.getTeam() == gameController.getCurrentTeam()) {
							gameController.setActivePiece(ocpt);
							gameController.updatePieceInformation(ocpt);
							gameController.setActiveSquare(sqr);
							gameController.setCurrentState(State.ENDMOVE);
						} else {
							// display dialog message if picking the wrong team piece
							String msg = "It is Team " + gameController.getCurrentTeam() + "'s turn!";
							DialogView.getInstance().showInformation(msg, e.getXOnScreen(), e.getYOnScreen());
						}
					}
				}
	            break;
	            
	        case ENDMOVE:				
				if (e.getButton() == MouseEvent.BUTTON1) {
					if (sqr.getOccupant() == null) {
						//Check for accessibility... how do we implement range?
						Boolean doMove = getDistance(gameController.getActiveSquare(), sqr);
						if (doMove) {
							gameController.setTargetSquare(sqr);
							movePiece();						
						}
					}						
				} else if (e.getButton() == MouseEvent.BUTTON3) {
					
				}				
	            break;
	            
			default:
				break;
				
	    }		
		
	}	
	
	private void movePiece() {
		gameController.getTargetSquare().setOccupant(gameController.getActivePiece());
		gameController.getActiveSquare().setOccupant(null);
		board.doCellsUpdate();
		gameController.setCurrentState(State.STARTMOVE);
		
		// automatically switch player when finishing a move
		gameController.getGameTurn().setGameTimer(0);
	}
	
	private Boolean getDistance(Square c, Square t) {		
		Boolean inrange = true;
		int range = c.getOccupant().getTraitSet().getRangeTrait().getTraitValue();		
		if ((t.getID()[0] - c.getID()[0]) > range || (c.getID()[0] - t.getID()[0]) > range ||
			(t.getID()[1] - c.getID()[1]) > range || (c.getID()[1] - t.getID()[1]) > range) {
			inrange = false;
		}
		return inrange;
	}
	
	public void setTeamColorPanel(TeamColorPanel pn) {
		teamColorPanel = pn;
	}
	
	public void setGameController(GameController g) {
		this.gameController = g;
	}
}
