package worms.model.expressions;

public class Sqrt extends DoubleUnaryExpression {

	protected Sqrt(DoubleExpressions operand) throws IllegalOperandException {
		super(operand);
	}

	@Override
	public String getOperatorSymbol() {
		return "sqrt";
	}

	@Override
	public double getValue() {
		return Math.sqrt((getOperand().getValue()));
	}

}
