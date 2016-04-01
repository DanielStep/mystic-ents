package view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import model.Piece;

/**
 * A component of ControlPanel
 * @author Phan Vo
 *
 */
public class PieceInfoPanel extends JPanel{
	private JLabel lblHealth, lblHealthValue;
	private JLabel lblAttackDamage, lblAttackDamageValue;
	private JLabel lblMove, lblMoveValue;
	private JLabel lblSkill, lblSkillValue;
	
	public PieceInfoPanel() {
		// TODO Auto-generated constructor stub
		super();
		JPanel pane = new JPanel(new GridLayout(0, 2));
		pane.setPreferredSize(new Dimension(160, 160));
		
		lblHealth = new JLabel("Health: ");
		lblHealthValue = new JLabel("16");

		lblAttackDamage = new JLabel("Attack: ");
		lblAttackDamageValue = new JLabel("5");
		
		lblMove = new JLabel("Move: ");
		lblMoveValue = new JLabel("3");
		
		lblSkill = new JLabel("Skill: ");
		lblSkillValue = new JLabel("Heal");
		
		pane.add(lblHealth);
		pane.add(lblHealthValue);
		pane.add(lblAttackDamage);
		pane.add(lblAttackDamageValue);
		pane.add(lblMove);
		pane.add(lblMoveValue);
		pane.add(lblSkill);
		pane.add(lblSkillValue);
		
	    TitledBorder titled = new TitledBorder("Selected Piece");
	    pane.setBorder(titled);

	    this.add(pane);
	}
	
	public void updatePieceInformation(Piece pce) {
		System.out.println("Piece: " + pce);
	}
}
