package worms.model.expressions;

public abstract class DoubleExpression extends Expression<Double> {

	@Override
	public boolean hasAsSubExpression(Expression<Double> expression) {
		return expression==this;
	}

	public abstract Double getValue();
}
