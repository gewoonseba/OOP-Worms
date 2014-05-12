package worms.model.expressions;

public abstract class BooleanExpression extends Expression<Boolean> {

	@Override
	public boolean hasAsSubExpression(Expression<Boolean> expression) {
		if (expression==this)
			return true;
		return false;
	}
	
	public abstract Boolean getValue();

}
