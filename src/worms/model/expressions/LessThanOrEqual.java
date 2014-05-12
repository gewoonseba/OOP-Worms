package worms.model.expressions;

import be.kuleuven.cs.som.annotate.Model;

public class LessThanOrEqual<E> extends BooleanCompareExpression<E> {

	@Model
	public LessThanOrEqual(DoubleExpressions left, DoubleExpressions right) {
		this.leftOperand = left;
		this.rightOperand = right;
	}
		
	@Override
	public Boolean getValue() {
		return (Double) getLeftOperand().getValue() <= (Double) getRightOperand().getValue();
	}

	@Override
	public boolean isMutable() {
		return false;
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
		return "<=";
	}

	@Override
	public Expression<E> getLeftOperand() {
		return (Expression<E>) leftOperand;
	}

	@Override
	public Expression<E> getRightOperand() {
		return (Expression<E>) rightOperand;
	}
	
	private final DoubleExpressions leftOperand;
	private final DoubleExpressions rightOperand;
}

