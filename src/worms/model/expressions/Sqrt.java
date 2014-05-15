package worms.model.expressions;

import worms.model.types.DoubleType;

public class Sqrt extends DoubleUnaryExpression {

	protected Sqrt(DoubleExpression operand) throws IllegalOperandException {
		super(operand);
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
