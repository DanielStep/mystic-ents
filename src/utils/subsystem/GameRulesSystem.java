package utils.subsystem;

import controller.ActionController;
import model.board.Square;
import model.state.StateAttack;
import model.state.StatePerformSkill;
import view.mediator.DialogView;

public class GameRulesSystem {

	public Boolean checkMoveRules(ActionController a, Square s) {
		//Perform skill. Change State, restart;
		if (a.getActionButton() == (Integer) 3) {
			a.changeState(StatePerformSkill.getInstance(a));
			return false;
		}
		if (s.getOccupant() == null) {
			if  (s.getInRange()) {
				return true;		
			}
		} else {
			//Swap piece so restart this State
			if (a.getActivePiece().getTeam() == s.getOccupant().getTeam()) {
				a.setActivePiece(s.getOccupant());
				return false;
			}		
			//Attack piece so change State
			if (a.getActivePiece().getTeam() != s.getOccupant().getTeam() && s.getInRange()) {
				a.changeState(StateAttack.getInstance(a));
				return false;
			}			
		}		
		return true;
	}
	
	public boolean isWinCondition(ActionController a, Square s){
		if (s.getTeamTower() != null) {
			// if the player's own Usurper piece lands on the opponent tower
			if (a.getActivePiece().getIsUsurper() &&
				a.getActivePiece().getTeam() != s.getTeamTower()) {
				// it is a win
				a.handleEndGameUI();
				DialogView.getInstance().showInformation("Team " + a.getActivePiece().getTeam() + " win!");
				return true;
			} else {
				return false;
			}
		}
		return true;
	}
	
}
