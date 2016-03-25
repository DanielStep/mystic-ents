package view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * A component of ControlPanel
 * @author Phan Vo
 *
 */
public class AvailablePiecePanel extends JPanel{
	private JTextField tfPieceRemaining;
	
	public AvailablePiecePanel() {
		// TODO Auto-generated constructor stub
		super();
		
	    tfPieceRemaining = new JTextField(10);
	    tfPieceRemaining.setHorizontalAlignment(JTextField.CENTER);
	    tfPieceRemaining.setText("8");
	    tfPieceRemaining.setEditable(false);

	    TitledBorder titled = new TitledBorder("Available pieces");
	    tfPieceRemaining.setBorder(titled);

	    this.add(tfPieceRemaining);
	}
	
	public void setAvailablePieces(int num) {
		tfPieceRemaining.setText(String.valueOf(num));
	}
}
