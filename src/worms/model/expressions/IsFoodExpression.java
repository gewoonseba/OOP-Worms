package worms.model.expressions;
import worms.model.types.*;
import worms.model.Food;
import worms.model.Worm;

public class IsFoodExpression<E> extends BooleanExpressions {
	
	private final E entity;
	
	public IsFoodExpression(Entity<E> entity) {
		this.entity = entity.getValue();
	}

	@Override
	public Boolean getValue() {
		return (entity instanceof Food);	
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

}
