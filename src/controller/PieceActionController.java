package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.border.LineBorder;

import model.Piece;
import model.Square;
import model.State;
import view.DialogView;
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
		Piece ocpt = sqr.getSqrObj().getOccupant();

		if (e.getButton() == MouseEvent.BUTTON1) {			
			//Check for piece
			if (ocpt != null) {
				//Check for the current team in turn
				if (ocpt.getTeam() == gameController.getCurrentTeam()) {
					//Ascertain if square is new occupant
					if (!ocpt.getInMove() && gameController.getActivePiece() != null) {
						switchPiece(ocpt);
					}
					gameController.setActivePiece(ocpt);
					manageSquare(sqr.getSqrObj(), ocpt);
				} else if (ocpt.getTeam() != gameController.getCurrentTeam()) {					
					if (sqr.getSqrObj().getInrange() && sqr.getSqrObj().getAccessible()) {
						// display dialog message if picking the wrong team piece
						if (!ocpt.getInMove() && gameController.getActivePiece() != null) {
							gameController.getActivePiece().attackOut(sqr.getSqrObj().getOccupant());
							String msg = "Attack!";
							DialogView.getInstance().showInformation(msg, e.getXOnScreen(), e.getYOnScreen());						
						}					
					}
				} else {
					if (!ocpt.getInMove() && gameController.getActivePiece() == null) {
						// display dialog message if picking the wrong team piece
						String msg = "It is Team " + gameController.getCurrentTeam() + "'s turn!";
						DialogView.getInstance().showInformation(msg, e.getXOnScreen(), e.getYOnScreen());
					}					
				}
			} else {
				//An inaccessible square cannot be moved to or selected
				if (sqr.getSqrObj().getInrange() && sqr.getSqrObj().getAccessible()) {					
					movePiece(sqr.getSqrObj(), ocpt);					
				}
			}
		}

	}	
	
	
	private void movePiece(Square sqrObj, Piece pce) {
		gameController.setTargetSquare(sqrObj);
		gameController.getActivePiece().setInMove(false);
		gameController.getTargetSquare().setOccupant(gameController.getActivePiece());
		gameController.getActiveSquare().setOccupant(null);
		board.getBoardState().doCellsUpdate();
		endTurn();		
	}	
	
	private void switchPiece(Piece pce) {
		gameController.getActivePiece().setInMove(false);
		clearActivePieceRange();
	}
	
	private void manageSquare(Square sqrObj, Piece pce) {
		pce.setInMove(true);
		gameController.getGameBoard().getBoardState().getRangeCells(sqrObj.getID()[0], sqrObj.getID()[1]);
		gameController.setActiveSquare(sqrObj);
		gameController.setActivePiece(pce);
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
	
	public void setTeamColorPanel(TeamColorPanel pn) {
		teamColorPanel = pn;
	}
	
	public void setGameController(GameController g) {
		this.gameController = g;
	}
}
