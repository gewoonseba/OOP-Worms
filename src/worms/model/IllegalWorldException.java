package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

public class IllegalWorldException extends RuntimeException {
	/**
	 * Initialize this new illegal world exception with the given world.
	 * 
	 * @param world
	 * @post The worm of this new illegal world exception is equal to the given world.
	 *      | new.getWorld() == world
	 * @effect This new illegal world exception is further initialized 
	 *        as a new runtime exception involving no diagnostic message and no cause
	 *        | super()
	 */
	public IllegalWorldException(World world){
		this.world = world;
	}
	
	/**
	 * Return the world of this illegal world exception.
	 */
	@Basic @Raw @Immutable
	public World getWorld(){
		return world;
	}
	
	/**
	 * Variable registering the world of this illegal world exception.
	 */
	private final World world;
}
