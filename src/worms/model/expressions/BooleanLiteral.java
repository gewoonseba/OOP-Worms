package worms.model.expressions;

import worms.model.types.BooleanType;

public class BooleanLiteral extends BooleanExpression {
	
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
		this.originalBoolean = value;
	}
	
	public BooleanLiteral(){
		this(false);
	}
	
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
	
	private boolean getOriginalBoolean(){
		return this.originalBoolean;
	}
	
	private boolean originalBoolean;

	@Override
	public BooleanLiteral clone() {
		return new BooleanLiteral(getOriginalBoolean());
	}
}