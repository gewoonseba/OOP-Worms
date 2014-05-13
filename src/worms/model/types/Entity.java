package worms.model.types;


import worms.model.Food;
import worms.model.Worm;

public class Entity<E> extends Type{
	public Entity(){
	}
	
	public void setValue(E value){
		this.value= value;
	}
	
	private E value;
	
	public E getValue(){
		return this.value;
	}
}
