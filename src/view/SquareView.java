package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class SquareView extends JPanel implements MouseListener {
	
	// Used for mapping with the back-end model
	private int[] ID = new int[2];
	
	public SquareView(int i, int j) {
		this.ID[0] = i;
		this.ID[1] = j;
		this.setLayout(new BorderLayout());
		this.setBorder(new LineBorder(Color.BLACK, 1));
		addMouseListener(this);
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
