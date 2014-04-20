package worms.model;

import be.kuleuven.cs.som.annotate.*;

public class IllegalAPException extends RuntimeException {
	/**
	 * Initialize this new illegal AP exception with the given AP and the given worm.
	 * 
	 * @param currentAP, worm
	 * @post The currentAP of this new illegal AP exception is equal to the given currentAP.
	 *      | new.getAP() == currentAP
	 * @post The worm of this new illegal AP exception is equal to the given worm.
	 *      | new.getworm() == worm
	 * @effect This new illegal AP exception is further initialized 
	 *        as a new runtime exception involving no diagnostic message and no cause
	 *        | super()
	 */
	public IllegalAPException(int currentAP,Worm worm){
		this.currentAP = currentAP;
		this.worm = worm;
	}
	
	/**
	 * Return the currentAP of this illegal AP exception.
	 */
	@Basic @Raw @Immutable
	public int getAP(){
		return currentAP;
	}
	
	/**
	 * Return the worm of this illegal AP exception.
	 */
	@Basic @Raw @Immutable
	public Worm getWorm(){
		return worm;
	}
	
	/**
	 * Variable registering the currentAP of this illegal AP exception.
	 */
	private final int currentAP;
	
	/**
	 * Variable registering the worm of this illegal AP exception.
	 */
	private final Worm worm;
	



}
