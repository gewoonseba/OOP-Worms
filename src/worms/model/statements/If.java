package worms.model.statements;

import worms.model.expressions.BooleanExpressions;

public class If extends Statement {
	
	private Statement ifCondition;
	private Statement elseCondition;
	private BooleanExpressions condition;
	
	public If(BooleanExpressions e,Statement a,Statement b) {
		ifCondition = a;
		elseCondition = b;
		condition= e;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void executeStatement(){
		if (condition.getValue())
			ifCondition.executeStatement();
		else
			elseCondition.executeStatement();

	}

}
