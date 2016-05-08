package model.state;

import controller.ActionController;
import model.board.Square;

public interface IGameState {
	
	//switchPiece(ocpt);//manageSquare(sqrObj, ocpt);
	
	//attackPiece(sqrObj,ocpt);
	
	//movePiece(sqr.getSqrObj(), ocpt);
	
	//performPieceSkill(sqrObj, ocpt);
	
   public abstract void startAction(ActionController a, Square s);
   
   public abstract void endAction(ActionController a, Square s);
	
}
