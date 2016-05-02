package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controller.ActionController;
import main.GameMain;
import model.board.Square;

public class SquareView extends JPanel implements MouseListener, Serializable {

	private ActionController pac;
	private Color defaultBg = Color.WHITE;
	private Square sqrObj;

	public SquareView(Square o) {
		super();
		this.setSqrObj(o);
		this.setLayout(new BorderLayout());
		this.setBorder(new LineBorder(new Color(212, 212, 212), 1));
		this.setBackground(getBackgroundColor(o));

		/*
		 * Extensibility of MVC decoupling of Square model with View We are
		 * accessing the model not the view properties throughout the game So
		 * only need to assign MouseListener to the 'parent' and update the
		 * Model.
		 */		

		pac = ActionController.getInstance();
				
		addTeamPiece(o);
		addMouseListener(this);
	}

	private void addTeamPiece(Square o) {
		if (o.getOccupant() == null) {
			return;
		} else if (o.getOccupant().getInPlay() == true) {
			PieceView pce = new PieceView(o.getOccupant());			
			this.add(pce);
		}
	}

	private Color getBackgroundColor(Square o) {
		Color bg = defaultBg;
		bg = o.getInRange() ? Color.YELLOW : bg;
		if (!o.getAccessible()) {
			bg = Color.BLACK;
			this.setBorder(new LineBorder(Color.BLACK, 1));
		}
		bg = o.getTeamTower() != null ? Color.GREEN : bg;
		return bg;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (this.sqrObj.getAccessible()) {
			this.setBorder(new LineBorder(new Color(255, 0, 0), 1));
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (this.sqrObj.getAccessible()) {
			this.setBorder(new LineBorder(new Color(212, 212, 212), 1));
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		pac.performAction(arg0, this);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public Square getSqrObj() {
		return sqrObj;
	}

	public void setSqrObj(Square sqrObj) {
		this.sqrObj = sqrObj;
	}

}
