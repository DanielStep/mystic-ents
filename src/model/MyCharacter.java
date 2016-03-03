package model;

import java.awt.Color;

/**
 * Character used in the game
 * 
 * @author skh
 *
 */

public class MyCharacter {
	/** Current x coordinate **/
	private int cX;
	/** Current x coordinate **/
	private int cY;

	private Color cColor;

	/**
	 * Creates new MyCharacter object.
	 */
	public MyCharacter(int x, int y, Color color) {
		// setup the starting coordinates.
		cX = x;
		cY = y;
		cColor = color;
	}

	public int getcX() {
		return cX;
	}

	public void setcX(int cX) {
		this.cX = cX;
	}

	public int getcY() {
		return cY;
	}

	public void setcY(int cY) {
		this.cY = cY;
	}

	public Color getcColor() {
		return cColor;
	}

	public void setcColor(Color cColor) {
		this.cColor = cColor;
	}
}
