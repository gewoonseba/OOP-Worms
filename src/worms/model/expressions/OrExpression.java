package worms.model.expressions;

import worms.model.types.BooleanType;

public class OrExpression extends BooleanExpression {
	
	public OrExpression(BooleanExpression expr1,BooleanExpression expr2) throws IllegalArgumentException {
		if( ! (expr1 instanceof BooleanExpression && expr2 instanceof BooleanExpression))
			throw new IllegalArgumentException();
		this.expr1=expr1;
		this.expr2=expr2;
	}
	
	public String getOperatorSymbol() {
		return "||";
	}

	@Override
	public BooleanType getValue() {
		return new BooleanType(expr1.getValue().getValue() || (Boolean)expr2.getValue().getValue());
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
