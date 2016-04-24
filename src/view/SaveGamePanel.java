package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.board.Square;
import utils.GameUtils;

public class SaveGamePanel extends JPanel{
	private JButton btnSaveGame;
	
	public SaveGamePanel() {
		// TODO Auto-generated constructor stub
		super();
		this.setLayout(new FlowLayout(FlowLayout.CENTER));

		btnSaveGame = new JButton("Save game");
		btnSaveGame.setPreferredSize(new Dimension(158, 50));
		btnSaveGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO call the end-turn method from the controller
				doSave();
			}
		});
		
		this.add(btnSaveGame);
	}
	
	private void doSave(){
		// TODO: need to specify what kind of object need to save here.
		// Object must be Serializable.
		GameUtils.getInstance().saveGame(new Square());		// TESTING ONLY
		DialogView.getInstance().showInformation("Save game successfully!");
	}
}
