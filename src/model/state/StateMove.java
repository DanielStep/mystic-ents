package model.state;

import controller.ActionController;
import model.board.BoardMemento;
import model.board.Square;
import view.DialogView;

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
		if (!isWinCondition(a, s)) return;
		
		if (!s.getInRange()) return;
		
		if (checkBasicGameRules(a, s)) {
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
		a.getBoardController().clearRangeCells();
		a.getActiveSquare().setOccupant(null);
		a.setActivePiece(null);
		a.checkActionCount();
	}
	
	
	private Boolean checkBasicGameRules(ActionController a, Square s) {
		
		if (a.getActionButton() == (Integer) 3) {
			a.changeState(StatePerformSkill.getInstance(a));
			return false;			
		}
		
		if (s.getOccupant() == null) return true;	
		
		//Swap piece so restart this State
		if (a.getActivePiece().getTeam() == s.getOccupant().getTeam()) {
			return false;
		}
		
		//Attack piece so change State
		if (a.getActivePiece().getTeam() != s.getOccupant().getTeam()) {
			a.changeState(StateAttack.getInstance(a));
			return false;
		}		
		
		return true;
		
	}

	private boolean isWinCondition(ActionController a, Square s){
		if (s.getTeamTower() != null) {
			// if the player's own Usurper piece lands on the opponent tower
			if (a.getActivePiece().getIsUsurper() &&
					a.getActivePiece().getTeam() != s.getTeamTower()) {
				// it is a win
				DialogView.getInstance().showInformation
						("Team " + a.getActivePiece().getTeam() + " win!");
				a.handleEndGameUI();
				return true;
			} else {
				return false;
			}
		}
		return true;
	}
}
