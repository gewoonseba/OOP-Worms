package worms.model.expressions;
import worms.model.types.*;
import worms.model.Worm;

public class IsWormExpression extends BooleanExpression {
	
	public IsWormExpression(Expression entity) {
		this.entity = entity;
	}

	@Override
	public BooleanType getValue() {
		if (entity==null || entity.getValue()==null)
			return new BooleanType(false);
		return new BooleanType(entity.getValue().getValue() instanceof Worm);	
	}
	
	public Expression getEntity(){
		return this.entity;
	}

	private final Expression entity;

	@Override
	public String toString() { 
		return ("isWorm(" + getEntity().toString() + ")");
	}

	@Override
	public IsWormExpression clone() {
		return new IsWormExpression(getEntity());
	}

}
