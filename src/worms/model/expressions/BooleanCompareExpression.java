package worms.model.expressions;

public abstract class BooleanCompareExpression extends BooleanExpression {


	
	public abstract String getOperatorSymbol();
	
	public abstract Expression getLeftOperand();
	
	public abstract Expression getRightOperand();

}
