package worms.model.expressions;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

public abstract class DoubleComposedExpression extends DoubleExpressions {

	/**
	 * Check whether this composed expression is equal to 
	 * the given object.
	 *
	 * @return If this composed expression is mutable or the other object
	 *         is a mutable composed expression, true if and only if this
	 *         composed expression is the same as the given object.
	 *       | if ( this.isMutable() ||
	 *       |   || ( (other instanceof ComposedExpression)
	 *       |     && ((ComposedExpression)other).isMutable() ) )
	 *       |   then result == (this == other)
	 * @return If this composed expression is immutable and the other object
	 *         is an immutable composed expression, true if and only if both
	 *         composed expressions belong to the same class, if they have the
	 *         same number of operands, and if all corresponding operands of
	 *         both composed expressions are equal.
	 *       | if ( (! this.isMutable())
	 *       |   && (other instanceof ComposedExpression) 
	 *       |   && (! ((ComposedExpression)other).isMutable() ) )
	 *       |   then result ==
	 *       |       (getNbOperands() == ((ComposedExpression)other).getNbOperands())
	 *       |    && ( for each I in 1..getNbOperands():
	 *       |         getOperandAt(I).equals(((ComposedExpression)other.getOperandAt(I)) )
	 */
	@Override
	public boolean equals(Object other) {
		if ((other == null) || (getClass() != other.getClass()))
			return false;
		DoubleComposedExpression otherExpr = (DoubleComposedExpression) other;
		if (this.isMutable() || otherExpr.isMutable())
			return this == other;
		if (getNbOperands() != otherExpr.getNbOperands())
			return false;
		for (int pos = 1; pos <= getNbOperands(); pos++)
			if (!getOperandAt(pos).equals(otherExpr.getOperandAt(pos)))
				return false;
		return true;
	}

	/**
	 * Check whether this composed expression is identical to 
	 * the given expression.
	 *
	 * @return If the other object is a composed expression, true if and only if both
	 *         composed expressions have the same number of operands, and if
	 *         all corresponding operands of both composed expressions are identical
	 *         to each other.
	 *       | if (other instanceof ComposedExpression)
	 *       |   then result ==
	 *       |     (getNbOperands() == ((ComposedExpression)other).getNbOperands()) &&
	 *       |     ( for each I in 1..getNbOperands():
	 *       |         getOperandAt(I).isIdenticalTo(((ComposedExpression)other.getOperandAt(I)) )
	 */
	@Override
	public boolean isIdenticalTo(Expression other) {
		if ((other == null) || (getClass() != other.getClass()))
			return false;
		DoubleComposedExpression otherExpr = (DoubleComposedExpression) other;
		if (getNbOperands() != otherExpr.getNbOperands())
			return false;
		for (int pos = 1; pos <= getNbOperands(); pos++)
			if (!getOperandAt(pos).isIdenticalTo(otherExpr.getOperandAt(pos)))
				return false;
		return true;
	}

	/**
	 * Check whether the state of this composed expression can be
	 * changed.
	 * 
	 * @return True if and only if the state of at least one of
	 *         the operands of this composed expression can be
	 *         changed.
	 *       | result ==
	 *       |   for some I in 1..getNbOperands():
	 *       |     getOperandAt(I).isMutable()
	 */
	public boolean isMutable() {
		for (int i = 1; i <= getNbOperands(); i++)
			if (getOperandAt(i).isMutable())
				return true;
		return false;
	}

//	/**
//	 * Return a clone of this composed expression.
//	 * 
//	 * @return Each operand of the resulting composed expression is the
//	 *         same as the corresponding operand of this expression, if
//	 *         and only if that operand is immutable.
//	 *       | for each I in 1..getNbOperands():
//	 *       |   ( (result.getOperandAt(I) == this.getOperandAt(I)) ==
//	 *       |       (! this.getOperandAt(I).isMutable()) )
//	 */
//	@Override
//	public DoubleComposedExpression clone() {
//		DoubleComposedExpression result = (DoubleComposedExpression) super.clone();
//		if (isMutable())
//			for (int i = 1; i <= getNbOperands(); i++)
//				if (getOperandAt(i).isMutable())
//					setOperandAt(i, getOperandAt(i).clone());
//		return result;
//	}

	/**
	 * Return the number of operands involved in this composed
	 * expression.
	 */
	@Basic
	public abstract int getNbOperands();

	/**
	 * Check whether this composed expression can have the given
	 * number of operands as its number of operands.
	 *
	 * @param  nbOperands
	 *         The number of operands to check.
	 * @return False if the given number is not positive.
	 *       | if (number <= 0)
	 *       |   then result == false
	 * @note   It is important not to define this method as a static method.
	 *         In that case, we would not be able to strengthen its definition
	 *         in e.g. BinaryExpression.
	 */
	@Raw
	public boolean canHaveAsNbOperands(int nbOperands) {
		return nbOperands > 0;
	}

	/**
	 * Return the operand of this composed expression at the given index.
	 *
	 * @param  index
	 *         The index of the requested operand.
	 * @throws IndexOutOfBoundsException
	 *         The given index is not positive or exceeds the
	 *         number of operands for this composed expression.
	 *       | (index < 1) || (index > getNbOperands())
	 */
	@Basic
	public abstract Expression getOperandAt(int index)
			throws IndexOutOfBoundsException;

	/**
	 * Check whether this composed expression can have the given
	 * expression as one of its operands.
	 *
	 * @param  expression
	 *         The expression to check.
	 * @return True if and only if the given expression is effective,
	 *         and if that expression does not have this composed
	 *         expression as a subexpression.
	 *       | result ==
	 *       |   ( (expression != null)
	 *       |  && (! expression.hasAsSubExpression(this)) )
	 */
	public boolean canHaveAsOperand(DoubleExpressions expression) {
		return (expression != null) && (!expression.hasAsSubExpression(this));
	}

	/**
	 * Set the operand for this composed expression at the given
	 * index to the given operand.
	 * 
	 * @param  index
	 *         The index at which the operand must be registered.
	 * @param  operand
	 *         The operand to be registered.
	 * @pre    The given index is positive and does not exceed the
	 *         number of operands for this composed expression.
	 *       | (index >= 1) && (index <= getNbOperands())
	 * @pre    This expression can have the given operand as one
	 *         of its operands.
	 *       | canHaveAsOperand(operand)
	 * @post   The operand at the given index of this composed
	 *         expression is the same as the given operand.
	 *       | new.getOperandAt(index) == operand
	 */
	protected abstract void setOperandAt(int index, DoubleExpressions operand);

	/**
	 * Check whether this composed expression has the given expression
	 * as one of its subexpressions.
	 *
	 * @return True if and only if the given expression is the same
	 *         expression as this composed expression, or if the given
	 *         expression is a subexpression of one of the operands
	 *         of this composed expression.
	 *       | result ==
	 *       |     (expression == this)
	 *       |  || ( for some I in 1..getNbOperands():
	 *       |         getOperandAt(I).hasAsSubExpression(expression) )
	 */
	@Override
	public boolean hasAsSubExpression(Expression expression) {
		if (expression == this)
			return true;
		for (int pos = 1; pos <= getNbOperands(); pos++)
			if (getOperandAt(pos).hasAsSubExpression(expression))
				return true;
		return false;
	}

	/**
	 * Return the symbol representing the operator of this composed
	 * expression.
	 * 
	 * @return An effective, non-empty string.
	 *       | result.length() > 0
	 */
	public abstract String getOperatorSymbol();


}

