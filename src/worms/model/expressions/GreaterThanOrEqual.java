package worms.model.expressions;

public class GreaterThanOrEqual<E> extends BooleanCompareExpression<E> {

	public GreaterThanOrEqual(DoubleExpressions left,DoubleExpressions right){
		this.leftOperand = left;
		this.rightOperand = right;
	}
	
	@Override
	public String getOperatorSymbol() {
		return ">=";
	}
	
	@Override
	public Boolean getValue() {
		return (Double) getLeftOperand().getValue() >= (Double) getRightOperand().getValue();
	}

	@Override
	public boolean isMutable() {
		return false;
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
