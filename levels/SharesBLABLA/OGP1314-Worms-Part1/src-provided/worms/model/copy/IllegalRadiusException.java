/**
 * A class of exceptions signaling illegal radii for the worms.
 *  Each illegal radius exception involves the illegal radius.
 */

package model.copy;

import be.kuleuven.cs.som.annotate.*;

public class IllegalRadiusException extends RuntimeException {

	/**
	 * Initialize this new illegal radius exception with the given radius.
	 * 
	 * @param radius
	 * @post The radius of this new illegal radius exception is equal to the given radius.
	 *      | new.getRadius() == radius
	 * @effect This new illegal radius exception is further initialized 
	 *        as a new runtime exception involving no diagnostic message and no cause
	 *        | super()
	 */
	public IllegalRadiusException(double radius){
		this.radius = radius;
	}
	
	/**
	 * Return the radius of this illegal radius exception.
	 */
	@Basic @Raw @Immutable
	public double getRadius(){
		return radius;
	}
	
	/**
	 * Variable registering the radius of this illegal radius exception.
	 */
	private final double radius;

}
