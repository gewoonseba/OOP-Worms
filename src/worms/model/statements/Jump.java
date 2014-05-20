package worms.model.statements;

import worms.gui.game.IActionHandler;
import worms.model.Worm;
import worms.model.expressions.SelfWormExpression;

public class Jump extends ActionStatement {
	
	public boolean executed=false;
	
	public Jump(){
		
	}
	
	@Override
	public String toString() {
		return "jump";
	}

	@Override
	public void executeStatement() {
		this.executed=false;
			if (!(SelfWormExpression.getWorm().getProgram().getstatementCount()>=1000)){
				Worm self = SelfWormExpression.getWorm();
				IActionHandler handler = self.getProgram().getHandler();
				handler.jump(self);
				self.getProgram().increaseCount();
				this.executed=true;}
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
