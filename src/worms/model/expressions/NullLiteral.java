package worms.model.expressions;


public class NullLiteral extends Expression<Double> {

	private Double double1;

	@Override
	public Double getValue() {
		return double1;
	}
	

	@Override
	public boolean isMutable() {
		return false;
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
	public boolean hasAsSubExpression(Expression<Double> expression) {
		// TODO Auto-generated method stub
		return false;
	}

}
