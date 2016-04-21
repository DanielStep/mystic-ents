package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.BoardController;
import controller.GameController;
import controller.PieceActionController;
import utils.GameConfig;

/**
 * Create main menu at the start
 * @author Phan Vo
 *
 */

public class MainMenuFrame extends JFrame{
	static GameController gameController;
	static BoardController boardController;
	static PieceActionController pieceActionController;
	
	public MainMenuFrame() {
		super();
		buildFrame();
		buildUI();
	}

	/**
	 * Adding and configuring properties of the frame
	 */
	private void buildFrame() {
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// adjust size using Dimension.
		this.getContentPane().setPreferredSize(new Dimension(200, 300));

		this.setResizable(false);
		this.setVisible(true);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, 
				dim.height/2-this.getSize().height-200);
	}

	/**
	 * Creating the main menu UI
	 */
	private void buildUI() {
		JPanel menuPanel = new JPanel(new GridLayout(0, 1));
		int margin = 10;
		menuPanel.setBorder(new EmptyBorder(margin, margin, margin, margin));
		
		JLabel lbTitle = new JLabel(GameConfig.GAME_TITLE, SwingConstants.CENTER);
		lbTitle.setFont(new Font("Serif", Font.BOLD, 22));
		menuPanel.add(lbTitle);
		
		JButton btNewGame = new JButton("New Game");
		btNewGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				doCloseFrame();
				doNewGame();
			}
		});
		menuPanel.add(btNewGame);
		
		JButton btContinue = new JButton("Continue");
		btContinue.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				doCloseFrame();
				doContinue();
			}
		});
		menuPanel.add(btContinue);
		
		JButton btOption = new JButton("Option");
		btOption.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new OptionFrame();
			}
		});
		menuPanel.add(btOption);
		
		JButton btExit = new JButton("Exit");
		btExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				doCloseFrame();
				System.exit(0);
			}
		});
		menuPanel.add(btExit);
		
		this.add(menuPanel);
		this.pack();
	}
	
	private void doCloseFrame(){
		this.dispose();
	}
	
	private void doNewGame(){
		//INSTANTIATE ALL CONTROLLERS 
		boardController = new BoardController();
		gameController = new GameController();
		pieceActionController = new PieceActionController();
		
		//Assign GameController to pieceActionController
		pieceActionController.setGameController(gameController);
		gameController.setBoardController(boardController);
		gameController.setPieceActionController(pieceActionController);	
		boardController.setPieceActionController(pieceActionController);
		
		//BoardController's first task is to load map data
		//From there GameController can generate teams
		//When done, we start.
		gameController.init();
		boardController.buildBoard();
		gameController.startTimer();
	}
	
	private void doContinue(){
		// TODO: load game here
		System.out.println("Load Game from the previous save...");
	}
}
