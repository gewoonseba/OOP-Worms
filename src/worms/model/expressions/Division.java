package worms.model.expressions;

import worms.model.types.DoubleType;

public class Division extends DoubleBinaryExpression {

	
	public Division(Expression left, Expression right)
			throws IllegalOperandException {
		super((DoubleExpression)left, (DoubleExpression)right);
	}


	@Override
	public DoubleType getValue() {
		return new DoubleType(getLeftOperand().getValue().getValue() / getRightOperand().getValue().getValue());
	}

	
	@Override
	public String getOperatorSymbol() {
		return "/";
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected void setOperandAt(int index, DoubleExpression left) {
		// TODO Auto-generated method stub
		
	}


	
}
