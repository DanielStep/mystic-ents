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
		
		State currentState = gameController.getCurrentState();
		//System.out.println("targetSquare: " + GameController.getTargetSquare().getID()[0] + " : " + GameController.getTargetSquare().getID()[1] + " :: " + GameController.getActiveSquare());
		
		switch (currentState) {
	        case STARTMOVE:
	        		
	        	System.out.println("Occupant: " + sqr.getOccupant());
	        	
				if (e.getButton() == MouseEvent.BUTTON1) {
					if (sqr.getOccupant() != null) {
//						System.out.println("------- current team: " + teamColorPanel.getTeamColorEnum());
//						System.out.println("---- Click on square: " + sqr.getOccupant().getTeam());
						
						//Check for the current team in turn
						if (sqr.getOccupant().getTeam() == teamColorPanel.getTeamColorEnum()) {
							gameController.setActivePiece(sqr.getOccupant());
							gameController.updatePieceInformation(sqr.getOccupant());
							gameController.setActiveSquare(sqr);
							gameController.setCurrentState(State.ENDMOVE);
						} else {
							// display dialog message if picking the wrong team piece
							String msg = "It is Team " + 
									teamColorPanel.getTeamColorEnum() + "'s turn!";
							DialogView.getInstance().showInformation(msg, e.getXOnScreen(), e.getYOnScreen());
						}
					}
				}

	            break;
	        case ENDMOVE:				
				if (e.getButton() == MouseEvent.BUTTON1) {
					if (sqr.getOccupant() == null) {
						//Check for accessibility... how do we implement range?
						//targetSquare = sqr;
						gameController.setTargetSquare(sqr);
						movePiece();
					}						
				} else if (e.getButton() == MouseEvent.BUTTON3) {
					
				}
				
	            break;
			default:
				break;
	    }		
		
	}	
	
	private void movePiece() {
		//System.out.println("targetSquare: " + GameController.getTargetSquare().getID()[0] + " : " + GameController.getTargetSquare().getID()[1] + " :: " + GameController.getActiveSquare());
		gameController.getTargetSquare().setOccupant(gameController.getActivePiece());
		gameController.getActiveSquare().setOccupant(null);
		board.doCellsUpdate();
		//activeSquare.setOccupant(null);
		gameController.setCurrentState(State.STARTMOVE);
		
		// automatically switch player when finishing a move
		gameController.getGameTurn().setGameTimer(0);
	}
	
	public void setTeamColorPanel(TeamColorPanel pn) {
		teamColorPanel = pn;
	}
	
	public void setGameController(GameController g) {
		this.gameController = g;
	}
}
