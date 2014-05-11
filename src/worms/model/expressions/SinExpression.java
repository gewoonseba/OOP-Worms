package worms.model.expressions;

public class SinExpression extends DoubleUnaryExpression {

	protected SinExpression(DoubleExpressions operand)
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
		return Math.sin(getOperand().getValue());
	}

}
