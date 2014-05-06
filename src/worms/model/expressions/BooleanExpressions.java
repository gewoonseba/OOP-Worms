package worms.model.expressions;

public abstract class BooleanExpressions extends Expression {

	@Override
	public boolean hasAsSubExpression(Expression expression) {
		if (expression==this)
			return true;
		return false;
	}


	@Override
	public boolean equals(Object other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public abstract boolean getBooleanValue();

}
