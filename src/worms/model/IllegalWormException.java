package worms.model;

import be.kuleuven.cs.som.annotate.*;

public class IllegalWormException extends RuntimeException {
	/**
	 * Initialize this new illegal worm exception with the given worm.
	 * 
	 * @param worm
	 * @post The worm of this new illegal worm exception is equal to the given worm.
	 *      | new.getWorm() == worm
	 * @effect This new illegal worm exception is further initialized 
	 *        as a new runtime exception involving no diagnostic message and no cause
	 *        | super()
	 */
	public IllegalWormException(Worm worm){
		this.worm = worm;
	}
	
	/**
	 * Return the worm of this illegal worm exception.
	 */
	@Basic @Raw @Immutable
	public Worm getWorm(){
		return worm;
	}
	
	/**
	 * Variable registering the worm of this illegal worm exception.
	 */
	private final Worm worm;
}
