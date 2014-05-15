package worms.model.expressions;

import worms.model.types.BooleanType;
import be.kuleuven.cs.som.annotate.Model;

public class LessThan extends BooleanCompareExpression {

	@Model
	public LessThan(Expression left, Expression right) {
		this.leftOperand = (DoubleExpression) left;
		this.rightOperand = (DoubleExpression) right;
	}
		
	public BooleanType getValue() {
		return new BooleanType((Double)getLeftOperand().getValue().getValue() < (Double)getRightOperand().getValue().getValue());	
		}


	@Override
	public boolean equals(Object other) {
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getOperatorSymbol() {
		// TODO Auto-generated method stub
		return "<";
	}

	@Override
	public DoubleExpression getLeftOperand() {
		return (DoubleExpression) leftOperand;
	}

	@Override
	public DoubleExpression getRightOperand() {
		return (DoubleExpression) rightOperand;
	}
	
	private final DoubleExpression leftOperand;
	private final DoubleExpression rightOperand;
}

