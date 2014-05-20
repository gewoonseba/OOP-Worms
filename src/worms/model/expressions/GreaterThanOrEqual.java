package worms.model.expressions;

import worms.model.types.BooleanType;

public class GreaterThanOrEqual extends Comparator {

	public GreaterThanOrEqual(Expression left,Expression right){
		this.leftOperand = left;
		this.rightOperand = right;
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

	public Expression getLeftOperand() {
		return  leftOperand;
	}

	public Expression getRightOperand() {
		return rightOperand;
	}
	
	private final Expression leftOperand;
	private final Expression  rightOperand;
}
