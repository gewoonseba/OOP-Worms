package worms.model.statements;

import worms.model.Worm;
import worms.model.expressions.*;

public class Assignment extends Statement {
	
	public Assignment(String variableName, Expression rhs){
		this.variableName = variableName;
		this.rhs = rhs;
	}

	@Override
	public String toString() {
		return variableName + " := " + rhs.toString();
	}
	
	public void executeStatement() {
		Object value;
		if (rhs instanceof DoubleExpressions)
			value = ((DoubleExpressions) rhs).getValue();
		else if (rhs instanceof BooleanExpressions)
			value = ((BooleanExpressions) rhs).getBooleanValue();
		else if (rhs instanceof Entity)
			value = ((Entity) rhs).getEntity();
		Worm self = SelfWormExpression.getWorm();
		self.getProgram().getGlobals().put(variableName,value);
	}
	
	private final String variableName;
	
	private final Expression rhs;

}
