package worms.model.expressions;

import worms.model.types.Entity;


public class NullLiteral extends Expression{

	private Entity<Double> double1;

	@Override
	public Entity<Double> getValue() {
		return new Entity<Double>();
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


}
