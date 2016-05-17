package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controller.ActionController;
import model.board.Square;

public class SquareView extends JPanel implements MouseListener, Serializable {

	private ActionController _ac;
	private Color defaultBg = Color.WHITE;
	private Square sqrObj;

	public SquareView(Square o) {
		super();
		this.setSqrObj(o);
		this.setLayout(new BorderLayout());
		this.setBorder(new LineBorder(new Color(212, 212, 212), 1));

		/*
		 * Extensibility of MVC decoupling of Square model with View We are
		 * accessing the model not the view properties throughout the game So
		 * only need to assign MouseListener to the 'parent' and update the
		 * Model.
		 */

		_ac = ActionController.getInstance();
		buildView(o);
		addMouseListener(this);		
	}
	
	public void buildView(Square o) {
		this.removeAll();
		this.setBackground(Color.WHITE);
		this.setBackground(getBackgroundColor(o));
		addTeamPiece(o);
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
	public void mouseEntered(MouseEvent arg0) {
		if (this.sqrObj.getAccessible() && !_ac.getGameController().getAiTurn()) {
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

		if (_ac.getGameController().getAiTurn()) { 
			return; 
		}
		
		//Get mouse event button as integer
		_ac.setActionButton((Integer) arg0.getButton());
		
		//No piece active, move is void
		if (_ac.getActivePiece() == null && this.sqrObj.getOccupant() == null) {
			return;
		}
		
		//Check team
		if (this.sqrObj.getOccupant() != null && _ac.getActivePiece() == null) {
			if (this.sqrObj.getOccupant().getTeam() != _ac.getGameController().getCurrentTeam()) {
				String msg = "It is Team " + _ac.getGameController().getCurrentTeam() + "'s turn!";
				_ac.getGameController().getUiMediator().showDialog(arg0, msg);
				return;
			}
		}
		
		//Continue or commence current state
		if (_ac.getActivePiece() == null) {
			_ac.startAction(_ac, this.sqrObj);
		} else {
			_ac.endAction(_ac, this.sqrObj);
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {}

	public Square getSqrObj() {
		return sqrObj;
	}

	public void setSqrObj(Square sqrObj) {
		this.sqrObj = sqrObj;
	}

}
