package model.state;

import controller.ActionController;
import model.board.BoardMemento;
import model.board.Square;
import utils.CFacade;

public class StateMove implements IGameState {
	
	private StateMove() { }

	private static StateMove instance;

	public static StateMove getInstance(ActionController a) {
		if (instance == null) {
			instance = new StateMove();
		}
		return instance;
	}	
	
	@Override
	public void startAction(ActionController a, Square s) {
		System.out.println("Start move.");
		a.setActiveSquare(s);
		a.setActivePiece(s.getOccupant());
		a.getBoardController().getRangeCells(s);
		a.getGameController().updatePieceInformation(s.getOccupant());
	}

	@Override
	public void endAction(ActionController a, Square s) {
		// check the game win condition
		if (!CFacade.getInstance().isWinCondition(a, s)) return;
		if (CFacade.getInstance().checkMoveRules(a, s)) {
			if (!s.getInRange()) return;
			System.out.println("End move");
			a.saveToMemento(new BoardMemento(a.getActiveSquare(), s));
			s.setOccupant(a.getActivePiece());
			updateAction(a);
		} else {
			a.startAction(a, s);
		}		
	}
	
	@Override
	public void updateAction(ActionController a) {
		// TODO Auto-generated method stub
		a.getActiveSquare().setOccupant(null);
		a.setActivePiece(null);
		a.getBoardController().clearRangeCells();
		a.checkActionCount();
	}
	
	

}
