package worms.model.expressions;

public class GreaterThan<E> extends BooleanCompareExpression<E> {
	
	public GreaterThan(DoubleExpression left,DoubleExpression right){
		this.leftOperand = left;
		this.rightOperand = right;
	}
	
	@Override
	public String getOperatorSymbol() {
		return ">";
	}

	@Override
	public Boolean getValue() {
		return (Double)getLeftOperand().getValue() >(Double)getRightOperand().getValue();
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
	
	private final DoubleExpression leftOperand;
	private final DoubleExpression rightOperand;

}
