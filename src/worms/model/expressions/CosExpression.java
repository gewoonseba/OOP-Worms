package worms.model.expressions;

import worms.model.types.DoubleType;

public class CosExpression extends DoubleUnaryExpression {

	protected CosExpression(DoubleExpression operand)
			throws IllegalOperandException {
		super(operand);
	}

	@Override
	public String getOperatorSymbol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleType getValue() {
		return new DoubleType(Math.cos(getOperand().getValue().getValue()));
	}

}
