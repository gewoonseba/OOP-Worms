package worms.model.expressions;

public abstract class BooleanExpressions extends Expression {

	@Override
	public boolean hasAsSubExpression(Expression expression) {
		if (expression==this)
			return true;
		return false;
	}
	
	public abstract boolean getBooleanValue();

}
