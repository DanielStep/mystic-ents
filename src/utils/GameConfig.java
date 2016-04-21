package utils;

public class GameConfig {

	//TIMER
    private static final int MAX_TURNS = 1000;
    private static final int MAX_SECONDS = 10;
    private static final int START_DELAY = 1000;
    private static final int TIMER_PERIOD = 1000;
    
	//CONTROL PANEL
	private static final int DEFAULT_CONTROL_PANEL_WIDTH = 200;
    
    //BOARD
	private static final int DEFAULT_WIDTH = 700;
	private static final int DEFAULT_HEIGHT = 700;
	
	//TEAMS
	private static final int REGULARPIECES_IN_TEAM = 7;
	private static final int USURPERS_IN_TEAM = 1;
	
	//MAP
//	private static final String MAP_TEXTFILE = "./src/model/maps/map2.txt";
	private static String fileMapName = "map2";		// default map
    private static int ROW_COL;
    
    //SKILLS
	public static final int DAMAGETRAITMULTIPLIER = 2;
	public static final int RANGEMULTIPLIER = 2;
	public static final int HEALAMOUNT = 1;
    
    //CONSTANT STRINGS
	public static final String GAME_TITLE = "Mystic Ents";
	
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
		return "./src/model/maps/" + fileMapName + ".txt";
	}
	
	public static void setFileMapName(String fileName) {
		fileMapName = fileName;
	}
	
	public static String getFileMapName() {
		return fileMapName;
	}
	
	public static int getROW_COL() {
		return ROW_COL;
	}

	public static void setROW_COL(int val) {
		ROW_COL = val;
	}

	//TEAMS
	public static int getRegularpiecesInTeam() {
		return REGULARPIECES_IN_TEAM;
	}

	public static int getUsurpersInTeam() {
		return USURPERS_IN_TEAM;
	}
	
}
