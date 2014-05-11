package worms.model.expressions;

public class Inequality<E> extends BooleanCompareExpression {

	public Inequality(Expression<E> left,Expression<E> right){
		leftValue = left;
		rightValue = right;
	}
	
	private Expression<E> leftValue;
	private Expression<E> rightValue;

	@Override
	public String getOperatorSymbol() {
		return "!=";
	}

	@Override
	public Boolean getValue() {
		return leftValue.getValue()!=rightValue.getValue();
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

}
