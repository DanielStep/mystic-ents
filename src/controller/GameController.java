package controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.GameTurn;
import model.Piece;

/**
 * Responsible for turn handling and computing the winner team
 *
 * @author skh, ms, pv
 *
 */

public class GameController implements Observer {

	private static GameTurn gameTimer;
	
	public GameController() {
		startTimer();
	}
	
	private void startTimer() {
		gameTimer = new GameTurn();
		observe(gameTimer);
		gameTimer.start();
	}
	
	public void observe(Observable o) {
		o.addObserver(this);
	}	
	
	@Override
	public void update(Observable o, Object arg) {
		int data = ((GameTurn) o).getGameTimer();
		System.out.println("Time remaining: " + data);
		if (data == 0) {
			System.out.println("Player change!");
			startTimer();
		}
	}


}