package utils;

public class GameConfig {
	
	//CONSTANT STRINGS
	public static final String GAME_TITLE = "Mystic Ents";
	public static final String SAVE_GAME_FILE = "savegame.dat";

	//TIMER
    private static final int MAX_TURNS = 1000;
    private static final int MAX_SECONDS = 10;
    private static final int START_DELAY = 1000;
    private static final int TIMER_PERIOD = 1000;
    
	//CONTROL PANEL
	private static final int DEFAULT_CONTROL_PANEL_WIDTH = 200;
    
    //BOARD
	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 800;
		
	//MAP
//	private static final String MAP_TEXTFILE = "./src/model/maps/map2.txt";
	private static String DEFAULTMAP = "map2";		// default map
    private static int ROW_COL;
    
    //SKILLS
	private static final int DAMAGETRAITMULTIPLIER = 2;
	private static final int RANGEMULTIPLIER = 2;
	private static final int HEALAMOUNT = 1;	
	
	
	//GAME TIMER
	public static int getTimerPeriod() {
		return TIMER_PERIOD;
	}

	public static int getMaxSeconds() {
		return MAX_SECONDS;
	}
	
	public static int getStartDelay() {
		return START_DELAY;
	}

	public static int getMaxTurns() {
		return MAX_TURNS;
	}
	
	//CONTROL PANEL	
	public static int getDefaultControlPanelWidth() {
		return DEFAULT_CONTROL_PANEL_WIDTH;
	}
	
    //BOARD
	public static int getDefaultHeight() {
		return DEFAULT_HEIGHT;
	}

	public static int getDefaultWidth() {
		return DEFAULT_WIDTH;
	}
	
	//MAP
	public static String getMapTextfile() {
		return "./src/model/maps/" + DEFAULTMAP + ".txt";
	}
	
	public static void setFileMapName(String fileName) {
		DEFAULTMAP = fileName;
	}
	
	public static String getFileMapName() {
		return DEFAULTMAP;
	}
	
	public static int getROW_COL() {
		return ROW_COL;
	}

	public static void setROW_COL(int val) {
		ROW_COL = val;
	}

    //SKILLS
	public static int getDamagetraitmultiplier() {
		return DAMAGETRAITMULTIPLIER;
	}

	public static int getRangemultiplier() {
		return RANGEMULTIPLIER;
	}

	public static int getHealamount() {
		return HEALAMOUNT;
	}
	
}
