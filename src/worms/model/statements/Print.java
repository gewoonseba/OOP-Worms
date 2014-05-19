package worms.model.statements;

import worms.model.expressions.Expression;
import worms.model.expressions.SelfWormExpression;

public class Print extends Statement {
	
	public boolean executed=false;
	
	public Print (Expression print1) {
		this.print1 = print1;
	}

	@Override
	public String toString() {
		return "print" + print1.toString();
	}
	
	public void executeStatement(){
		if (!(SelfWormExpression.getWorm().getProgram().getstatementCount()>=1000)){
			this.executed=false;
			System.out.println(print1.getValue().getValue());
			SelfWormExpression.getWorm().getProgram().increaseCount();
			this.executed=true;}
	}
	
	private final Expression print1;
	
	@Override
	public boolean isexecuted() {
		
		return this.executed;
	}
	@Override
	public void setExecuted(boolean bool) {
		this.executed=bool;
		
	}
	
}
