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
	public String toString() {
		return getValue().toString();
	}

	@Override
	public EntityExpression<E> clone() {
		return new EntityExpression<E>(getValue());
	}
}
