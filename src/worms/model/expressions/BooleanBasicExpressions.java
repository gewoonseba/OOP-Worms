package worms.model.expressions;

public abstract class BooleanBasicExpressions extends BooleanExpression {

	@Override
	public boolean hasAsSubExpression(Expression<Boolean> expression) {
		return expression==this;
	}

}
