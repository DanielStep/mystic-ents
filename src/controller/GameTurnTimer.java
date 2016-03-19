package controller;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.Toolkit;

public class GameTurnTimer {
	
    Toolkit toolkit;
    Timer timer;
    
    private static final int MAX_TURNS = 1000;
    public int gameTurn;
    public long period;

    public GameTurnTimer(int i) {
    	period = i;
    	toolkit = Toolkit.getDefaultToolkit();
    }
    
    public void buildTimer() {    	
    	timer = new Timer();
        timer.schedule(new TimerTask() {
			@Override
			public void run() {
				gameTurn++;				
				if (gameTurn >= MAX_TURNS)
				{
					stop();
					return;
				}
				System.out.println("New Turn: " + gameTurn);
			}
    	}, 50, 1*period);
    }

    public int getGameTurn() {
    	return gameTurn;
    }
    
    public void stop() {
    	timer.cancel();
    }    
    
    public void start() {
    	buildTimer();
    }
    
}