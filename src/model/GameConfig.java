package model;

public class GameConfig {

	//TIMER
    private static final int MAX_TURNS = 1000;
    private static final int START_DELAY = 1000;
    private static final int TIMER_PERIOD = 2000;
    
    //BOARD
    private static final int ROW_COL = 30;
	private static final int DEFAULT_WIDTH = 700;
	private static final int DEFAULT_HEIGHT = 700;
	
	
	public static int getDefaultHeight() {
		return DEFAULT_HEIGHT;
	}

	public static int getTimerPeriod() {
		return TIMER_PERIOD;
	}

	public static int getStartDelay() {
		return START_DELAY;
	}

	public static int getMaxTurns() {
		return MAX_TURNS;
	}

	public static int getDefaultWidth() {
		return DEFAULT_WIDTH;
	}

	public static int getRowCol() {
		return ROW_COL;
	}
	
}
