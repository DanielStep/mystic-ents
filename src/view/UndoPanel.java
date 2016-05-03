package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import controller.BoardController;
import utils.GameConfig;

/**
 * A component of ControlPanel: undo a certain number of moves for both players
 * @author Phan Vo
 *
 */

public class UndoPanel extends JPanel{
	private JComboBox<String> cbNumberOfTurns;
	private JLabel lbTurn;
	private JButton btUndo;
	
	private BoardController boardController;
	
	public UndoPanel() {
		// TODO Auto-generated constructor stub
		super();
		
		JPanel pnMain = new JPanel(new FlowLayout());
		pnMain.setPreferredSize(new Dimension(160, 100));
		
		String[] turnsToUndo = {"1", "2", "3"};
		cbNumberOfTurns = new JComboBox<String>(turnsToUndo);
		cbNumberOfTurns.setPreferredSize(new Dimension(98, 26));
		((JLabel)cbNumberOfTurns.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		
		lbTurn = new JLabel("moves");
		
		btUndo = new JButton("Accept");
		btUndo.setPreferredSize(new Dimension(140, 36));
		btUndo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				doUndo();
			}
		});
		
		pnMain.add(cbNumberOfTurns);
		pnMain.add(lbTurn);
		pnMain.add(btUndo);
		
		TitledBorder titled = new TitledBorder("Undo");
		pnMain.setBorder(titled);

	    this.add(pnMain);
	    
	    //FOR undo
	    this.boardController = boardController;
	}
	
	public JButton getUndoButton(){
		return btUndo;
	}
	
	private void doUndo(){
		// TODO undo method here
		System.out.println("Undo " + 
							cbNumberOfTurns.getSelectedItem().toString() +
							" moves");
		
	}
}
