package view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.GameConfig;
/**
 * A component of ControlPanel
 * @author Phan Vo
 *
 */
public class TimePanel extends JPanel{
	
	private JTextField tfTime;
	
	public TimePanel() {
		// TODO Auto-generated constructor stub
		super();
		
	    tfTime = new JTextField(10);
	    tfTime.setHorizontalAlignment(JTextField.CENTER);
	    tfTime.setText("...");
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
