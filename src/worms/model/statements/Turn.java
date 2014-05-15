package worms.model.statements;

import worms.gui.game.IActionHandler;
import worms.model.Worm;
import worms.model.expressions.*;


public class Turn extends ActionStatement {
	
	public Turn(DoubleExpression angle) {
		if (!(angle instanceof DoubleExpression))
			throw new IllegalArgumentException();
		this.angle = angle;
	}

	@Override
	public String toString() {
		return "turn " + angle.toString();
	}

	@Override
	public void executeStatement() {
		Worm self = SelfWormExpression.getWorm();
		IActionHandler handler = self.getProgram().getHandler();
		handler.turn(self, getActualAngle());
	}
	
	private final DoubleExpression angle;
	
	private double getActualAngle() {
		return (Double) angle.getValue().getValue();
	}

}
