package worms.model.expressions;

public abstract class DoubleBasicExpression extends DoubleExpressions {

	@Override
	public boolean hasAsSubExpression(Expression<Double> expression) {
		return expression==this;
	}

}
