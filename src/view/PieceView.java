package view;

import java.awt.Color;

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

	// Represents team.
	private Color color;
	private boolean isUsurper; // TODO: change it subtype
	private JLabel label;

	PieceView(Color c, boolean usurper) {
		this.color = c;
		label.setText(usurper ? "U" : "R");
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

}
