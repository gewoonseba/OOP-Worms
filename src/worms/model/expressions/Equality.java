package worms.model.expressions;

public class Equality<E> extends BooleanCompareExpression<E> {

	public Equality(Expression<E> left,Expression<E> right){
		this.leftOperand = left;
		this.rightOperand = right;
	}
	
	@Override
	public String getOperatorSymbol() {
		return "==";
	}

	@Override
	public Boolean getValue() {
		return getLeftOperand().getValue() == getRightOperand().getValue();
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
		return leftOperand;
	}

	@Override
	public Expression<E> getRightOperand() {
		return rightOperand;
	}
	
	private final Expression<E> leftOperand;
	
	private final Expression<E> rightOperand;

}
