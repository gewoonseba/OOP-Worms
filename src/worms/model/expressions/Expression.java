package worms.model.expressions;

public abstract class Expression<E> implements Cloneable {

	/**
	 * Check whether this expression has the given expression as one
	 * of its subexpressions.
	 *
	 * @param  expression
	 *         The expression to be checked.
	 * @return True if the given expression is the same expression as this
	 *         expression.
	 *       | if (expression == this)
	 *       |   then result == true
	 * @return False if the given expression is not effective.
	 *       | if (expression == null)
	 *       |   then result == false
	 * @note   This method illustrates partial specifications of methods. At this
	 *         level, the effect of the method is only defined in 2 cases. All
	 *         other cases must be worked out at the lower levels of the hierarchy.
	 */
	public abstract boolean hasAsSubExpression(Expression<E> expression);

	

	/**
	 * Check whether the state of this expression can be changed.
	 * 
	 * @note   This inspector is best avoided in languages fully supporting
	 *         multiple inheritance. Then, mutable expression will inherit
	 *         from an abstract class of mutable expressions introducing a.o.
	 *         a method setValue, and a preliminary definition of cloning.
	 *         An abstract class of immutable expressions might also be defined
	 *         then, introducing a final version of cloning.
	 */
	public abstract boolean isMutable();

	/**
	 * Check whether this expression is equal to the given object.
	 * 
	 * @return If the given object is the same as this expression,
	 *         always true.
	 *       | if (this == other)
	 *       |   then result == true
	 * @return If the given object is not effective, always false.
	 *       | if (other == null)
	 *       |   then result == false
	 * @return If this expression and the given object do not belong
	 *         to the same (concrete) class, always false.
	 *       | if (getClass() != other.getClass())
	 *       |   then result == false
	 * @return If the given object is an expression and its value
	 *         differs from the value of this expression, always false.
	 *       | if ( (other instanceof Expression) &&
	 *       |      (getValue() != ((Expression)other).getValue()) )
	 *       |   then result == false
	 * @return If this expression is mutable, true if and only if this
	 *         expression and the given object are the same.
	 *       | if (this.isMutable())
	 *       |   then result == (this == other)
	 * @note   We could have worked out a partial implementation at
	 *         this level, as we do for the method isIdenticalTo.
	 */
	@Override
	public abstract boolean equals(Object other);
	
	/**
	 * Check whether this expression is identical to the given expression.
	 * 
	 * @param  other
	 *         The expression to compare with.
	 * @return False if the given expression is not effective.
	 *       | if (other == null)
	 *       |   then result == false
	 * @return False if this expression and the given expression do not
	 *         belong to the same (concrete) class.
	 *       | if (getClass() != other.getClass())
	 *       |   then result == false
	 * @return True if this expression is equal to the given expression.
	 *       | if (this.equals(other))
	 *       |   then result == true
	 */
	public boolean isIdenticalTo(Expression<E> other) {
		if (other == null)
			 return false;
		if (this.getClass() != other.getClass())
			return false;
		if (this.equals(other))
			return true;
		return false;
	}

	/**
	 * Return a clone of this expression.
	 * 
	 * @return The resulting expression is identical to this expression.
	 *       | result.isIdenticalTo(this)
	 * @return The resulting expression is the same as this expression
	 *         if and only if this expression is immutable.
	 *       | (result == this) == (! this.isMutable())
	 */
	@Override
	public Expression<E> clone() {
		try {
			if (isMutable())
				return (Expression<E>) super.clone();
			else
				return this;
		} catch (CloneNotSupportedException exc) {
			assert false;
			return null;
		}
	}


	/**
	 * Return a textual representation of this expression.
	 *
	 * @return The resulting string is non-empty.
	 *       | result.length() > 0
	 */
	@Override
	public abstract String toString();
	
	public abstract E getValue();


}