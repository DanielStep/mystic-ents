package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Representation of piece. Eg. Usurper, regularpiece
 * 
 * @author skh
 *
 */
public class PieceView extends JPanel {

	private static final long serialVersionUID = 1L;

	private int ID;

	// Represents team.
	private Color color;
	private boolean isUsurper; // TODO: change it to subtype
	private JLabel label;

	PieceView(Color c, boolean usurper) {
		this.color = c;
		label = new JLabel();
		label.setText(usurper ? "U" : "R");
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawOval(0, 0, 20, 20);
		g.setColor(this.color);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isUsurper() {
		return isUsurper;
	}

	public void setUsurper(boolean isUsurper) {
		this.isUsurper = isUsurper;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(String txt) {
		this.label.setText(txt);
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

}
