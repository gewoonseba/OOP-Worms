package worms.model.expressions;
import worms.model.Worm;

public abstract class OperationExpression extends Expression{
	
	 public OperationExpression(Expression entity) {
			if (entity instanceof SelfWormExpression)
				this.entity= new EntityExpression<Worm>((new SelfWormExpression()).getValue());
			else
				this.entity = entity;
		}
	
	public Expression getEntity(){
		return this.entity;
	}

	private Expression entity;

	@Override
	public abstract String toString();
}
