package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.GameConfig;
import model.Game;
import model.Piece;

/**
 * Representation of piece. Eg. Usurper, regularpiece
 * 
 * @author skh, ms
 *
 */
public class PieceView extends JPanel {

	private static final long serialVersionUID = 1L;

	// Represents team.
	private Color color;
	private Piece piece;
	private Game gameUtils;
	private int size = 40;
	private JLabel label;

	PieceView(Piece pce) {
		gameUtils = Game.getInstance();
		size = (int) ((GameConfig.getDefaultHeight() / GameConfig.getROW_COL()) - 3.5);
		this.piece = pce;
		this.color = gameUtils.stringToColor(pce.getTeam().name(), Color.WHITE);
		addPieceLabel();
	}

	private void addPieceLabel() {
		label = new JLabel(buildLabelString(), JLabel.LEFT);
		label.setFont(new Font("Sans-serif", Font.PLAIN, (int) (size / 2.5)));
		if (piece.getIsUsurper()) {
			label.setForeground(this.color);
		} else {
			label.setForeground(Color.white);
		}
		this.add(label);
	}

	private String buildLabelString() {
		StringBuilder str = new StringBuilder();
		str.append(piece.getSkillSet().getCurrentSkill().getName().charAt(0));
		return str.toString();
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (piece.getIsUsurper()) {
			g2.setPaint(Color.white);
			g2.fillOval(0, 0, size, size);
			g2.setPaint(this.color);
			g2.setStroke(new BasicStroke(4));
			g.drawOval(2, 2, size - 4, size - 4);
		} else {
			g2.setPaint(this.color);
			g2.fillOval(0, 0, size, size);
		}
	}

}
