package main;

import controller.*;

public class GameMain {

	static GameController gameController;
	static BoardController boardController;
	static PieceActionController pieceActionController;	

	public static void main(String[] args) {

		//INSTANTIATE ALL CONTROLLERS 
		boardController = new BoardController();
		gameController = new GameController();
		pieceActionController = new PieceActionController();
		
		//Assign GameController to pieceActionController
		pieceActionController.setGameController(gameController);
		gameController.setBoardController(boardController);
		gameController.setPieceActionController(pieceActionController);	
		boardController.setPieceActionController(pieceActionController);
				
	}
	
	
}
