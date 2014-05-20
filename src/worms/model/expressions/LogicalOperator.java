package worms.model.expressions;

import worms.model.types.BooleanType;

public abstract class LogicalOperator extends BooleanExpression {
	
	public LogicalOperator(Expression expr1,Expression expr2) throws IllegalArgumentException {
		if (! (expr1 instanceof BooleanExpression && expr2 instanceof BooleanExpression))
			throw new IllegalArgumentException();
		this.left = (BooleanExpression)expr1;
		this.right = (BooleanExpression)expr2;
	}
	
	public abstract String getOperatorSymbol();
	
	public Expression getLeft(){
		return this.left;
	}
	
	private Expression left;
	
	public Expression getRight(){
		return this.right;
	}
	
	private Expression right;
	
	public abstract LogicalOperator clone();

	@Override
	public abstract BooleanType getValue();

	@Override
	public abstract String toString();

}
