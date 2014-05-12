package worms.model.statements;

import worms.gui.game.IActionHandler;
import worms.model.Worm;
import worms.model.expressions.SelfWormExpression;

public class Turn extends ActionStatement {
	
	public Turn(double angle) {
		this.angle = angle;
	}

	@Override
	public String toString() {
		return "turn " + Double.toString(angle);
	}

	@Override
	public void executeStatement() {
		Worm self = SelfWormExpression.getWorm();
		IActionHandler handler = self.getProgram().getHandler();
		handler.turn(self, angle);
	}
	
	private final double angle;

}
