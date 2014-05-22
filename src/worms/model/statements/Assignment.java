package worms.model.statements;

import worms.model.Worm;
import worms.model.expressions.*;
import worms.model.types.Entity;
import worms.model.types.Type;

public class Assignment extends Statement {
	
	public boolean executed=false;
	
	public Assignment(String variableName, Expression rhs){
		this.variableName = variableName;
		this.rhs = rhs;
	}

	@Override
	public String toString() {
		return variableName + " := " + rhs.toString();
	}
	
	public void executeStatement() {
		if ((!(SelfWormExpression.getWorm().getProgram().getstatementCount()>=1000))){
        	this.executed=false;
        	Worm self = SelfWormExpression.getWorm();
        	if (rhs.getValue()==null){
        		self.getProgram().getGlobals().put(variableName,null);
        		self.getProgram().increaseCount();
            	this.executed=true;
            	return;}
        	Type right = rhs.getValue().clone();
        	self.getProgram().getGlobals().put(variableName,right);
        	self.getProgram().increaseCount();
        	this.executed=true;}
	}
	
	private final String variableName;
	
	private final Expression rhs;

	@Override
	public boolean isexecuted() {
		
		return this.executed;
	}

	@Override
	public void setExecuted(boolean bool) {
		this.executed=bool;
		
	}
	
	
	

}
