package worms.model.statements;

import worms.gui.game.IActionHandler;
import worms.model.Worm;
import worms.model.expressions.*;


public class Turn extends ActionStatement {
	
	public boolean executed=false;
	
	public Turn(Expression angle) {
		
		this.angle = angle;
	}

	@Override
	public String toString() {
		return "turn " + angle.toString();
	}

	@Override
	public void executeStatement() {
		if (!(SelfWormExpression.getWorm().getProgram().getstatementCount()>=1000)){
			this.executed=false;
			Worm self = SelfWormExpression.getWorm();
			IActionHandler handler = self.getProgram().getHandler();
			handler.turn(self, getActualAngle());
			self.getProgram().increaseCount();
			this.executed=true;}
	}
	
	private final Expression angle;
	
	private double getActualAngle() {
		return (Double) angle.getValue().getValue();
	}
	
	@Override
	public boolean isexecuted() {
		
		return this.executed;
	}
	@Override
	public void setExecuted(boolean bool) {
		this.executed=bool;
		
	}

	@Override
	public boolean enoughAp() {
		return SelfWormExpression.getWorm().canJumpAP();
	}
	
	
}
