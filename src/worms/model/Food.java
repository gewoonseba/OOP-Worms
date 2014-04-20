package worms.model;

import be.kuleuven.cs.som.annotate.*;

public class Food {
	/**
	 * Create a new food at the given x and y coordinates.
	 * @param x
	 * @param y
	 * @effect the x coordinate of this food is equal to x
	 *         |this.x = x
	 * @effect the y coordinate of this food is equal to y
	 *         |this.y = y
	 * @post   the value of the field terminated is 0
	 *         |this.terminated=0
	 */
	public Food(double x,double y){
		this.setX(x);
	    this.setY(y);
	    this.terminated=0;
	}

	/**
	 * Method to check whether this food has a world.
	 * @return True if getWorld() does not return null
	 * 		| return == (! getWorld() == null)
	 */
	public boolean hasWorld(){
		return (! (getWorld() == null));
	}
	
	/**
	 * Method to set the world of this food to the given world.
	 * @param world
	 * 		The world that should be set as the world of this food.
	 * @post If the world is valid, the new World of this food is equal to the given world.
	 * 		| new.getWorld() == world.
	 * @throws IllegalWorldException
	 * 		This food cannot have the given world as its world.
	 * 		| ! canHaveAsWorld(world)
	 * @throws IllegalStateException
	 * 		This food already has a world.
	 * 		| hasWorld()
	 */
	public void setWorldTo(World world) throws IllegalWorldException, IllegalStateException{
		if (! canHaveAsWorld(world))
			throw new IllegalWorldException(world);
		if (hasWorld())
			throw new IllegalStateException();
		this.world = world;
	}
	
	/**
	 * returns the world of the current food.
	 */
	public World getWorld(){
		return this.world;
	}
	
	/**
	 * Method to check whether this food can have the given world as its world.
	 * @param world
	 * 		The world to be checked.
	 * @return True if and only if the given world is not null and does not already contain this worm.
	 * 		| return == (world != null) && (! world.hasAsWorm(this))
	 */
	public boolean canHaveAsWorld(World world){
		return (world != null) && (! world.hasAsFood(this));
	}
	
	/**
	 * Method to remove the world of this food and remove this food from the world it belonged to.
	 * @throws NullPointerException
	 * 		This food has no world
	 * 		| ! hasWorld()
	 */
	public void removeWorld() throws NullPointerException{
		if (! hasWorld())
			throw new NullPointerException();
		World formerWorld = getWorld();
		this.world = null;
		formerWorld.removeAsFood(this);
	}
	/**
	 * Variable registering the world of this food.
	 */
	private World world;
	
	/**
	 * Sets the x coordinate of the given food to x
	 * @param x
	 * @post the new x coordinate of the food is equal to x.
	 *      |new.x== x
	 */
	public void setX(double x){
		this.x = x;
	}
	
	/**
	 * returns the x coordinate of the given food.
	 * @return returns the x coordinate.
	 *        |return =this.x
	 */
	public double getX(){
		return this.x;
	}
	
	/**
	 * Variable registering the x coordinate of the food.
	 */
	private double x;
	
	/**
	 * Sets the y coordinate of the given food to y.
	 * @param y
	 * @post the new y coordinate of the food is equal to y.
	 *      |new.y== y
	 */
	public void setY(double y){
		this.y = y;
	}
	
	/**
	 * returns the y coordinate of the given food.
	 * @return returns the y coordinate.
	 *        |return =this.y
	 */
	public double getY(){
		return this.y;
	}
	
	/**
	 * Variable registering the y coordinate of the food.
	 */
	private double y;
	
	/**
	 * Static method to retrieve the radius of any food.
	 */
	@Basic
	public static double getRadius(){
		return radius;
	}
	
	/**
	 * Variable registering the radius of any food.
	 */
	private static final double radius = 0.20;
	
	/**
	 * checks whether the food is active.
	 * @return true if and only if terminated is zero
	 *         | return = (this.terminated==0)
	 */
	public boolean isActive(){
		if (this.terminated==0){
				return true;
		}
		else {
			return false;
		}
	}
	/**
	 * method to terminate the given food.
	 */
	public void terminate(){
		this.terminated=1;
	}
	
	/**
	 * variable to determine whether food is terminated.
	 */
	private int terminated;

}
