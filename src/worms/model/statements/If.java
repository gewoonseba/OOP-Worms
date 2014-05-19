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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void executeStatement(){
		this.executed=false;
		if (getActualCondition()){
			if (then instanceof ActionStatement){
				if (!((ActionStatement)then).enoughAp()){
					SelfWormExpression.getWorm().setCurrentAP(0);
					}
				else{
					this.executed=true;
				then.executeStatement();}}
			else{
				this.executed=true;
			then.executeStatement();}}
		else{
			if (otherwise instanceof ActionStatement){
				if (!((ActionStatement)otherwise).enoughAp()){
					SelfWormExpression.getWorm().setCurrentAP(0);
					}
				else{
					this.executed=true;
				otherwise.executeStatement();}}
			else{
				this.executed=true;
			otherwise.executeStatement();}}
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
