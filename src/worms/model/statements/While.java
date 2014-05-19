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
				break;
	    	if (body instanceof ActionStatement){
				if (!((ActionStatement)body).enoughAp()){
					SelfWormExpression.getWorm().setCurrentAP(0);
					break;}}
	    	body.executeStatement();
	    	if (SelfWormExpression.getWorm().getCurrentAP()==0||SelfWormExpression.getWorm().getHitPoints()==0)
				break;
	    }
	    if (!(getActualCondition()) ){
	    	System.out.println("condition");
	    	SelfWormExpression.getWorm().getProgram().increaseCount();
	    	this.executed=true;}
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
