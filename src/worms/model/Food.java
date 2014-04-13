package worms.model;

public class Food {
	/**
	 * create a new food.
	 * @param world
	 * @param x
	 * @param y
	 * @effect the world of this food is equal to world
	 *         |this.world=world
	 * @effect the x coordinate of this food is equal to x
	 *         |this.x = x
	 * @effect the y coordinate of this food is equal to y
	 *         |this.y = y
	 * @post   the value of the field terminated is 0
	 *         |this.terminated=0
	 */
	public Food(World world,double x,double y){
		this.setWorld(world);
		this.setX(x);
	    this.setY(y);
	    this.terminated=0;
	}
	
	/**
	 * variable registering the world this food was created in.
	 */
	private World world;
	
	/**
	 * Sets the world of the food to world.
	 * @param world
	 * @post the foods world is  equal to world.
	 *       |new.world=world
	 */
	public void setWorld(World world){
		this.world=world;
	}
	
	/**
	 * returns the world of the current food.
	 * @return returns the world
	 *         |return ==this.world
	 */
	public World getWorld(){
		return this.world;
	}
	
	/**
	 * variable registering the y coordinate of the food.
	 */
	private double y;
	
	/**
	 * variable registering the x coordinate of the food.
	 */
	private double x;
	
	/**
	 * sets the x coordinate of the given food to x
	 * @param x
	 * @post the new x coordinate of the food is equal to x.
	 *      |new.x== x
	 */
	public void setX(double x){
		this.x = x;
	}
	
	/**
	 * sets the y coordinate of the given food to y.
	 * @param y
	 * @post the new y coordinate of the food is equal to y.
	 *      |new.y== y
	 */
	public void setY(double y){
		this.y = y;
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
	 * returns the y coordinate of the given food.
	 * @return returns the y coordinate.
	 *        |return =this.y
	 */
	public double getY(){
		return this.y;
	}
	
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
