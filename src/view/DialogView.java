package view;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Create dialog view for displaying messages during the game
 * @author Phan Vo
 *
 */
public class DialogView {
	private static DialogView instance;
	
	private DialogView(){}
	
	public static synchronized DialogView getInstance() {
		if (instance == null) {
			instance = new DialogView();
		}
		return instance;
	}
	
	public void showInformation(String info, int x, int y) {
		final JOptionPane a = new JOptionPane(info, JOptionPane.INFORMATION_MESSAGE);
		final JDialog b = a.createDialog(null, "Information");
		b.setLocation(x, y);
		b.setVisible(true);
	}
	
	public void showInformation(String info) {
		JOptionPane.showMessageDialog(null, info, "Information", JOptionPane.INFORMATION_MESSAGE);
	}
}
