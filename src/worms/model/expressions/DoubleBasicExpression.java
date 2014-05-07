package worms.model.expressions;

public abstract class DoubleBasicExpression extends DoubleExpressions {

	@Override
	public boolean hasAsSubExpression(Expression expression) {
		return expression==this;
	}

}
