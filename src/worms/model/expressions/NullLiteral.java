package worms.model.expressions;

public class NullLiteral extends DoubleBasicExpression {

	private Double double1;

	@Override
	public double getValue() {
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

}
