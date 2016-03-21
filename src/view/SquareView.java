package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import model.Square;

public class SquareView extends JPanel implements MouseListener {
	
	// Used for mapping with the back-end model
	private int[] ID = new int[2];
	
	public SquareView(int i, int j, Square o) {

		this.ID[0] = i;
		this.ID[1] = j;
				
		this.setLayout(new BorderLayout());
		this.setBorder(new LineBorder(Color.BLACK, 1));		
		this.setPreferredSize(new Dimension(20, 20));
		
		/*
		 * Elements based on Square model:
		 */
		
		this.setBackground(getBackgroundColor(o));
		
		addMouseListener(this);
	}
	
	private Color getBackgroundColor(Square o) {
		Color bg = Color.WHITE;
		bg = (!o.getAccessible() ? Color.BLACK : bg);
		bg = (o.getTeamTower() ? Color.GREEN : bg);
		bg = (o.getTeamPiece() ? Color.BLUE : bg);
		return bg;
	}

	public int[] getID() {
		return ID;
	}

	public void setID(int[] iD) {
		ID = iD;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("mouse press @ " + getID()[0] + " x " + getID()[1]);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
