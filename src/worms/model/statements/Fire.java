package worms.model.statements;

import worms.gui.game.IActionHandler;
import worms.model.Worm;
import worms.model.expressions.*;

public class Fire extends ActionStatement {
	
	public Fire(DoubleExpression yield) throws IllegalArgumentException {
		if (! (yield instanceof DoubleExpression))
			throw new IllegalArgumentException();
		this.yield = yield;
	}

	@Override
	public String toString() {
		return "fire " + yield.toString();
	}

	@Override
	public void executeStatement() {
		Worm self = SelfWormExpression.getWorm();
		IActionHandler handler = self.getProgram().getHandler();
		handler.fire(self, getActualYield());
	}
	
	private final DoubleExpression yield;
	
	private int getActualYield() {
		return (int) Math.floor(yield.getValue().getValue());
	}

}
