package worms.model.expressions;

public class AndExpression<E> extends BooleanCompareExpression<E> {
	
<<<<<<< HEAD
	private final Expression<E> leftOperand;
	private final Expression<E> rightOperand;
	
	public AndExpression(Expression<E> expr1,Expression<E> expr2){
		this.leftOperand= expr1;
		this.rightOperand= expr2;
=======
	private final Expression<Boolean> expr1;
	private final Expression<Boolean> expr2;
	
	public AndExpression(Expression<Boolean> expr1,Expression<Boolean> expr2){
		this.expr1=expr1;
		this.expr2=expr2;
>>>>>>> ceabdadfda7ae534a2c7a19482a1aba21e2e6577
	}
	@Override
	public String getOperatorSymbol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean getValue() {
<<<<<<< HEAD
		return ((Boolean)getLeftOperand().getValue() && (Boolean)getRightOperand().getValue());
=======
		return (expr1.getValue()&&expr2.getValue());
>>>>>>> ceabdadfda7ae534a2c7a19482a1aba21e2e6577
	}

	@Override
	public boolean isMutable() {
		// TODO Auto-generated method stub
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
	@Override
	public Expression<E> getLeftOperand() {
		return leftOperand;
	}
	@Override
	public Expression<E> getRightOperand() {
		return rightOperand;
	}

}
