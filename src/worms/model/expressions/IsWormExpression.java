package worms.model.expressions;
import worms.model.types.*;
import worms.model.Worm;

public class IsWormExpression<E> extends BooleanExpression {
	
	private final E entity;
	
	public IsWormExpression(Expression entity) {
		this.entity = ((EntityExpression<E>)entity).getValue().getValue();
	}

	@Override
	public BooleanType getValue() {
		return new BooleanType(entity instanceof Worm);	
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
