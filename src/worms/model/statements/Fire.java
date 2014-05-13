package worms.model.statements;

import worms.gui.game.IActionHandler;
import worms.model.Worm;
import worms.model.expressions.*;

public class Fire extends ActionStatement {
	
	public Fire(Expression<?> yield) throws IllegalArgumentException {
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
	
	private final Expression<?> yield;
	
	private int getActualYield() {
		return (Integer) yield.getValue();
	}

}
