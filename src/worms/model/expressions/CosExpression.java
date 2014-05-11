package worms.model.expressions;

public class CosExpression extends DoubleUnaryExpression {

	protected CosExpression(DoubleExpressions operand)
			throws IllegalOperandException {
		super(operand);
	}

	@Override
	public String getOperatorSymbol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getValue() {
		return Math.cos(getOperand().getValue());
	}

}
