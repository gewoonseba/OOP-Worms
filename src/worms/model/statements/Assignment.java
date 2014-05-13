package worms.model.statements;

import worms.model.Worm;
import worms.model.expressions.*;
import worms.model.types.Entity;

public class Assignment<E> extends Statement {
	
	public Assignment(String variableName, Expression rhs){
		this.variableName = variableName;
		this.rhs = rhs;
	}

	@Override
	public String toString() {
		return variableName + " := " + rhs.toString();
	}
	
	public void executeStatement() {
		Object value = null;
		if (rhs instanceof DoubleExpression)
			value = ((DoubleExpression) rhs).getValue();
		else if (rhs instanceof BooleanExpression)
			value = ((BooleanExpression) rhs).getValue();
		else if (rhs instanceof EntityExpression)
			value = ((EntityExpression) rhs).getValue();
		Worm self = SelfWormExpression.getWorm();
		self.getProgram().getGlobals().put(variableName,value);
	}
	
	private final String variableName;
	
	private final Expression rhs;

}
