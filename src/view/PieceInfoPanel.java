package view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import model.Piece;

/**
 * A component of ControlPanel: Display essential piece info in selection
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
		lblHealthValue = new JLabel("-");

		lblAttackDamage = new JLabel("Attack: ");
		lblAttackDamageValue = new JLabel("-");
		
		lblMove = new JLabel("Move: ");
		lblMoveValue = new JLabel("-");
		
		lblSkill = new JLabel("Skill: ");
		lblSkillValue = new JLabel("-");
		
		pane.add(lblHealth);
		pane.add(lblHealthValue);
		pane.add(lblAttackDamage);
		pane.add(lblAttackDamageValue);
		pane.add(lblMove);
		pane.add(lblMoveValue);
		pane.add(lblSkill);
		pane.add(lblSkillValue);
		
	    TitledBorder titled = new TitledBorder("Selected piece");
	    pane.setBorder(titled);

	    this.add(pane);
	}
	
	/**
	 * Reset piece info value by default if no piece is selected/ change player turn
	 */
	public void resetPieceInformation() {	
		lblHealthValue.setText("-");
		lblAttackDamageValue.setText("-");		
		lblMoveValue.setText("-");
		lblSkillValue.setText("-");
	}
	
	/**
	 * Retrieve piece data from the Piece model
	 * @param pce
	 */
	public void updatePieceInformation(Piece pce) {
		lblHealthValue.setText(String.valueOf(pce.getTraitSet().getHealthTrait().getTraitValue()));
		lblAttackDamageValue.setText(String.valueOf(pce.getTraitSet().getDamageTrait().getTraitValue()));		
		lblMoveValue.setText(String.valueOf(pce.getTraitSet().getRangeTrait().getTraitValue()));
		lblSkillValue.setText(pce.getSkillSet().getCurrentSkill().getName());
	}
}
