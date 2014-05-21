package worms.model.expressions;

public abstract class Comparator extends BooleanExpression {
	
	public Comparator(Expression left, Expression right){
		this.left = left;
		this.right = right;
	}
	
	public abstract String getOperatorSymbol();
	
	public abstract Comparator clone();
	
	public Expression getLeft(){
		return this.left;
	}
	
	private Expression left;
	
	public Expression getRight(){
		return this.right;
	}
	
	private Expression right;

}
