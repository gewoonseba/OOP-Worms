package worms.model.expressions;
import worms.model.types.*;
import worms.model.Food;
import worms.model.Worm;

public class IsFoodExpression<E> extends BooleanExpression {
	
	private final E entity;
	
	public IsFoodExpression(Expression entity) {
		this.entity = ((EntityExpression<E>) entity).getValue().getValue();
	}

	@Override
	public BooleanType getValue() {
		return new BooleanType(entity instanceof Food);	
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

}
