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
		if (!(SelfWormExpression.getWorm().getProgram().getstatementCount()>=1000)){
			Worm self = SelfWormExpression.getWorm();
			IActionHandler handler = self.getProgram().getHandler();
			handler.move(self);
			self.getProgram().increaseCount();
			if (SelfWormExpression.getWorm().getHitPoints()<=0)
				SelfWormExpression.getWorm().getProgram().stop();
			this.executed=true;
		}
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
