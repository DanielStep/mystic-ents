package controller;

import java.awt.event.MouseEvent;

import model.board.BoardData;
import model.board.Square;
import model.piece.Piece;
import view.DialogView;
import view.SquareView;

/**
 * This is main handler for selecting pieces and controlling their moves
 * Class is assigned access to the GameController
 * 
 * @author mark
 *
 */
public class PieceActionController {		

	private GameController gameController;
	private BoardController boardController;

	private Piece activePiece;
	private Square activeSquare;
	private Square targetSquare;
	private int actionCount;

	/**
	* Receives MouseEvent from SquareView Object
	* And manages the current state of the Game through
	* accessing and resetting the SquareView occupant
	*
	* @author mark
	* @param e 
	* 	is the capturing of the MousEvent
	* @param sqr
	* 	the SquareView event owner is passed as a reference
	* 
	*
	*/
	
	public PieceActionController() {}
	
	public void performAction(MouseEvent e, SquareView sqr) {
		
		//Minimize calls to sqr by getting square obj and occupant;
		Square sqrObj = sqr.getSqrObj();
		Piece ocpt = sqrObj.getOccupant();


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
					manageSquare(sqrObj, ocpt);
				} else {
					if (sqrObj.getInrange()) {
						// display dialog message if attacking
						if (!ocpt.getInMove() && activePiece != null) {
							attackPiece(sqr.getSqrObj(),ocpt);
							String msg = "Attack!";
							DialogView.getInstance().showInformation(msg, e.getXOnScreen(), e.getYOnScreen());						
						}					
					} else if (!ocpt.getInMove()) {
						// display dialog message if picking the wrong team piece
						String msg = "It is Team " + gameController.getCurrentTeam() + "'s turn!";
						DialogView.getInstance().showInformation(msg, e.getXOnScreen(), e.getYOnScreen());
						return;
					}
				}
			} else {
				//An inaccessible square cannot be moved to or selected
				if (sqrObj.getInrange()) {
					movePiece(sqr.getSqrObj(), ocpt);
				}
			}
		}
		//USING SKILLS
		if (e.getButton() == MouseEvent.BUTTON3) {
			if (activePiece != null) {
				// display dialog message if performing SKILL
				checkActionCount();
				//endTurn();
				String msg = "Performing Skill!";
				DialogView.getInstance().showInformation(msg, e.getXOnScreen(), e.getYOnScreen());
			}
		}

	}	
	
	/**
	* This is the parent SquareView's set of moves.
	* Each Square view implements MouseListener, but the responsibility 
	* has been delegated here.
	* The separation of view and operations affecting model allows
	* the de-coupling of view design from model.
	*
	* @author mark
	*
	*/	
		
	private void attackPiece(Square sqrObj, Piece pce) {
		activePiece.attackOut(pce);
		
		int targetHealthValue = pce.getTraitSet().getHealthTrait().getTraitValue();
		if(targetHealthValue < 1){
			sqrObj.setOccupant(null);
		}
		
		checkActionCount();
		//endTurn();
	}
	
	private void movePiece(Square sqrObj, Piece pce) {
		targetSquare = sqrObj;
		activePiece.setInMove(false);
		targetSquare.setOccupant(activePiece);
		activeSquare.setOccupant(null);		
		activePiece = null;
		boardController.clearRangeCells();
		checkActionCount();
		//endTurn();
	}	
	
	private void switchPiece(Piece pce) {
		activePiece.setInMove(false);
		boardController.clearRangeCells();
	}
	
	private void manageSquare(Square sqrObj, Piece pce) {
		pce.setInMove(true);
		activeSquare = sqrObj;
		activePiece = pce;		
		boardController.getRangeCells(sqrObj.getID()[0], sqrObj.getID()[1]);
		gameController.updatePieceInformation(pce);
	}
	
	/**
	 * Method controls number of actions permitted per turn
	 * Ends turn if at least 2 actions performed
	 * If less than 2 actions performed, increments the action counter
	 * 
	 * @author DS
	 */
	private void checkActionCount(){
		
		if (actionCount >=1){
			actionCount = 0;
			endTurn();
		}else{
			actionCount++;
		}
	}
	
	private void endTurn() {
		// automatically switch player when finishing a move
		gameController.getGameTurn().setGameTimer(0);
		
		// saving game board for undo
		boardController.saveToMemento();
	}
	
	private void clearActivePieceRange() {
		// reset board
		boardController.clearRangeCells();
	}
	
	public void setGameController(GameController g) {
		this.gameController = g;
	}
	public void setBoardController(BoardController bd) {
		this.boardController = bd;
	}
}
