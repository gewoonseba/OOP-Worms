package worms.model;

import be.kuleuven.cs.som.annotate.*;

public class IllegalFoodException extends RuntimeException {
	/**
	 * Initialize this new illegal food exception with the given food.
	 * 
	 * @param food
	 * @post The food of this new illegal food exception is equal to the given food.
	 *      | new.getFood() == food
	 * @effect This new illegal worm exception is further initialized 
	 *        as a new runtime exception involving no diagnostic message and no cause
	 *        | super()
	 */
	public IllegalFoodException(Food food){
		this.food = food;
	}
	
	/**
	 * Return the food of this illegal food exception.
	 */
	@Basic @Raw @Immutable
	public Food getFood(){
		return food;
	}
	
	/**
	 * Variable registering the food of this illegal food exception.
	 */
	private final Food food;
}
