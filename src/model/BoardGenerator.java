package model;

public class BoardGenerator {

	
	public Object[][] generateStartBoard() {
		
		Object[][] boardData = new Object[GameConfig.getRowCol()][GameConfig.getRowCol()];
		
		
		for (int i = 0; i < boardData.length; i++) {
			for (int j = 0; j < boardData[i].length; j++) {
				boardData[i][j] = null;// blank
				
				
				if(i==2&&j==3){
					boardData[i][j] = 'x';
				}

				//System.out.println(".");
				boardData[i][j] = '.';
			}
		}
		
		
		return boardData;
		
	}
	
}
