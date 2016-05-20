package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;

import model.board.BoardData;
import model.game.GameTurn;
import model.piece.Piece;
import model.piece.Team;
import view.mediator.DialogView;
import view.mediator.EndTurnPanel;
import view.mediator.MoveInfoPanel;
import view.mediator.PieceInfoPanel;
import view.mediator.SaveGamePanel;
import view.mediator.TeamColorPanel;
import view.mediator.TimePanel;
import view.mediator.UndoPanel;

public class UIMediator {
	
	private BoardController boardController;
	private DialogView dialogView;
	
	private TimePanel pnTime;
	private TeamColorPanel pnTeamColor;
	private PieceInfoPanel pnPieceInfo;
	private EndTurnPanel pnEndTurn;
	private UndoPanel pnUndo;
	private SaveGamePanel pnSaveGame;
	private MoveInfoPanel pnMoveInfo;	
	
	private static UIMediator instance;

	private UIMediator() { }

	public static UIMediator getInstance()
	{
		if(instance==null)
			instance=new UIMediator();      
		return instance;
	}	   
	
	public void registerColleagues
	(
			TimePanel pnTime,
			TeamColorPanel pnTeamColor,
			PieceInfoPanel pnPieceInfo,
			UndoPanel pnUndo,
			SaveGamePanel pnSaveGame,
			EndTurnPanel pnEndTurn,
			MoveInfoPanel pnMoveInfo			
	)
	{
		
		/**
		 * Just a question:
		 * Wouldn't it be better if we used getters and setters for this kind of thing?
		 * The example was done this way, but it feels really unwieldy.
		 * 
		 */
		this.pnTime = pnTime;
		this.pnTeamColor = pnTeamColor;
		this.pnPieceInfo = pnPieceInfo;
		this.pnUndo = pnUndo;
		this.pnSaveGame = pnSaveGame;
		this.pnEndTurn = pnEndTurn;
		this.pnMoveInfo = pnMoveInfo;		
		this.dialogView  = DialogView.getInstance();

	}
	
	public void doUndo(Object o) {
		if (!boardController.undo(Integer.parseInt(o.toString()))) {
			DialogView.getInstance().showInformation("Undo move number invalid.");
		} else {
			checkUndoButton();
			
			// set undo value in save game for the team who used undo feature
			BoardData data = boardController.getBoardData();
			Team t = data.getCurrentTeam();
			data.setTeamUndo(t, Boolean.valueOf(true));
		}
	}	
	
	public void doUIEndTurn() {
		//reset the Piece info panel on switch team.
		pnPieceInfo.resetPieceInformation();
		
		// auto end the current player's turn
		pnEndTurn.executeEndTurn();
		
		checkUndoButton();
	}
	
	public void doUIUpdate(GameTurn gameTurn) {
		// update time on ControlPanel view		
		pnTime.setTime(gameTurn.getGameTimer());

		// set end turn conditions
		pnEndTurn.setGameTurn(gameTurn);
	}	
	
	// each team has only one chance to undo
	public void checkUndoButton(){
		Team t = boardController.getBoardData().getCurrentTeam();
		Boolean isUndo = boardController.getBoardData().getTeamUndo().get(t);
		// enable/disable undo button
		if (isUndo != null) {
			// if this is a continue game
			pnUndo.getUndoButton().setEnabled(!isUndo.booleanValue());
		} else {
			// else, check the allowed undo number
			pnUndo.getUndoButton().setEnabled((t.getUndoNum() == 0) ? false : true);
		}
		
	}
	
	public void disableAllButtons() {
		pnSaveGame.getSaveButton().setEnabled(false);
		pnUndo.getUndoButton().setEnabled(false);
		pnEndTurn.getEndTurnButton().setEnabled(false);
	}
	
	public void setMoveInfoMessage(String msg) {
		pnMoveInfo.setMessage(msg);
	}	
	
	public void updatePieceInformation(Piece pce) {
		pnPieceInfo.updatePieceInformation(pce);
	}
	
	public void setPieceCount(int count) {
		// update available pieces for the current team 
		pnTeamColor.setAvailablePieces(count);
	}	

	public void setCurrentTeam(Team team) {
		// update team color on ControlPanel view based on current team enum
		pnTeamColor.setTeamColor(new Color(team.getRed(),team.getGreen(),team.getBlue()));
	}

	public BoardController getBoardController() {
		return boardController;
	}

	public void setBoardController(BoardController boardController) {
		this.boardController = boardController;
	}

	public void showDialog(MouseEvent arg0, String msg) {
		dialogView.showInformation(msg, arg0.getXOnScreen(), arg0.getYOnScreen());
	}	

}
