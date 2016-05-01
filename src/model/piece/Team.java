package model.piece;

public enum Team {
	ONE(250, 186, 182),
	TWO(182, 239, 250),
	THREE(248, 250, 182),
	FOUR(209, 250, 182);

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