package worms.model.expressions;

import worms.model.Food;
import worms.model.types.BooleanType;

public class IsFoodExpression extends BooleanExpression {
	
	public IsFoodExpression(Expression entity) {
		this.entity = entity;
	}

	@Override
	public BooleanType getValue() {
		if (entity==null || entity.getValue()==null)
			return new BooleanType(false);
		return new BooleanType(entity.getValue().getValue() instanceof Food);	
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
	
	public Expression getEntity(){
		return this.entity;
	}

	private final Expression entity;

	@Override
	public IsFoodExpression clone() {
		return new IsFoodExpression(getEntity());
	}

}
