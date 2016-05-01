package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.piece.Piece;

public class PieceIcon extends JPanel{

    private BufferedImage image;
    private int size;

    public PieceIcon(Piece pce, int size) {
    
    	String icon = pce.getSkillSet().getCurrentSkill().getIcon();
    	
    	//System.out.println(icon);
	
		try {                
			image = ImageIO.read(new File("./src/view/icons/"+icon));
		} catch (IOException ex) {
			// handle exception...
			System.out.println("Piece icon not found");
		}
    }

    @Override
    protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
        super.paintComponent(g);
        //g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters    
		
        
		
		
        Double scale = (double) (size/100);
		
        System.out.println("Size: " + scale);
		
        AffineTransform oldXform = g2.getTransform(); 
        g2.scale(0.3, 0.3); 
        g2.drawImage(image, 0, 0, null);    
    }

}
