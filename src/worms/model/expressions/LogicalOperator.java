package worms.model.expressions;

import worms.model.types.BooleanType;

public abstract class LogicalOperator extends BooleanExpression {
	
	public LogicalOperator(Expression expr1,Expression expr2) {
		this.left = expr1;
		this.right = expr2;
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
