package view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
/**
 * A component of ControlFrame
 * @author Phan Vo
 *
 */
public class TimePanel extends JPanel{
	
	private JTextField tfTime;
	
	public TimePanel() {
		// TODO Auto-generated constructor stub
		super(true);
		
	    tfTime = new JTextField(10);
	    tfTime.setHorizontalAlignment(JTextField.CENTER);
	    tfTime.setText("10");
	    tfTime.setEditable(false);

	    TitledBorder titled = new TitledBorder("Timer");
	    tfTime.setBorder(titled);

	    this.add(tfTime);
	}
	
	/**
	 * update time countdown
	 * @param time
	 */
	public void setTime(int time) {
		tfTime.setText(String.valueOf(time));
	}
}
