package worms.model.expressions;

public abstract class BooleanCompareExpression<E> extends BooleanExpressions {


	@Override
	public boolean hasAsSubExpression(Expression<Boolean> expression) {
		return expression==this;
	}
	
	public abstract String getOperatorSymbol();
	
	public abstract Expression<E> getLeftOperand();
	
	public abstract Expression<E> getRightOperand();

}
