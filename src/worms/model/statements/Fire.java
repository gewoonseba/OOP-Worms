package worms.model.statements;

import worms.gui.game.IActionHandler;
import worms.model.Worm;

public class Fire extends ActionStatement {
	
	public Fire(int yield) {
		this.yield = yield;
	}

	@Override
	public String toString() {
		return "fire " + Integer.toString(yield);
	}

	@Override
	public void executeStatement() {
		Worm self = SelfWormExpression.getWorm();
		IActionHandler handler = self.getProgram().getHandler();
		handler.fire(self, yield);
	}
	
	private final int yield;

}
