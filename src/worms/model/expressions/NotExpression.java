package worms.model.expressions;

import worms.model.types.BooleanType;

public class NotExpression extends LogicalOperator{
	
	public NotExpression(Expression expr1) {
		super(expr1,null);
	}
	
	@Override
	public String getOperatorSymbol() {
		return "!";
	}

	@Override
	public BooleanType getValue() {
		return new BooleanType(! (Boolean) super.getLeft().getValue().getValue());
	}

	@Override
	public String toString() {
		return super.getLeft().toString() + getOperatorSymbol() + super.getRight().toString();
	}

	@Override
	public NotExpression clone() {
		return new NotExpression(super.getLeft());
	}

}
