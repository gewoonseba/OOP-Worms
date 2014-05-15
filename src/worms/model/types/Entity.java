package worms.model.types;


import worms.model.Food;
import worms.model.Worm;

public class Entity<E> extends Type{
	public Entity(){
	}
	
	public Entity(E object){
		setValue(object);
	}
	
	public void setValue(E value){
		this.value= value;
	}
	
	private E value;
	
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
}
