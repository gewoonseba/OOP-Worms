package worms.model.expressions;
import worms.model.Worm;

public abstract class OperationExpression extends Expression{
	
	 public OperationExpression(Expression entity) {
		this.entity = entity;
		}
	
	public Expression getEntity(){
		return this.entity;
	}

	private Expression entity;

	@Override
	public abstract String toString();
}
