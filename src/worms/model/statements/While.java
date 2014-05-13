package worms.model.statements;

import worms.model.expressions.BooleanExpression;
import worms.model.expressions.BooleanExpressions;

public class While extends Statement {
	 
	private BooleanExpression condition;
	private Statement body;
	
	public While(BooleanExpression e,Statement s) {
		e = condition;
		s = body;
	}

	@Override
	public void executeStatement(){
		int n = 0;
	    while (condition.getValue()||n<1000){
	    	body.executeStatement();
	        n+=1;
	    }
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
