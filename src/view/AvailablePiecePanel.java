package view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * A component of ControlPanel: Display the remaining pieces of the current team
 * @author Phan Vo
 *
 */
public class AvailablePiecePanel extends JPanel{
	private JTextField tfPieceRemaining;
	
	public AvailablePiecePanel() {
		// TODO Auto-generated constructor stub
		super();
		
	    tfPieceRemaining = new JTextField(12);
	    tfPieceRemaining.setHorizontalAlignment(JTextField.CENTER);
	    tfPieceRemaining.setText("0");
	    tfPieceRemaining.setEditable(false);

	    TitledBorder titled = new TitledBorder("Available pieces");
	    tfPieceRemaining.setBorder(titled);

	    this.add(tfPieceRemaining);
	}
	
	public void setAvailablePieces(int num) {
		tfPieceRemaining.setText(String.valueOf(num));
	}
}
