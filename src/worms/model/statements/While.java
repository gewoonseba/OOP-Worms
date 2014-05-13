package worms.model.statements;

import worms.model.expressions.*;

public class While extends Statement {
	 
	private Expression<?> condition;
	private Statement body;
	
	public While(Expression<?> condition,Statement body) {
		this.condition = condition;
		this.body = body;
	}

	@Override
	public void executeStatement(){
		int n = 0;
	    while (getActualCondition() || n<1000){
	    	body.executeStatement();
	        n+=1;
	    }
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private boolean getActualCondition() {
		return (Boolean) condition.getValue();
	}

}
