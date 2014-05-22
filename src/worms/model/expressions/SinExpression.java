package worms.model.expressions;

import worms.model.types.DoubleType;

public class SinExpression extends DoubleUnaryExpression {

	public SinExpression(Expression operand){
		super((DoubleExpression) operand);
	}

	@Override
	public String getOperatorSymbol() {
		return "sin";
	}

	@Override
	public DoubleType getValue() {
		return new DoubleType(Math.sin((Double)getOperand().getValue().getValue()));
	}

	@Override
	public SinExpression clone() {
		return new SinExpression(super.getOperand());
	}
	
	@Override
	public String toString(){
		return(getOperatorSymbol() + "(" + getOperand().toString() + ")");
	}
	
}
