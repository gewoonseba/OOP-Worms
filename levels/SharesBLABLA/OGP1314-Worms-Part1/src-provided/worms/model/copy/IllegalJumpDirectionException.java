package model.copy;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

public class IllegalJumpDirectionException extends RuntimeException {
	/**
	 * Initialize this new illegal jump direction exception with the given jump direction and the given worm.
	 * 
	 * @param direction, worm
	 * @post The direction of this new illegal jump direction exception is equal to the given direction.
	 *      | new.getDirection() == direction
	 * @post The worm of this new illegal jump direction exception is equal to the given worm.
	 *      | new.getWorm() == worm
	 * @effect This new illegal jump direction exception is further initialized 
	 *        as a new runtime exception involving no diagnostic message and no cause
	 *        | super()
	 */
	public IllegalJumpDirectionException(double direction,Worm worm){
		this.direction = direction;
		this.worm = worm;
	}
	
	/**
	 * Return the direction of this illegal jump direction exception.
	 */
	@Basic @Raw @Immutable
	public double getDirection(){
		return direction;
	}
	
	/**
	 * Return the worm of this illegal jump direction exception.
	 */
	@Basic @Raw @Immutable
	public Worm getWorm(){
		return worm;
	}
	
	/**
	 * Variable registering the direction of this illegal jump direction exception.
	 */
	private final double direction;
	
	/**
	 * Variable registering the worm of this illegal jump direction exception.
	 */
	private final Worm worm;
	


}
