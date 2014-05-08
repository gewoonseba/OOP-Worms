package worms.model.types;


import worms.model.Food;
import worms.model.Worm;

public class Entity {
	
	private Worm worm;
	private Food food;
	
	public Entity(Worm worm){
		this.worm=worm;
	}
	
	public Entity(Food food){
		this.food=food;
	}
	
	public Entity(){
	}
	
	public Worm getWorm(){
		return this.worm;
	}
	
	public Food getFood(){
		return this.food;
	}
}
