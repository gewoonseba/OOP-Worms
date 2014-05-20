package worms.model.expressions;

import worms.model.types.BooleanType;

public class BooleanLiteral extends Expression {
	
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
		BooleanType valueType = new BooleanType(value);
		this.value = valueType;
	}
	
	public BooleanLiteral(){
		this(false);
	}
	
	/**
	 * Variable registering the value of this double literal.
	 */
	private final BooleanType value;

	@Override
	public BooleanType getValue() {
		return this.value;
	}

	@Override
	public boolean equals(Object other) {
		return (other instanceof BooleanLiteral)
				&& (getValue() == ((BooleanLiteral) other).getValue());
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}