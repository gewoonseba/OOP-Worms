package worms.model.expressions;

import worms.model.types.DoubleType;

public class CosExpression extends DoubleUnaryExpression {

	public CosExpression(Expression operand) {
		super((DoubleExpression) operand);
	}

	@Override
	public String getOperatorSymbol() {
		return "cos";
	}
	
	@Override
	public String toString(){
		return (getOperatorSymbol() + "(" + getOperand().toString() + ")");
	}

	@Override
	public DoubleType getValue() {
		return new DoubleType(Math.cos((Double)getOperand().getValue().getValue()));
	}

	@Override
	public CosExpression clone() {
		return new CosExpression(super.getOperand());
	}
	
}
