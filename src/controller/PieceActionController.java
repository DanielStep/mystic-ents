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
	
	private static Piece activePiece;
	private static Piece targetPiece;
	private static Square activeSquare;
	private static Square targetSquare;
	
	private Board board;
	
	public PieceActionController(Board b)  {
		board = b;
	}	

	public void performAction(MouseEvent e, Square sqr) {
		
		State currentState = GameController.getCurrentState();
		
		switch (currentState) {
	        case STARTMOVE:
	        						
					if (e.getButton() == MouseEvent.BUTTON1) {
						if (sqr.getOccupant() != null) {
							//Check for team...
							activePiece = sqr.getOccupant();
							activeSquare = sqr;
				        	GameController.setCurrentState(State.ENDMOVE);
						}
					}

	            break;
	        case ENDMOVE:				
					if (e.getButton() == MouseEvent.BUTTON1) {
						if (sqr.getOccupant() == null) {
							//Check for accessibility... how do we implement range?
							targetSquare = sqr;
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
		System.out.println("targetSquare: " + targetSquare.getID()[0] + " : " + targetSquare.getID()[1] + " :: " + activeSquare);
		board.setBoardCell(targetSquare.getID()[0], targetSquare.getID()[1], activeSquare);		
		targetSquare.setOccupant(activePiece);
		activeSquare.setOccupant(null);
		GameController.setCurrentState(State.STARTMOVE);
	}
	
	public Piece getActivePiece() {
		return activePiece;
	}

	public void setActivePiece(Piece active) {
		activePiece = active;
	}

	public Piece getTargetPiece() {
		return targetPiece;
	}

	public void setTargetPiece(Piece target) {
		targetPiece = target;
	}
	
	public Square getActiveSquare() {
		return activeSquare;
	}

	public static void setActiveSquare(Square active) {
		activeSquare = active;
	}

	public Square getTargetSquare() {
		return targetSquare;
	}

	public void setTargetSquare(Square targetSquare) {
		this.targetSquare = targetSquare;
	}

}
