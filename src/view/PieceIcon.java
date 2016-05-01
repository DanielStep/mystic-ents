package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.piece.Piece;

public class PieceIcon extends JPanel{

    private BufferedImage image;

    public PieceIcon(Piece pce) {    	
    
    	String icon = pce.getSkillSet().getCurrentSkill().getIcon();
	
		try {                
			image = ImageIO.read(new File("image name and path"));
		} catch (IOException ex) {
			// handle exception...
			System.out.println("Piece icon not found");
		}
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
    }

}
