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


	@Override
	public boolean equals(Object other) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Expression getEntity(){
		return this.entity;
	}

	private final Expression entity;

	@Override
	public String toString() { 
		//TODO generated method stub
		return null;
	}

	@Override
	public IsWormExpression clone() {
		return new IsWormExpression(getEntity());
	}

}
