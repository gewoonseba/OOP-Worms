package worms.model.expressions;

public abstract class BooleanBasicExpressions extends BooleanExpressions {

	@Override
	public boolean hasAsSubExpression(Expression<Boolean> expression) {
		return expression==this;
	}

}
