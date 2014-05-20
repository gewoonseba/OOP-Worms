package worms.model.statements;


import worms.model.expressions.*;


public class While extends Statement {
	
	public boolean executed=false;
	 
	private Expression condition;
	private Statement body;
	
	public While(Expression condition,Statement body) {
		this.condition = condition;
		this.body = body;
	}

	@Override
	public void executeStatement(){
		this.executed=false;
	    while (getActualCondition() ){
	    	if (SelfWormExpression.getWorm().getProgram().getstatementCount()>=1000)
				return;
	    	if (body instanceof ActionStatement){
				if (!((ActionStatement)body).enoughAp()){
					SelfWormExpression.getWorm().getProgram().stop();
					return;}}
	    	body.executeStatement();
	    	if (SelfWormExpression.getWorm().getProgram().isStopped())
				return;
	    }
	    this.executed=true;
	    SelfWormExpression.getWorm().getProgram().increaseCount();
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
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
	
	public Statement getBody(){
		return this.body;
	}
	
}
