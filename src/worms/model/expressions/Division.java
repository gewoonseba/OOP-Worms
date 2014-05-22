package worms.model.expressions;

import worms.model.types.DoubleType;

public class Division extends DoubleBinaryExpression {

	
	public Division(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public DoubleType getValue() {
		return new DoubleType(((Double) getLeftOperand().getValue().getValue()) / 
				((Double) getRightOperand().getValue().getValue()));
	}

	@Override
	public String getOperatorSymbol() {
		return "/";
	}

	@Override
	public String toString() {
		return (getLeftOperand().toString() + getOperatorSymbol() + getRightOperand().toString());
	}

	@Override
	public Division clone() {
		return new Division(super.getLeftOperand(),super.getRightOperand());
	}
	
}
