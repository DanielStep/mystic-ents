package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.GameUtils;
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
	private GameUtils gameUtils;
	private int size = 40;
	JLabel label;
	
	PieceView(Piece pce) {

		gameUtils = GameUtils.getInstance();
		
		this.piece = pce;
		this.color = gameUtils.stringToColor(pce.getTeam().name(), Color.WHITE);
		
		addPieceLabel();
    
	}
	
	private void addPieceLabel() {		
	    label = new JLabel(buildLabelString(), JLabel.LEFT);
	    label.setFont(new Font("Sans-serif", Font.PLAIN, 15));
	    if (piece.getIsUsurper()){
	    	label.setForeground(this.color);
	    } else {
	    	label.setForeground(Color.white);
	    }
	    this.add(label);
	}	
	
	private String buildLabelString() {		
		StringBuilder str = new StringBuilder();
		//str.append(piece.getSkillSet().getCurrentSkill().getName().charAt(0));
		return str.toString();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(this.color);
		if (piece.getIsUsurper()){
			g2.setStroke(new BasicStroke(4));
			g.drawOval(2, 2, size-4, size-4);
		} else {
			g2.fillOval(0, 0, size, size);		
		}
	}

}
