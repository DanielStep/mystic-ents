package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.piece.Piece;
import model.piece.Team;
import utils.BoardUtils;
import utils.GameConfig;

/**
 * Representation of piece. Eg. Usurper, regularpiece
 * 
 * @author skh, ms
 *
 */
public class PieceView extends JPanel implements Serializable{

	private static final long serialVersionUID = 1L;

	// Represents team.
	private Color color;
	private Piece piece;
	private BufferedImage image;
	private BoardUtils boardUtils;
	private int size;
	private JLabel label;

	public PieceView(Piece pce) {
		boardUtils = BoardUtils.getInstance();
		size = (int) ((GameConfig.getDefaultHeight() / GameConfig.getROW_COL()));
		this.piece = pce;
		
		//Get color details from Piece's Team class
		Team team = (Team) pce.getTeam();
		this.color = new Color(team.getRed(),team.getGreen(),team.getBlue());

		addPieceIcon();		
		//addPieceLabel();
	}

	private void addPieceIcon() {
    	String icon = piece.getIcon();
		try {                
			image = ImageIO.read(new File("./src/view/icons/"+icon));
		} catch (IOException ex) {
			// handle exception...
			System.out.println("Piece icon not found");
		}		
	}
	
	/*private void addPieceLabel() {
		label = new JLabel(buildLabelString(), JLabel.LEFT);
		label.setFont(new Font("Sans-serif", Font.PLAIN, (int) (size / 2.5)));
		if (piece.getIsUsurper()) {
			label.setForeground(this.color);
		} else {
			label.setForeground(Color.white);
		}
		this.add(label);
	}*/

	private String buildLabelString() {
		StringBuilder str = new StringBuilder();
		str.append(piece.getSkillSet().getCurrentSkill().getName().charAt(0));
		return str.toString();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(this.color);
		g2.fillRect(0, 0, size, size);

		int isize = 210;		
        Double scale = ((double) size) / isize;
        
        AffineTransform oldXform = g2.getTransform(); 
        g2.scale(scale, scale); 
        g2.drawImage(image, 0, 0, null);   
        
		//Need to do this polymorphically
		
		/*if (piece.getIsUsurper()) {
			g2.setPaint(Color.white);
			g2.fillOval(0, 0, size, size);
			g2.setPaint(this.color);
			g2.setStroke(new BasicStroke(4));
			g.drawOval(2, 2, size - 4, size - 4);
		} else {
			g2.setPaint(this.color);
			g2.fillOval(0, 0, size, size);
		}*/
	}

}
