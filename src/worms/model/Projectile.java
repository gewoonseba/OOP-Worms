package worms.model;

import java.nio.channels.IllegalSelectorException;
import java.util.*;

import be.kuleuven.cs.som.annotate.*;

/**
 * A Class of Projectiles. A Projectile has a location (x,y), a direction (expressed as a radian value),
 * a mass and a propulsion yield. Projectiles are able to jump.
 * 
 * @author Matthias Maeyens 2BBiCwsElt2, Sebastian Stoelen 2BBiCwsElt2
 * @version 1.0
 *
 */
public class Projectile {
	
	public Projectile(double x, double y, double direction, double mass, int yield){
		this.setX(x);
		this.setY(y);
		this.setDirection(direction);
		this.mass = mass;
		this.yield = yield;
		this.terminated = false;
		
	}
	
	/**
	 * Method to return the x coordinate of the current location of this projectile.
	 */
	@Basic
	public double getX(){
		return this.x;
	}

	/**
	 * Method to set x to the given x.
	 * @param x
	 * @post The new x coordinate of this projectile is equal to the given x.
	 * 		| new.getX() == x
	 * @throws IllegalArgumentException
	 * 		The given x coordinate is not a valid coordinate
	 * 		| ! isValidCoordinate(x)
	 */
	public void setX(double x) throws IllegalArgumentException{
		if (! isValidCoordinate(x))
			throw new IllegalArgumentException();
		this.x = x;
	}
	
	/**
	 * Variable registering the x coordinate of the location of this projectile.
	 */
	private double x = 0;
	
	/**
	 * Method to return the y coordinate of the current location of this projectile.
	 */
	@Basic
	public double getY(){
		return this.y;
	}

	/**
	 * Method to set y to the given y.
	 * @param y
	 * @post The new y coordinate of this projectile is equal to the given y.
	 * 		| new.getY() == y
	 * @throws IllegalArgumentException
	 * 		The given y coordinate is not a valid coordinate
	 * 		| ! isValidCoordinate(y)
	 */
	public void setY(double y) throws IllegalArgumentException{
		if (! isValidCoordinate(y))
			throw new IllegalArgumentException();
		this.y = y;
	}
	
	/**
	 * Variable registering the y coordinate of the location of this projectile.
	 */
	private double y = 0;
	
	/**
	 * Method to check whether the given coordinate is a valid coordinate.
	 * @param coordinate
	 * 		The coordinate to be checked.
	 * @return True if and only if the given coordinate is not equal to Double.Nan
	 * 		| return == (coordinate != Double.Nan)
	 */
	@Raw
	public boolean isValidCoordinate(double coordinate){
		return (coordinate != Double.NaN);
	}
	
	/**
	 * Method to return the direction of the given projectile.
	 */
	@Basic
	public double getDirection(){
		return direction;
	}
	
	/**
	 * Check whether the given direction is a valid direction for a projectile.
	 * @param direction
	 *        The direction that has to be checked
	 * @return True if and only if the given direction is higher or equal to zero 
	 *         and less than 2 times PI.
	 *         | return == ((direction>=0) && (direction<=2*Math.PI))
	 */
	@Raw
	public static boolean isValidDirection(double direction){
		return ((direction >= 0) && (direction < 2*Math.PI));
	}
	
	/**
	 * Method to set the direction of this projectile to the given direction.
	 * @Pre The given direction must be a valid direction.
	 * 		| isValidDirection(direction)
	 * @param direction
	 * 		The new direction of the projectile.
	 * @post The new direction of this projectile is equal to the given projectile.
	 * 		| new.getDirection() == direction.
	 */
	public void setDirection(double direction){
		assert isValidDirection(direction);
		this.direction = direction;
	}
	
	/**
	 * Variable registering the direction of the given projectile.
	 */
	private double direction = 0;
	
	/**
	 * Method to return the mass of the given projectile.
	 */
	public double getMass(){
		return this.mass;
	}
	
	/**
	 * Variable registering the mass of this projectile.
	 */
	private final double mass;
	
	/**
	 * Method to return the propulsion yield of this projectile.
	 */
	@Basic
	public int getYield(){
		return this.yield;
	}
	
	/**
	 * Variable registering the propulsion yield of this projectile.
	 */
	private final int yield;
	
	/**
	 * Variable registering the density of any projectile.
	 */
	private static final int density = 7800;
	
	/**
	 * Method to return the radius of the given projectile.
	 * @return The radius, calculated via the mass and the density of the projectile.
	 * 		| return == Math.pow((3.0/4)*getMass()/Projectile.density,1.0/3)
	 */
	public double getRadius(){
		return Math.pow((3.0/4)*getMass()/Projectile.density,1.0/3);
	}
	
	
	private double getInitialSpeed(){
		double force = 0;
		if (getMass() == 0.01)
			force = 1.5;
		if (getMass() == 0.3)
			force = 2.5 + 0.7*yield;
		double initialSpeed = (force/getMass())*0.5;
		return initialSpeed;
	}
	
	//TODO: Formal specification second @effect
	/**
	 * Method to make the given projectile jump.
	 * @param timeStep
	 * 		  An elementary time interval.
	 * @post If the direction is between zero and PI, then the given projectile will jump to the first location, adjacent to
	 * 		   impassable terrain or a worm, it encounters on its trajectory, which is calculated
	 * 			according to its mass, direction and propulsion yield. After the jump te projectile shall be terminated.
	 *         |new.terminated = true;
	 */
	public void jump(double timeStep) throws IllegalStateException {
		if (! isAlive())
			throw new IllegalStateException();
		double time = (0.1*this.getRadius())/getInitialSpeed();
		double[] tempCoordinates = jumpStep(time);
		double tempX = tempCoordinates[0];
		double tempY = tempCoordinates[1];
		while ((! this.getWorld().isAdjacent(tempX,tempY,getRadius())) && (! overlapsWithWorm(tempX,tempY)) 
				&& (! getWorld().isOutOfBounds(tempX,tempY,getRadius()) && this.getWorld().isPassable(tempX, tempY, this.getRadius()))){
			time += timeStep;
			tempCoordinates = jumpStep(time);
			tempX = tempCoordinates[0];
			tempY = tempCoordinates[1];
		}
		if (! getWorld().isOutOfBounds(tempX, tempY,getRadius())){
			setX(tempX);
			setY(tempY);
			hit(getOverlappingWorms());
		}
		terminate();
	}
	
	
	//TODO:Formal specification
	/**
	 * Method to calculate the time it takes to jump for the given projectile.
	 * @param timeStep
	 * 		  An elementary time interval.
	 * @return Returns the time it takes to jump.
	 *        | return == (distance/(initialSpeed*Math.cos(this.getDirection())))
	 */
	public double jumpTime(double timeStep) throws IllegalJumpDirectionException{
		double jumpTime = (0.1*this.getRadius())/getInitialSpeed();
		double[] tempCoordinates = jumpStep(jumpTime);
		double tempX = tempCoordinates[0];
		double tempY = tempCoordinates[1];
		while ((! this.getWorld().isAdjacent(tempX,tempY,getRadius())) && (! overlapsWithWorm(tempX,tempY)) 
				&& (! getWorld().isOutOfBounds(tempX,tempY,getRadius()) && this.getWorld().isPassable(tempX, tempY, getRadius()))){
			jumpTime += timeStep;
			tempCoordinates = jumpStep(jumpTime);
			tempX = tempCoordinates[0];
			tempY = tempCoordinates[1];
		}
		return jumpTime;
	}
	
	/**
	 * Calculate the position of the projectile, t seconds after he jumps.
	 * @param t
	 * 		  the time after which the position is calculated
	 * @return Returns the position of the given projectile t seconds after the jump.
	 * 			| return == {newX,newY}
	 * @throws IllegalTimeException
	 *        The time given is not valid for the given worm.
	 *        | (! this.canHaveAsTime(t))
	 */
	public double[] jumpStep(double t) throws IllegalAPException, IllegalJumpDirectionException, IllegalTimeException{
		if (! this.canHaveAsTime(t))
			throw new IllegalTimeException(t, this);
		double initialSpeed = this.getInitialSpeed();
		double theta = this.getDirection();
		double initialSpeedX = initialSpeed * Math.cos(theta);
		double initialSpeedY = initialSpeed * Math.sin(theta);
		double newX = this.getX() + (initialSpeedX * t);
		double newY = this.getY() + (initialSpeedY * t -0.5*g*Math.pow(t,2));
		double[] stepArray = {newX,newY};
		return stepArray;
	}
	
	/**
	 * Check whether the given time is a valid time for the method jumpStep.
	 * @param t
	 * 		The time to be checked
	 * @return True if and only if t is equal to or bigger than zero.
	 * 		| return == (t >= 0)
	 */
	public boolean canHaveAsTime(double t){
		return (t >= 0);
	}
	
	/**
	 * Method to return the worm of this projectile.
	 */
	@Basic
	public Worm getWorm(){
		return this.worm;
	}
	/**
	 * Method to set the worm of this projectile to the given worm.
	 * @param worm
	 * 		The worm to be set.
	 * @effect The new worm of this projectile is the given worm.
	 * 		| new.getWorm() = worm
	 */
	public void setWormTo(Worm worm){
		if (!(worm.getProjectile() == this))
			throw new IllegalStateException();
		this.worm = worm;
	}
	
	/**
	 * Variable registering the worm of this projectile.
	 */
	private Worm worm;
	
	/**
	 * Adds the given projectile to the given world.
	 * @param world
	 * 		The world in which the projectile should be added.
	 * @post This projectile is added to the world.
	 * 		| new.world.getActiveProjectile() == this
	 */
	public void addToWorld(World world){
		world.setActiveProjectile(this);
	}
	
	/**
	 * Removes this projectile from the given world.
	 * @param world
	 * 		The world from which this projectile has to be removed
	 * @post The new active projectile of the given world is null.
	 * 		| new.world.getActiveProjectile() == null
	 */
	public void removeFromWorld(World world){
		world.setActiveProjectile(null);
	}
	
	
	/**
	 * Method to return the world this projectile (indirectly) belongs to.
	 * @return The world of the worm of this projectile.
	 * 		| return == getWorm().getWorld()
	 */
	public World getWorld(){
		return getWorm().getWorld();
	}
	
	/**
	 * The gravitational constant.
	 */
	public static final double g = 9.80665;
	
	/**
	 * Method to check if the projectile currently overlaps with a worm.
	 * @return True if and only if the distance between this projectile and any worm is smaller than the sum of their radii.
	 */
	public boolean overlapsWithWorm(double x, double y){
		for (Worm worm: getWorld().getWorms()){
			if (getWorld().getDistance(x, y, worm.getX(), worm.getY()) < (getRadius() + worm.getRadius()) && (worm!=this.getWorm()))
				return true;
		}
		return false;
	}
	
	/**
	 * Method to return all the affected worms if a projectile is terminated.
	 * @return All the worms which are closer to the projectile than the sum of the projectiles radius and the worms radius.
	 */
	public List<Worm> getOverlappingWorms(){
		List<Worm> affectedWorms = new ArrayList<Worm>();
		for (Worm worm: getWorld().getWorms()){
			if (getWorld().getDistance(getX(), getY(), worm.getX(), worm.getY()) < (getRadius() + worm.getRadius()))
				affectedWorms.add(worm);
		}
		return affectedWorms;
	}
	
	//TODO: specification
	/**
	 * Method to hit the overlapping worms with the given projectile, if any.
	 * @param affectedWorms
	 * 
	 */
	public void hit(List<Worm> affectedWorms){
		int damage = 0;
		if (getMass() == 0.01)
			damage = 20;
		if (getMass() == 0.3)
			damage = 80;
		for (Worm worm: affectedWorms){
			int newHitpoints = worm.getHitPoints() - damage;
			if (newHitpoints >= 0)
				worm.setHitPoints(newHitpoints);
			else
				worm.setHitPoints(0);
		}
	}
	
	/**
	 * Method to terminate the given projectile.
	 */
	public void terminate(){
		removeFromWorld(getWorld());
		this.terminated = true;
	}
	
	/**
	 * Method to check if the current projectile is still alive.
	 * @return true if and only if terminated is false.
	 */
	public boolean isAlive(){
		return (! this.terminated);
	}
	/**
	 * method to see whether projectile is terminated
	 * @return
	 */
	public boolean isActive(){
		return (!this.terminated);
	}
	/**
	 * Variable registering whether or not the given projectile is terminated.
	 */
	private boolean terminated = false;

}
