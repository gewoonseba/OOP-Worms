package worms.model.expressions;

import worms.model.types.BooleanType;

public class GreaterThan extends Comparator {
	
	public GreaterThan(Expression left,Expression right){
		super(left,right);
	}
	
	@Override
	public String getOperatorSymbol() {
		return ">";
	}

	@Override
	public BooleanType getValue() {
		return new BooleanType((Double) super.getLeft().getValue().getValue() 
				> (Double) super.getRight().getValue().getValue());
	}

	@Override
	public String toString() {
		return super.getLeft().toString() + getOperatorSymbol() + super.getRight().toString() ;
	}

	@Override
	public Comparator clone() {
		return new GreaterThan(super.getLeft(),super.getRight());
	}

}
