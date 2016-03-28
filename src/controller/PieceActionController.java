package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Board;
import model.Piece;
import model.Square;
import model.State;
import model.Team;
import view.SquareView;

public class PieceActionController {
		
	private Board board;
	
	public PieceActionController(Board b)  {
		board = b;
	}	

	public void performAction(MouseEvent e, Square sqr) {
		
		State currentState = GameController.getCurrentState();
		
		//System.out.println("targetSquare: " + GameController.getTargetSquare().getID()[0] + " : " + GameController.getTargetSquare().getID()[1] + " :: " + GameController.getActiveSquare());
		
		switch (currentState) {
	        case STARTMOVE:
	        		
	        	System.out.println("Occupant: " + sqr.getOccupant());
	        	
				if (e.getButton() == MouseEvent.BUTTON1) {
					if (sqr.getOccupant() != null) {
						//Check for team...
						GameController.setActivePiece(sqr.getOccupant());
						GameController.setActiveSquare(sqr);
			        	GameController.setCurrentState(State.ENDMOVE);
					}
				}

	            break;
	        case ENDMOVE:				
				if (e.getButton() == MouseEvent.BUTTON1) {
					if (sqr.getOccupant() == null) {
						//Check for accessibility... how do we implement range?
						//targetSquare = sqr;
						GameController.setTargetSquare(sqr);
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
		GameController.getTargetSquare().setOccupant(GameController.getActivePiece());
		GameController.getActiveSquare().setOccupant(null);
		board.doCellsUpdate();
		//activeSquare.setOccupant(null);
		GameController.setCurrentState(State.STARTMOVE);
	}

}
