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
		this.executed=false;
		if (!(SelfWormExpression.getWorm().getProgram().getstatementCount()>=1000)){
			if (print1.getValue()==null){
				System.out.println("null");
			    this.executed=true;
			    SelfWormExpression.getWorm().getProgram().increaseCount();
			    return;}
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
