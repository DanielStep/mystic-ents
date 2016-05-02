package model.piece;

public enum Team {
	BLUE(93, 203, 240),
	RED(242, 116, 116),
	YELLOW(255, 230, 64),
	GREEN(124, 247, 52);

	private Team(final Integer red, final Integer green, final Integer blue) {
	    this.red = red;
	    this.green = green;
	    this.blue = blue;
	}
	
	private final Integer red, green, blue;
	
    public int getRed(){
        return red;
    }

    public int getGreen(){
        return green;
    }

    public int getBlue(){
        return blue;
    }

}