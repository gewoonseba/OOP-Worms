package worms.model.expressions;

public class AndExpression<E> extends BooleanCompareExpression<E> {
	
	private final Expression<E> leftOperand;
	private final Expression<E> rightOperand;
	
	public AndExpression(Expression<E> expr1,Expression<E> expr2){
		this.leftOperand= expr1;
		this.rightOperand= expr2;
	}
	@Override
	public String getOperatorSymbol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean getValue() {
		return ((Boolean)getLeftOperand().getValue() && (Boolean)getRightOperand().getValue());
	}

	@Override
	public boolean isMutable() {
		// TODO Auto-generated method stub
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

}
