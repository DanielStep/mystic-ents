package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * Responsible for turn handling and computing the winner team
 *
 * @author skh, ms
 *
 */

public class GameController {

	private final GameTurnTimer gameTimer;
	
	public GameController() {
		
		gameTimer = new GameTurnTimer(1000);
		gameTimer.start();
		
	}

}
