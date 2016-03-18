package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * Responsible for turn handling and computing the winner team
 * @author skh, ms
 *
 */

public class GameController {

	//TODO: Create other controllers
	
	//private static PieceActionController actionController;
	//private static BoardController boardController;
	
	private int timerDelay = 1000;
	private final Timer gameTimer;
	
	public GameController() {
		//actionController = new PieceActionController();
		//boardController = new BoardController();
		
		// Updater of the view
		gameTimer = new Timer(timerDelay, timerListener);
		// gameTimer.start();		
	}
	
	ActionListener timerListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("listener triggered:" + e.getActionCommand());
		}
	};	
	
}
