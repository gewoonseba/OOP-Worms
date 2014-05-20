package worms.model.expressions;

import worms.model.types.BooleanType;

public class Inequality extends Comparator {

	public Inequality(Expression left,Expression right){
		super(left,right);
	}
	
	@Override
	public String getOperatorSymbol() {
		return "!=";
	}

	@Override
	public BooleanType getValue() {
		return new BooleanType(! super.getLeft().getValue().equals(super.getRight().getValue()));
	}

	

	@Override
	public String toString() {
		return super.getLeft().toString() + getOperatorSymbol() + super.getRight().toString();
	}

	@Override
	public Inequality clone() {
		return new Inequality(super.getLeft(), super.getRight());
	}

}
