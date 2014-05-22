package worms.model.types;


import worms.model.Food;
import worms.model.Worm;

public class Entity<E> extends Type{
	public Entity(){
		this.value=null;
	}
	
	public Entity(E object){
		setValue(object);
	}
	
	public void setValue(E value){
		this.value= value;
	}
	
	private E value;
	
	@Override
	public E getValue(){
		return this.value;
	}

	@Override
	public boolean equals(Type first) {
		if (! (first instanceof Entity))
			return false;
		if (! (this.getValue() == ((Entity<?>) first).getValue()))
			return false;
		return true;
	}
	@Override
	public Entity<E> clone(){
		return new Entity<E>(getValue());
	}
}
