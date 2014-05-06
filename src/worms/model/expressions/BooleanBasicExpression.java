package worms.model.expressions;

public abstract class BooleanBasicExpression extends BooleanExpressions {

	@Override
	public boolean hasAsSubExpression(Expression expression) {
		return expression==this;
	}

}