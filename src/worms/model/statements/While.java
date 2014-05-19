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
		int n = 0;
	    while (getActualCondition() || n<1000){
	    	
	    	System.out.println("whileke");
	    	if (body instanceof ActionStatement)
				if (!((ActionStatement)body).enoughAp())
					break;
	    	body.executeStatement();
	    	if (SelfWormExpression.getWorm().getCurrentAP()==0||SelfWormExpression.getWorm().getHitPoints()==0)
				break;
	        n+=1;
	    }
	    if (getActualCondition() || n<1000)
	    	this.executed=true;
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
