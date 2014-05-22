package worms.model.expressions;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;

public abstract class DoubleUnaryExpression extends DoubleComposedExpression {

	@Model
	protected DoubleUnaryExpression(Expression operand){
		this.operand = operand;
	}

	@Basic
	public Expression getOperand() {
		return operand;
	}

	private final Expression operand;

	@Override
	public String toString() {
		if (getOperand() instanceof DoubleBasicExpression)
			return getOperatorSymbol() + getOperand().toString();
		if (getOperand() instanceof DoubleComposedExpression)
			return getOperatorSymbol() + "(" + getOperand().toString() + ")";
		throw new Error("Unknown expression type!");
	}
}
