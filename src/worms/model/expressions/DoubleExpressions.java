package worms.model.expressions;

public abstract class DoubleExpressions extends Expression {

	@Override
	public boolean hasAsSubExpression(Expression expression) {
		return expression==this;
	}

	public abstract double getValue();
}
