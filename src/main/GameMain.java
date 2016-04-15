package main;

import controller.*;

public class GameMain implements Runnable {
	
	static GameController gameController;
	static BoardController boardController;
	static PieceActionController pieceActionController;

	public static void main(String[] args) {

		//INSTANTIATE ALL CONTROLLERS 
		boardController = BoardController.getInstance();
		gameController = GameController.getInstance();
		pieceActionController = PieceActionController.getInstance();
		
		//Assign GameController to pieceActionController
		pieceActionController.setGameController(gameController);
		gameController.setBoardController(boardController);
		gameController.setPieceActionController(pieceActionController);	
		boardController.setPieceActionController(pieceActionController);
		
		//BoardController's first task is to load map data
		//From there GameController can generate teams
		//When done, we start.
		boardController.init();
		gameController.init();
		boardController.buildBoard();
		gameController.startTimer();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	}
	
	public GameController getGameController() {
		return gameController;
	}

	public BoardController getBoardController() {
		return boardController;
	}
	
	public PieceActionController getPieceActionController() {
		return pieceActionController;
	}

}
