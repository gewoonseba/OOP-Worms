package worms.model.expressions;

import worms.model.types.BooleanType;

public class OrExpression extends LogicalOperator{
	
	public OrExpression(Expression expr1,Expression expr2) {
		super(expr1,expr2);
	}
	
	@Override
	public String getOperatorSymbol() {
		return "||";
	}

	@Override
	public BooleanType getValue() {
		BooleanType andType= new BooleanType((Boolean) super.getLeft().getValue().getValue() ||  (Boolean) super.getRight().getValue().getValue());
		return (andType);
	}

	@Override
	public String toString() {
		return super.getLeft().toString() + getOperatorSymbol() + super.getRight().toString();
	}

	@Override
	public LogicalOperator clone() {
		return new OrExpression(super.getLeft(),super.getRight());
	}

}
