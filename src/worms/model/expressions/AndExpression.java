package worms.model.expressions;

public class AndExpression<E> extends BooleanExpression{
	
	public AndExpression(Expression<Boolean> expr1,Expression<Boolean> expr2){
		this.expr1 = expr1;
		this.expr2 = expr2;

	}
	
	public String getOperatorSymbol() {
		return "&&";
	}

	@Override
	public Boolean getValue() {
		return (expr1.getValue()&&expr2.getValue());
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
	
	private final Expression<Boolean> expr1;
	private final Expression<Boolean> expr2;

}
