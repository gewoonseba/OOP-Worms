package worms.model.expressions;

public class OrExpression<E> extends BooleanCompareExpression<E> {
	
	private final Boolean expr1;
	private final Boolean expr2;
	
	public OrExpression(Expression<Boolean> expr1,Expression<Boolean> expr2){
		this.expr1=expr1.getValue();
		this.expr2=expr2.getValue();
	}
	@Override
	public String getOperatorSymbol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean getValue() {
		return (expr1||expr2);
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
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Expression<E> getRightOperand() {
		// TODO Auto-generated method stub
		return null;
	}

}
