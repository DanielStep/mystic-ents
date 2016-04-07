package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.lang.reflect.Field;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controller.PieceActionController;
import model.GameUtils;
import model.Square;

public class SquareView extends JPanel implements MouseListener {

	private PieceActionController pac;
	private Color defaultBg = Color.WHITE;
	public Square sqrObj;

	public SquareView(PieceActionController p, Square o) {
		super();
		pac = p;
		this.sqrObj = o;
		this.setLayout(new BorderLayout());
		this.setBorder(new LineBorder(Color.BLACK, 1));
		this.setBackground(getBackgroundColor(o));
		
		/*
		 * Extensibility of MVC decoupling of Square model with View
		 * We are accessing the model not the view properties throughout the game
		 * So only need to assign MouseListener to the 'parent' and
		 * update the Model. 
		 */
		addTeamPiece(o);
		addMouseListener(this);
	}

	private void addTeamPiece(Square o) {		
		if (o.getOccupant() == null) {
			return;
		}
		PieceView pce = new PieceView(o.getOccupant());
		this.add(pce);
	}
	
	private Color getBackgroundColor(Square o) {
		Color bg = defaultBg;
		bg = o.getInrange() ? Color.YELLOW : bg;
		bg = !o.getAccessible() ? Color.BLACK : bg;
		bg = o.getTeamTower() ? Color.GREEN : bg;	
		return bg;
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
		pac.performAction(arg0, this);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}

