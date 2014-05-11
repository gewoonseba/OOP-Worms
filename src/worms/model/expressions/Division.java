package worms.model.expressions;

public class Division extends DoubleBinaryExpression {

	
	public Division(DoubleExpressions left, DoubleExpressions right)
			throws IllegalOperandException {
		super(left, right);
	}


	@Override
	public Double getValue() {
		return getLeftOperand().getValue() / getRightOperand().getValue();
	}

	
	@Override
	public String getOperatorSymbol() {
		return "/";
	}
}
