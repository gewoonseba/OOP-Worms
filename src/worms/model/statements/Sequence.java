package worms.model.statements;

import java.util.List;

import worms.model.expressions.SelfWormExpression;

public class Sequence extends Statement {
	
	public boolean executed=false;
	
	private int i=0;
	
	public Sequence(List<Statement> statements){
		this.statements = statements;
	}

	@Override
	public String toString() {
		return "s*";
	}
	
	public void executeStatement() {
		this.executed=false;
		if (i ==statements.size())
			i=0;
		while (i <statements.size()){
			if (SelfWormExpression.getWorm().getProgram().getstatementCount()>=1000)
				break;
			if (statements.get(i).isexecuted()){
				statements.get(i).setExecuted(false);
				i+=1;
		        continue;}
			if (statements.get(i) instanceof ActionStatement){
				if (!((ActionStatement)statements.get(i)).enoughAp()){
					SelfWormExpression.getWorm().setCurrentAP(0);
					break;}}
			statements.get(i).executeStatement();
			if (SelfWormExpression.getWorm().getCurrentAP()==0||SelfWormExpression.getWorm().getHitPoints()==0)
				break;
			statements.get(i).setExecuted(false);
			i+=1;
		}
		if (i == statements.size())
	        SelfWormExpression.getWorm().getProgram().increaseCount();
			this.executed=true;
	}
	
	private List<Statement> statements;
	
	@Override
	public boolean isexecuted() {
		
		return this.executed;
	}
	@Override
	public void setExecuted(boolean bool) {
		this.executed=bool;
		
	}
	
	public List<Statement> getStatements(){
		return this.statements;
	}
	
}
