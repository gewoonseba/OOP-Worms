package worms.model.statements;

import worms.model.Worm;
import worms.model.expressions.SelfWormExpression;

public class Skip extends ActionStatement {
	
	public boolean executed=false;

	@Override
	public String toString() {
		return "skip";
	}

	//FIXME: should use actionhandler
	@Override
	public void executeStatement() {
		this.executed=false;
		SelfWormExpression.getWorm().setCurrentAP(0);
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
		return true;
	}
	
}
