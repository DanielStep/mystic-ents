package main;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import controller.*;

public class GameMain {

	static GameController gameController;
	static BoardController boardController;
	static ActionController pieceActionController;	
	static UIMediator uiMediator;	

	public static void main(String[] args) {

		//INSTANTIATE ALL CONTROLLERS 
		boardController = new BoardController();
		pieceActionController = ActionController.getInstance();
		uiMediator = UIMediator.getInstance();
		gameController = new GameController();		
		
		//Assign Controllers 
		pieceActionController.setGameController(gameController);
		pieceActionController.setBoardController(boardController);
		gameController.setBoardController(boardController);
		uiMediator.setBoardController(boardController);
		System.out.println("All Controllers constructed");

	}
	
	
}
