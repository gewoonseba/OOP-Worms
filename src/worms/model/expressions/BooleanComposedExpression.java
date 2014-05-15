package worms.model.expressions;


import be.kuleuven.cs.som.annotate.*;

/**
 * A class of composed arithmetic expressions.
 *   A composed expression involves a single operator applied
 *   to a series of operands.
 * 
 * @invar    Each composed expression can have its number of
 *           operands as number of operands.
 *         | canHaveAsNbOperands(getNbOperands())
 * @invar    Each composed expression can have each of its
 *           operands as operand.
 *         | for each I in 1..getNbOperands():
 *         |   canHaveAsOperand(getOperandAt(I))
 * 
 * @version  2.0
 * @author   Eric Steegmans
 */
public abstract class BooleanComposedExpression extends BooleanExpression {

	

	

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
//	public BooleanComposedExpression clone() {
//		BooleanComposedExpression result = (BooleanComposedExpression) super.clone();
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
	protected abstract void setOperandAt(int index, BooleanExpression operand);

	
	/**
	 * Return the symbol representing the operator of this composed
	 * expression.
	 * 
	 * @return An effective, non-empty string.
	 *       | result.length() > 0
	 */
	public abstract String getOperatorSymbol();


}
