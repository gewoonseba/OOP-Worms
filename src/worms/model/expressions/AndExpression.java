package worms.model.expressions;

public class AndExpression<E> extends BooleanExpression{
	
	public AndExpression(Expression<E> expr1,Expression<E> expr2) throws IllegalArgumentException {
		if (! (expr1 instanceof BooleanExpression && expr2 instanceof BooleanExpression))
			throw new IllegalArgumentException();
		this.expr1 = expr1;
		this.expr2 = expr2;

	}
	
	public String getOperatorSymbol() {
		return "&&";
	}

	@Override
	public Boolean getValue() {
		return ((Boolean) expr1.getValue() && (Boolean) expr2.getValue());
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
		return expr1.toString() + getOperatorSymbol() + expr2.toString();
	}
	
	private final Expression<E> expr1;
	private final Expression<E> expr2;

}
