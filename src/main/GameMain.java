package main;

import controller.GameController;

public class GameMain implements Runnable {
	

	public static void main(String[] args) {		
		
		//TODO: 1. GameController
		GameController gameController = new GameController();	
		gameController.control();
		

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
