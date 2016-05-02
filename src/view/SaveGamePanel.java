package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.board.BoardData;
import model.board.Square;
import utils.GameUtils;

/**
 * A component of Control Panel: display save game button to save the current game
 * @author Phan Vo
 *
 */
public class SaveGamePanel extends JPanel implements Serializable {
	private JButton btnSaveGame;
	
	private BoardData boardData;
	
	public SaveGamePanel() {
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
		boardData = BoardData.getInstance();		
		if (GameUtils.getInstance().saveGameData(boardData)) {
			DialogView.getInstance().showInformation("Save game successfully!");			
		};

	}
}
