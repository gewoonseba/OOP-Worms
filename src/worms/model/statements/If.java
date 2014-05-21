package worms.model.statements;


import java.util.ArrayList;
import java.util.List;

import worms.model.expressions.*;


public class If extends Statement {
	
	public boolean executed=false;
	private Statement then;
	private Statement otherwise;
	private Expression condition;
	
	public If(Expression condition,Statement then,Statement otherwise) {
		
		this.condition =condition;
		this.then = then;
		this.otherwise = otherwise;
	}

	@Override
	public String toString() {
		return ("if(" + condition.toString() + ")" + "\n" +"\t" + then.toString() 
				+ "\n" + "else:" + "\n" + "\t" + otherwise.toString());
	}

	@Override
	public void executeStatement(){
		this.executed=false;
		if ((SelfWormExpression.getWorm().getProgram().getstatementCount()>=1000)){
			SelfWormExpression.getWorm().setCurrentAP(0);}
		else if(getActualCondition()){
			if (then instanceof ActionStatement){
				if (!((ActionStatement)then).enoughAp()){
					SelfWormExpression.getWorm().getProgram().stop();
					return;
					}
			}
			SelfWormExpression.getWorm().getProgram().increaseCount();
			then.executeStatement();
		}
		else if(!getActualCondition()){
			if (otherwise instanceof ActionStatement){
				if (!((ActionStatement)otherwise).enoughAp()){
					SelfWormExpression.getWorm().getProgram().stop();
					return;
					}
			}
		SelfWormExpression.getWorm().getProgram().increaseCount();
		otherwise.executeStatement();
		}
	this.executed=true;
	}
	
	private boolean getActualCondition() {
		return (Boolean) condition.getValue().getValue();
	}
	
	@Override
	public boolean isexecuted() {
		return this.executed;
	}
	@Override
	public void setExecuted(boolean bool) {
		this.executed=bool;
		
	}
	
	public List<Statement> getStatements(){
		List<Statement> statements = new ArrayList<>();
		statements.add(then);
		statements.add(otherwise);
		return statements;
	}
}
