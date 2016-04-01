package model;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Toolkit;

public class GameTurn extends Observable {
	
    Toolkit toolkit;
    Timer timer;
    
    public int gameTimer = GameConfig.getMaxSeconds();

    public GameTurn() {
    	toolkit = Toolkit.getDefaultToolkit();
    }
    
    private void buildTimer() {
    	timer = new Timer();
        timer.schedule(new TimerTask() {
			@Override
			public void run() {
				setChanged();
			    notifyObservers();
				if (gameTimer-- == 0)
				{
					//stop();
					setGameTimer(GameConfig.getMaxSeconds());
				}
			}
    	}, GameConfig.getStartDelay(), GameConfig.getTimerPeriod());
    }

    public int getGameTimer() {
    	return gameTimer;
    }
    
    public void setGameTimer(int time) {
    	gameTimer = time;
    }
    
    public void stop() {
    	timer.cancel();
    }    
    
    public void start() {
    	buildTimer();
    }
    
    public void reset() {
    	gameTimer = GameConfig.getMaxSeconds();
    }
    
}