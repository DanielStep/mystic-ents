package view;

import javax.swing.JOptionPane;

/**
 * Create dialog view for displaying messages during the game
 * @author Phan Vo
 *
 */
public class DialogView {
	private static DialogView instance = new DialogView();
	
	private DialogView(){}
	
	public static DialogView getInstance() {
		return instance;
	}
	
	public void showInformation(String info) {
		JOptionPane.showMessageDialog(null, info, "Information", JOptionPane.INFORMATION_MESSAGE);
	}
}
