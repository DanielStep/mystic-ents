package model;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Toolkit;

public class GameTurn extends Observable {
	
    Toolkit toolkit;
    Timer timer;
    
    public int gameTurn = 0;

    public GameTurn() {
    	toolkit = Toolkit.getDefaultToolkit();
    }
    
    private void buildTimer() {
    	timer = new Timer();
        timer.schedule(new TimerTask() {
			@Override
			public void run() {
				gameTurn++;				
				if (gameTurn >= GameConfig.getMaxTurns())
				{
					stop();
					return;
				}
				setChanged();
			    notifyObservers();
			}
    	}, GameConfig.getStartDelay(), GameConfig.getTimerPeriod());
    }

    public int getGameTurn() {
    	return gameTurn;
    }
    
    public void stop() {
    	timer.cancel();
    }    
    
    public void start() {
    	System.out.println("Start");
    	buildTimer();
    }
    
}