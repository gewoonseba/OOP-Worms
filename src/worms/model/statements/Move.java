package worms.model.statements;

import worms.gui.game.IActionHandler;
import worms.model.Worm;

public class Move extends ActionStatement {
	
	public Move(){
		
	}

	@Override
	public String toString() {
		return "move";
	}

	@Override
	public void executeStatement() {
		Worm self = SelfWormExpression.getWorm();
		IActionHandler handler = self.getProgram().getHandler();
		handler.move(self);
	}

}
