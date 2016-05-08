package model.state;

import controller.ActionController;
import model.board.Square;

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
		a.setActivePiece(s.getOccupant());
		a.getBoardController().getRangeCells(s);
		a.getGameController().updatePieceInformation(s.getOccupant());
	}

	@Override
	public void endAction(ActionController a, Square s) {
		if (checkGameRules(a, s)) {
			if (!s.getInRange()) return;
			System.out.println("End move");
			a.getBoardController().clearRangeCells();
			a.getActiveSquare().setOccupant(null);
			s.setOccupant(a.getActivePiece());			
			a.setActivePiece(null);
		} else {
			a.startAction(a, s);
		}		
	}
	
	private Boolean checkGameRules(ActionController a, Square s) {
		
		if (s.getOccupant() == null) { return true; }
		
		if (a.getActionButton() == 3) {
			a.changeState(StatePerformSkill.getInstance(a));
			return false;			
		}
		
		//Swap piece so restart this State
		if (a.getActivePiece().getTeam() == s.getOccupant().getTeam()) {
			a.startAction(a, s);
			return false;
		}
		
		//Attack piece so change State
		if (a.getActivePiece().getTeam() != s.getOccupant().getTeam()) {
			a.changeState(StateAttack.getInstance(a));
			return false;
		}		
		
		return true;
		
	}

}
