package worms.model.expressions;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;

public abstract class DoubleUnaryExpression extends DoubleComposedExpression {

	@Model
	protected DoubleUnaryExpression(DoubleExpression operand)
			throws IllegalOperandException {
		this.operand = operand;
	}

	@Basic
	public DoubleExpression getOperand() {
		return operand;
	}

	private final DoubleExpression operand;

	@Override
	public String toString() {
		if (getOperand() instanceof DoubleBasicExpression)
			return getOperatorSymbol() + getOperand().toString();
		if (getOperand() instanceof DoubleComposedExpression)
			return getOperatorSymbol() + "(" + getOperand().toString() + ")";
		throw new Error("Unknown expression type!");
	}
}
