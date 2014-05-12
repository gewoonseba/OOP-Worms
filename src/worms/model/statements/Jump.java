package worms.model.statements;

import worms.gui.game.IActionHandler;
import worms.model.Worm;
import worms.model.expressions.SelfWormExpression;

public class Jump extends ActionStatement {
	
	public Jump(){
		
	}
	
	@Override
	public String toString() {
		return "jump";
	}

	@Override
	public void executeStatement() {
		Worm self = SelfWormExpression.getWorm();
		IActionHandler handler = self.getProgram().getHandler();
		handler.jump(self);
	}

}
