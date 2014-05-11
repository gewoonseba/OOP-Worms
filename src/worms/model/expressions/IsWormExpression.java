package worms.model.expressions;
import worms.model.types.*;
import worms.model.Worm;

public class IsWormExpression<E> extends BooleanExpressions {
	
	private final E entity;
	
	public IsWormExpression(EntityExpression<E> entity) {
		this.entity = entity.getValue().getValue();
	}

	@Override
	public Boolean getValue() {
		return (entity instanceof Worm);	
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
