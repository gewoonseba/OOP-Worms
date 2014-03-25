package worms.model;

import be.kuleuven.cs.som.annotate.*;

public class IllegalNameException extends RuntimeException {
	/**
	 * Initialize this new illegal name exception with the given name.
	 * 
	 * @param name
	 * @post The name of this new illegal name  exception is equal to the given name.
	 *      | new.getName() == name
	 * @effect This new illegal name exception is further initialized 
	 *        as a new runtime exception involving no diagnostic message and no cause
	 *        | super()
	 */
	public IllegalNameException(String name){
		this.name = name;
	}
	
	/**
	 * Return the name of this illegal name exception.
	 */
	@Basic @Raw @Immutable
	public String getName(){
		return name;
	}
	
	/**
	 * Variable registering the name of this illegal name exception.
	 */
	private final String name;

}
