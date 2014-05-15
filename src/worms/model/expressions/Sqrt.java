package worms.model.expressions;

import worms.model.types.DoubleType;

public class Sqrt extends DoubleUnaryExpression {

	public Sqrt(Expression operand) throws IllegalOperandException {
		super((DoubleExpression)operand);
	}

	@Override
	public String getOperatorSymbol() {
		return "sqrt";
	}

	@Override
	public DoubleType getValue() {
		return new DoubleType(Math.sqrt((getOperand().getValue().getValue())));
	}


}
