package worms.model.expressions;

import worms.model.types.Entity;

public class EntityExpression<E> extends Expression {
	
	public EntityExpression(Entity<E> entity){
		this.value = entity;
	}
	
	private final Entity<E> value;
	
	public Entity<E> getValue(){
		return this.value;
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
	public EntityExpression<E> clone() {
		return new EntityExpression<E>(getValue());
	}


}
