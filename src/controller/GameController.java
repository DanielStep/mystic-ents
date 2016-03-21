package controller;

import java.util.Observable;
import java.util.Observer;

import model.GameTurn;

/**
 * Responsible for turn handling and computing the winner team
 *
 * @author skh, ms
 *
 */

public class GameController implements Observer {

	private static GameTurn gameTimer;

	public GameController() {
		gameTimer = new GameTurn();
		observe(gameTimer);
		gameTimer.start();
	}
	
	public void observe(Observable o) {
		o.addObserver(this);
	}	
	
	@Override
	public void update(Observable o, Object arg) {
		int data = ((GameTurn) o).getGameTurn();
		System.out.println("Turn: " + data);
	}	

}