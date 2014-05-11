package worms.model.expressions;

public abstract class DoubleExpressions extends Expression<Double> {

	@Override
	public boolean hasAsSubExpression(Expression<Double> expression) {
		return expression==this;
	}

	public abstract Double getValue();
}
