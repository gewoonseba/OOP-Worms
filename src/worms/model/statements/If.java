package worms.model.statements;


import worms.model.expressions.*;


public class If extends Statement {
	
	private Statement then;
	private Statement otherwise;
	private Expression condition;
	
	public If(Expression condition,Statement then,Statement otherwise) {
		if (! (condition instanceof BooleanExpression))
			throw new IllegalArgumentException();
		this.then = then;
		this.otherwise = otherwise;
		this.condition= condition;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void executeStatement(){
		if (getActualCondition())
			then.executeStatement();
		else
			otherwise.executeStatement();
	}
	
	private boolean getActualCondition() {
		return (Boolean) condition.getValue();
	}

}
