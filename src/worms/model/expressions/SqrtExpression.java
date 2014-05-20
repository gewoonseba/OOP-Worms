package worms.model.expressions;

import worms.model.types.DoubleType;

public class SqrtExpression extends DoubleUnaryExpression {

	public SqrtExpression(Expression operand)
			throws IllegalOperandException {
		super((DoubleExpression) operand);
	}

	@Override
	public String getOperatorSymbol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleType getValue() {
		return new DoubleType(Math.sqrt(getOperand().getValue().getValue()));
	}

	@Override
	public SqrtExpression clone() {
		return new SqrtExpression(super.getOperand());
	}
	
}
