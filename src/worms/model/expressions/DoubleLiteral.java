package worms.model.expressions;


import be.kuleuven.cs.som.annotate.*;

/**
 * A class of double literals.
 * 
 * @version  2.0
 * @author   Eric Steegmans
 */
public class DoubleLiteral extends DoubleBasicExpression {

	/**
	 * Initialize this new double literal with given value.
	 *
	 * @param  value
	 *         The value for this new double literal.
	 * @post   The value of this new double literal is equal to
	 *         the given value.
	 *       | new.getValue() == value
	 */
	public DoubleLiteral(double value) {
		this.value = value;
	}

	/**
	 * Initialize this new double literal with value 0.
	 *
	 * @effect This new double literal is initialized with 0
	 *         as its value.
	 *       | this(0)
	 */
	public DoubleLiteral() {
		// We must explicitly initialize the final instance variable value in
		// this constructor, either in a direct way or in an indirect way.
		this(0);
	}

	/**
	 * Constant referencing a predefined double literal with value 0.
	 * 
	 * @invar  The constant references an effective double literal,
	 *         whose value is 0.
	 *       | ZERO.getValue() == 0
	 */
	public final static DoubleLiteral ZERO = new DoubleLiteral();

	/**
	 * Return the value of this double literal.
	 */
	@Override
	@Basic @Immutable
	public double getValue() {
		return value;
	}
	
	

	/**
	 * Variable registering the value of this double literal.
	 */
	private final double value;

	/**
	 * Check whether this double literal is equal to the given
	 * object.
	 *
	 * @returnTrue if and only if the other object is an effective
	 *         double literal, whose value is equal to the value
	 *         of this double literal.
	 *       | result ==
	 *       |   (other instanceof DoubleLiteral) &&
	 *       |   (this.getValue() == ((DoubleLiteral)other).getValue())
	 */
	@Override
	public boolean equals(Object other) {
		return (other instanceof DoubleLiteral)
				&& (getValue() == ((DoubleLiteral) other).getValue());
	}

	/**
	 * Check whether the state of this double literal can be changed.
	 * 
	 * @return Always false.
	 *       | result == false
	 */
	@Override
	public final boolean isMutable() {
		return false;
	}

	/**
	 * Return a textual representation of this double literal.
	 *
	 * @return The textual representation of the value of this double
	 *         literal as defined by the predefined class Double.
	 *       | result.equals(Double.toString(getValue())
	 */
	@Override
	public String toString() {
		return Double.toString(getValue());
	}

}