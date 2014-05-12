package worms.model.expressions;



public class BooleanNegation extends BooleanUnaryExpression {

	/**
	 * Initialize this new negation with given operand.
	 *
	 * @param  operand
	 *         The operand for this new negation.
	 * @effect This new negation is initialized as a unary expression
	 *         with the given operand as its operand.
	 *       | super(operand)
	 */
	public BooleanNegation(BooleanExpressions operand) throws IllegalOperandException {
		super(operand);
	}

	
	
	/**
	 * Return the value of this negation.
	 *
	 * @return The negation of the value of the operand of this negation.
	 *       | result == - getOperand().getValue()
	 */
	@Override
	public Boolean getValue() {
		return !(getOperand().getValue());
	}

	/**
	 * Return the symbol representing the operator of this negation.
	 * 
	 * @return The string "-"
	 *       | result.equals("-")
	 */
	@Override
	public String getOperatorSymbol() {
		return "!";
	}

}