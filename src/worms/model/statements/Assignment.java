package worms.model.statements;

import worms.model.Worm;
import worms.model.expressions.*;
import worms.model.types.Entity;
import worms.model.types.Type;

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

		Worm self = SelfWormExpression.getWorm();
		self.getProgram().getGlobals().put(variableName,rhs.getValue());
	}
	
	private final String variableName;
	
	private final Expression rhs;

}
