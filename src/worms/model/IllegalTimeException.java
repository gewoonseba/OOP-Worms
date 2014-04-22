package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

public class IllegalTimeException extends RuntimeException {
	
	/**
	 * Initialize this new illegal time exception with the given time.
	 * 
	 * @param t
	 * @param worm
	 * @post The time of this new illegal time exception is equal to the given time.
	 *      | new.getTime() == t
	 * @post The worm of this new illegal time direction exception is equal to the given worm.
	 *      | new.getWorm() == worm
	 * @effect This new illegal time exception is further initialized 
	 *        as a new runtime exception involving no diagnostic message and no cause
	 *        | super()
	 */
	public IllegalTimeException(double t, Worm worm){
		this.time = t;
		this.worm = worm;
		this.projectile = null;
	}
	
	/**
	 * Initialize this new illegal time exception with the given time.
	 * 
	 * @param t
	 * @param projectile
	 * @post The time of this new illegal time exception is equal to the given time.
	 *      | new.getTime() == t
	 * @post The projectile of this new illegal time direction exception is equal to the given projectile.
	 *      | new.getProjectile() == projectile
	 * @effect This new illegal time exception is further initialized 
	 *        as a new runtime exception involving no diagnostic message and no cause
	 *        | super()
	 */
	public IllegalTimeException(double t, Projectile projectile){
		this.time = t;
		this.projectile = projectile;
		this.worm = null;
	}
	
	/**
	 * Return the time of this illegal time exception.
	 */
	@Basic @Raw @Immutable
	public double getTime(){
		return time;
	}
	
	/**
	 * Variable registering the time of this illegal time exception.
	 */
	private final double time;
	
	/**
	 * Return the worm of this illegal time exception.
	 */
	@Basic @Raw @Immutable
	public Worm getWorm(){
		return worm;
	}
	
	/**
	 * Variable registering the worm of this illegal time exception.
	 */
	private final Worm worm;
    
	/**
	 * Retrun the projectile of this illegal time exception.
	 * @return
	 */
	@Basic @Raw @Immutable
	public Projectile getProjectile(){
		return projectile;
	}
	
	/**
	 * Variable registering the projectile of this illegal time exception.
	 */
	private final Projectile projectile;
}
