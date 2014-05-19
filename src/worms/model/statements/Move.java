package worms.model.statements;

import worms.gui.game.IActionHandler;
import worms.model.Worm;
import worms.model.expressions.SelfWormExpression;

public class Move extends ActionStatement {
	
	public boolean executed=false;
	
	public Move(){
		
	}

	@Override
	public String toString() {
		return "move";
	}

	@Override
	public void executeStatement() {
		this.executed=false;
		Worm self = SelfWormExpression.getWorm();
		IActionHandler handler = self.getProgram().getHandler();
		handler.move(self);
		this.executed=true;
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
		return SelfWormExpression.getWorm().canMoveAP();
	}
	
	
}
