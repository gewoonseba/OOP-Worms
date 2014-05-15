package worms.model.expressions;

import worms.model.types.BooleanType;

public class GreaterThan extends BooleanCompareExpression {
	
	public GreaterThan(DoubleExpression left,DoubleExpression right){
		this.leftOperand = left;
		this.rightOperand = right;
	}
	
	@Override
	public String getOperatorSymbol() {
		return ">";
	}

	@Override
	public BooleanType getValue() {
		return new BooleanType(getLeftOperand().getValue().getValue() >(Double)getRightOperand().getValue().getValue());
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleExpression getLeftOperand() {
		return leftOperand;
	}

	@Override
	public DoubleExpression getRightOperand() {
		return rightOperand;
	}
	
	private final DoubleExpression leftOperand;
	private final DoubleExpression rightOperand;

}
