package worms.model.statements;

<<<<<<< HEAD
import worms.model.expressions.BooleanExpression;
import worms.model.expressions.BooleanExpressions;
=======
import worms.model.expressions.*;
>>>>>>> 734409432258e8c3e9cf562a2084aa04af2dd360

public class While extends Statement {
	 
	private Expression condition;
	private Statement body;
	
	public While(Expression condition,Statement body) {
		this.condition = condition;
		this.body = body;
	}

	@Override
	public void executeStatement(){
		int n = 0;
	    while (getActualCondition() || n<1000){
	    	body.executeStatement();
	        n+=1;
	    }
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private boolean getActualCondition() {
		return (Boolean) condition.getValue();
	}

}
