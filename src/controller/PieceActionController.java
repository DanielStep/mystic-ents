package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.border.LineBorder;

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
		
	private BoardController board;
	private TeamColorPanel teamColorPanel;
	private GameController gameController;
	
	public PieceActionController(BoardController b)  {
		board = b;
	}	

	public void performAction(MouseEvent e, SquareView sqr) {
		
		//Minimize calls to sqr by getting occupant;
		Piece ocpt = sqr.sqrObj.getOccupant();
		Square sqrObj = sqr.sqrObj;
		
		//Ascertain if square is new occupant
		if (ocpt != gameController.getActivePiece() && 
			ocpt != null &&
			ocpt.getTeam() == teamColorPanel.getTeamColorEnum()) {
			gameController.setCurrentState(State.STARTMOVE);
			clearActivePieceRange();
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
							sqr.setBorder(new LineBorder(Color.YELLOW, 3));
							//board.getBoardState().setRangeCells(sqrObj.getID()[0], sqrObj.getID()[1]);
							gameController.getGameBoard().getBoardState().setRangeCells(sqrObj.getID()[0], sqrObj.getID()[1]);
							gameController.setActivePiece(ocpt);
							gameController.updatePieceInformation(ocpt);
							gameController.setActiveSquare(sqrObj);
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
					if (sqrObj.getOccupant() == null) {
						//Check for accessibility... how do we implement range?
						//Boolean doMove = getDistance(gameController.getActiveSquare(), sqrObj);
						if (sqrObj.getInrange()) {
							gameController.setTargetSquare(sqrObj);
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
		//clearActivePieceRange();
		gameController.getTargetSquare().setOccupant(gameController.getActivePiece());
		gameController.getActiveSquare().setOccupant(null);
		board.getBoardState().doCellsUpdate();
		gameController.setCurrentState(State.STARTMOVE);
		endTurn();
	}
	
	private void endTurn() {
		// automatically switch player when finishing a move
		gameController.getGameTurn().setGameTimer(0);
	}
	private void clearActivePieceRange() {
		// reset board
		gameController.getGameBoard().getBoardState().clearRangeCells();
	}
	
	public void setTeamColorPanel(TeamColorPanel pn) {
		teamColorPanel = pn;
	}
	
	public void setGameController(GameController g) {
		this.gameController = g;
	}
}
