package worms.model.expressions;

public class OrExpression extends BooleanCompareExpression {
	
	private final Expression<Boolean> expr1;
	private final Expression<Boolean> expr2;
	
	public OrExpression(Expression<Boolean> expr1,Expression<Boolean> expr2){
		this.expr1=expr1;
		this.expr2=expr2;
	}
	@Override
	public String getOperatorSymbol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean getValue() {
		return (expr1.getValue()||expr2.getValue());
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

}
