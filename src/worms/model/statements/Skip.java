package worms.model.statements;

import worms.model.Worm;
import worms.model.expressions.SelfWormExpression;

public class Skip extends ActionStatement {

	@Override
	public String toString() {
		return "skip";
	}

	//FIXME: should use actionhandler
	@Override
	public void executeStatement() {
		Worm self = SelfWormExpression.getWorm();
		self.getWorld().startNextTurn();
	}

}
