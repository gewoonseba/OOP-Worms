package worms.model.expressions;

import worms.model.types.BooleanType;

public class AndExpression extends BooleanExpression{
	
	public AndExpression(Expression expr1,Expression expr2) throws IllegalArgumentException {
		if (! (expr1 instanceof BooleanExpression && expr2 instanceof BooleanExpression))
			throw new IllegalArgumentException();
		this.expr1 = (BooleanExpression)expr1;
		this.expr2 = (BooleanExpression)expr2;

	}
	
	public String getOperatorSymbol() {
		return "&&";
	}

	@Override
	public BooleanType getValue() {
		BooleanType andType= new BooleanType(expr1.getValue().getValue() &&  expr2.getValue().getValue());
		return ( andType);
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
	
	private final BooleanExpression expr1;
	private final BooleanExpression expr2;

}
