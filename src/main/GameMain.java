package main;

import controller.*;

public class GameMain {

	static GameController gameController;
	static BoardController boardController;
	static PieceActionController pieceActionController;	

	public static void main(String[] args) {

		//INSTANTIATE ALL CONTROLLERS 
		boardController = new BoardController();
		pieceActionController = PieceActionController.getInstance();
		gameController = new GameController();
		
		//Assign GameController to pieceActionController
		pieceActionController.setGameController(gameController);
		pieceActionController.setBoardController(boardController);
		gameController.setBoardController(boardController);
		//gameController.setPieceActionController(pieceActionController);	
		//boardController.setPieceActionController(pieceActionController);
		
		System.out.println("All setters allocated");
				
	}
	
	
}
