package worms.model.statements;

import worms.model.expressions.Expression;

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
		System.out.println(print1.getValue().getValue());
		this.executed=true;
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
