package worms.model.statements;

import worms.gui.game.IActionHandler;
import worms.model.Worm;
import worms.model.expressions.*;

public class Fire extends ActionStatement {
	
	public boolean executed=false;
	
	public Fire(Expression yield) throws IllegalArgumentException {
		if (! (yield instanceof DoubleExpression))
			throw new IllegalArgumentException();
		this.yield = (DoubleExpression) yield;
	}

	@Override
	public String toString() {
		return "fire " + yield.toString();
	}

	@Override
	public void executeStatement() {
		if (!(SelfWormExpression.getWorm().getProgram().getstatementCount()>=1000)){
		
			this.executed=false;
			Worm self = SelfWormExpression.getWorm();
			IActionHandler handler = self.getProgram().getHandler();
			handler.fire(self, getActualYield());
			self.getProgram().increaseCount();
			this.executed=true;}
	}
	
	private final DoubleExpression yield;
	
	private int getActualYield() {
		return (int) Math.floor(yield.getValue().getValue());
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
		System.out.println("SCHIETAP");
		System.out.println(SelfWormExpression.getWorm().getShootAP());
		return SelfWormExpression.getWorm().getShootAP()>=0;
	}

}
