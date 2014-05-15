package worms.model.expressions;

import worms.model.types.DoubleType;

public class SinExpression extends DoubleUnaryExpression {

	public SinExpression(Expression operand)
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
		return new DoubleType(Math.sin(getOperand().getValue().getValue()));
	}

}
