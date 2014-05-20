package worms.model.expressions;

public abstract class Comparator extends BooleanExpression {
	
	public abstract String getOperatorSymbol();
	
	public abstract Expression getLeftOperand();
	
	public abstract Expression getRightOperand();

}
