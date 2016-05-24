package model.state;

import controller.ActionController;
import model.board.BoardMemento;
import model.board.Square;
import view.mediator.DialogView;

public class StateAttack implements IGameState {

	private StateAttack() { }

	private static StateAttack instance;

	public static StateAttack getInstance(ActionController a) {
		if (instance == null) {
			instance = new StateAttack();
		}
		return instance;
	}	
	
	@Override
	public void startAction(ActionController a, Square s) {
		if (!s.getInRange()) return;
		System.out.println("Start attack");
		
		//Save state before making action
		a.saveToMemento(new BoardMemento(a.getActiveSquare(), s));
		
		a.getActivePiece().attackOut(s.getOccupant());

		//move to gameutils
		int targetHealthValue = s.getOccupant().getTraitSet().getHealthTrait().getTraitValue();
		if ( targetHealthValue < 1) {
			s.getOccupant().setInPlay(false);
			a.getGameController().setMessage(a.getActivePiece().getTeam() + "  KILLED  " + s.getOccupant().getTeam() + "!");
			s.setOccupant(null);
			a.getBoardController().getBoardData().doCellsUpdate();
		}		
		endAction(a, s);
	}

	@Override
	public void endAction(ActionController a, Square s) {
		a.setActivePiece(null);
		updateAction(a);
	}

	@Override
	public void updateAction(ActionController a) {
		System.out.println("End attack");
		a.changeState(StateMove.getInstance(a));
		a.getBoardController().clearRangeCells();
		a.checkActionCount();
	}


}