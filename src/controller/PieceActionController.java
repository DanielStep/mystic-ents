package controller;

import java.awt.event.MouseEvent;

import model.Piece;
import model.Square;
import view.DialogView;
import view.SquareView;

/**
 * This is main handler for selecting pieces and controlling their moves
 * Class acts as a bridge between the BoardController and the GameController
 * 
 * @author mark
 *
 */
public class PieceActionController {
		
	private BoardController board;
	private GameController gameController;
	
	private Piece activePiece;
	private Piece targetPiece;
	private Square activeSquare;
	private Square targetSquare;
	
	public PieceActionController(BoardController b)  {
		board = b;
	}
	
	public void performAction(MouseEvent e, SquareView sqr) {
		
		//Minimize calls to sqr by getting occupant;
		Piece ocpt = sqr.getSqrObj().getOccupant();

		//USING SKILLS
		if (e.getButton() == MouseEvent.BUTTON1) {			
			//Check for piece
			if (ocpt != null) {
				//Check for the current team in turn
				if (ocpt.getTeam() == gameController.getCurrentTeam()) {
					//Ascertain if square is new occupant
					if (!ocpt.getInMove() && activePiece != null) {
						switchPiece(ocpt);
					}
					manageSquare(sqr.getSqrObj(), ocpt);
				} else {
					if (!ocpt.getInMove() && activePiece == null) {
						// display dialog message if picking the wrong team piece
						String msg = "It is Team " + gameController.getCurrentTeam() + "'s turn!";
						DialogView.getInstance().showInformation(msg, e.getXOnScreen(), e.getYOnScreen());
						return;
					}					
					if (sqr.getSqrObj().getInrange() && sqr.getSqrObj().getAccessible()) {
						// display dialog message if attacking
						if (!ocpt.getInMove() && activePiece != null) {
							attackPiece(ocpt);
							String msg = "Attack!";
							DialogView.getInstance().showInformation(msg, e.getXOnScreen(), e.getYOnScreen());						
						}					
					}
				}
			} else {
				//An inaccessible square cannot be moved to or selected
				if (sqr.getSqrObj().getInrange() && sqr.getSqrObj().getAccessible()) {					
					movePiece(sqr.getSqrObj(), ocpt);					
				}
			}
		}
		//USING SKILLS
		if (e.getButton() == MouseEvent.BUTTON3) {
			if (activePiece != null) {
				// display dialog message if performing SKILL
				String msg = "Performing Skill!";
				DialogView.getInstance().showInformation(msg, e.getXOnScreen(), e.getYOnScreen());
			}
		}

	}	
	
	
	private void attackPiece(Piece pce) {		
		activePiece.attackOut(pce);
	}
	private void movePiece(Square sqrObj, Piece pce) {
		targetSquare = sqrObj;
		activePiece.setInMove(false);
		targetSquare.setOccupant(activePiece);
		activeSquare.setOccupant(null);
		board.getBoardState().doCellsUpdate();
		endTurn();		
	}	
	
	private void switchPiece(Piece pce) {
		activePiece.setInMove(false);
		clearActivePieceRange();
	}
	
	private void manageSquare(Square sqrObj, Piece pce) {
		pce.setInMove(true);
		gameController.getGameBoard().getBoardState().getRangeCells(sqrObj.getID()[0], sqrObj.getID()[1]);
		activeSquare = sqrObj;
		activePiece = pce;
		gameController.updatePieceInformation(pce);
	}
	
	private void endTurn() {
		// automatically switch player when finishing a move
		gameController.getGameTurn().setGameTimer(0);
	}
	
	private void clearActivePieceRange() {
		// reset board
		gameController.getGameBoard().getBoardState().clearRangeCells();
	}
	
	public void setGameController(GameController g) {
		this.gameController = g;
	}
}
