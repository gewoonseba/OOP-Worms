package worms.model.expressions;

import worms.model.types.DoubleType;

public class SqrtExpression extends DoubleUnaryExpression {

	public SqrtExpression(Expression operand){
		super((DoubleExpression) operand);
	}

	@Override
	public String getOperatorSymbol() {
		return "sqrt";
	}

	@Override
	public DoubleType getValue() {
		return new DoubleType(Math.sqrt((Double)getOperand().getValue().getValue()));
	}

	@Override
	public SqrtExpression clone() {
		return new SqrtExpression(super.getOperand());
	}
	
	@Override
	public String toString(){
		return ( getOperatorSymbol() +"(" + getOperand().toString() +")");
	}
	
}
