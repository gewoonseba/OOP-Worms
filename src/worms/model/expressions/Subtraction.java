package worms.model.expressions;

import worms.model.types.DoubleType;

public class Subtraction extends DoubleBinaryExpression {

	public Subtraction(Expression left, Expression right)
			throws IllegalOperandException {
		super(left, right);
	}

	@Override
	public DoubleType getValue() {
		return new DoubleType(((Double) super.getLeftOperand().getValue().getValue()) - 
				((Double) super.getRightOperand().getValue().getValue()));
	}

	@Override
	public String getOperatorSymbol() {
		return "-";
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

	@Override
	public Subtraction clone() {
		return new Subtraction(super.getLeftOperand(),super.getRightOperand());
	}

}