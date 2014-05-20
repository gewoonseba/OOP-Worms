package worms.model.expressions;

import worms.model.types.DoubleType;


public class NullLiteral extends Expression{
	
	public NullLiteral(){
	}

	@Override
	public DoubleType getValue() {
		return null;
	}

	@Override
	public boolean equals(Object other) {
		return (other instanceof NullLiteral);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "null";
	}
	
	@Override
	public NullLiteral clone() {
		return new NullLiteral();
	}


}
