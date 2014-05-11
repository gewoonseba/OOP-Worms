package worms.model.expressions;

import worms.model.types.Entity;

public class EntityExpression<E> extends Expression<Entity<E>> {
	
	public EntityExpression(Entity<E> entity){
		this.value = entity;
	}
	
	private final Entity<E> value;
	
	public Entity<E> getValue(){
		return this.value;
	}

	
	@Override
	public boolean hasAsSubExpression(Expression<Entity<E>> expression) {
		// TODO Auto-generated method stub
		return false;
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
