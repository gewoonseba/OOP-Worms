package worms.model.expressions;

import worms.model.types.BooleanType;

public class GreaterThanOrEqual extends BooleanCompareExpression {

	public GreaterThanOrEqual(Expression left,Expression right){
		this.leftOperand = (DoubleExpression) left;
		this.rightOperand = (DoubleExpression) right;
	}
	
	@Override
	public String getOperatorSymbol() {
		return ">=";
	}
	
	@Override
	public BooleanType getValue() {
		return new BooleanType((Double) getLeftOperand().getValue().getValue() >= (Double) getRightOperand().getValue().getValue());
	}

	
	@Override
	public boolean equals(Object other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public DoubleExpression getLeftOperand() {
		return  leftOperand;
	}

	public DoubleExpression getRightOperand() {
		return rightOperand;
	}
	
	private final DoubleExpression leftOperand;
	private final DoubleExpression  rightOperand;
}
