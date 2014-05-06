package worms.model.expressions;

public class BooleanLiteral extends BooleanBasicExpression {
	
	/**
	 * Initialize this new double literal with given value.
	 *
	 * @param  value
	 *         The value for this new double literal.
	 * @post   The value of this new double literal is equal to
	 *         the given value.
	 *       | new.getValue() == value
	 */
	public BooleanLiteral(boolean value) {
		this.value = value;
	}
	
	public BooleanLiteral(){
		this(false);
	}
	
	/**
	 * Variable registering the value of this double literal.
	 */
	private final boolean value;

	@Override
	public boolean getBooleanValue() {
		return this.value;
	}
	//TODO:How to implement getValue and getBooleanValue.
	
	public double getValue(){
		return 0;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public boolean equals(Object other) {
		return (other instanceof BooleanLiteral)
				&& (getValue() == ((BooleanLiteral) other).getValue());
	}

	@Override
	public String toString() {
		return Boolean.toString(getBooleanValue());
	}

}
