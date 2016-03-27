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
import model.Board;

public class SquareView extends JPanel implements MouseListener {

	// Used for mapping with the back-end model
	private int[] ID = new int[2];
	private Square sqrObj;

	public SquareView(int i, int j, Square o) {

		this.ID[0] = i;
		this.ID[1] = j;
		setSquare(o);

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
		return o.getBgColor();
	}

	public int[] getID() {
		return ID;
	}

	public void setID(int[] iD) {
		ID = iD;
	}
	
	public Square getSquare() {
		return sqrObj;
	}

	public void setSquare(Square o) {
		this.sqrObj = o;
	}	

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

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
		if (this.sqrObj.getOccupant() != null) {
			if (arg0.getButton() == MouseEvent.BUTTON1) {
				System.out.println("Detected Mouse Left Click @ " + this.sqrObj.getOccupant() + " : " + getID()[0] + " x " + getID()[1]);			
				// return square?
			} else if (arg0.getButton() == MouseEvent.BUTTON3) {
				System.out.println("Detected Mouse Right Click @ " + getID()[0] + " x " + getID()[1]);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
