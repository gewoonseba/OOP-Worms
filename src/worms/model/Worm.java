package worms.model;

import be.kuleuven.cs.som.annotate.*;


/**
 * A class of Worms. A Worm has a position (x,y), a direction (expressed as a radian value), 
 * a radius (in metres), a mass (calculated according to the radius) and a number of action points. 
 * A Worm can turn, move, jump and change its name.
 * 
 * @author Sebastian Stoelen 2BbiCwsElt2, Matthias Maeyens 2BbiCwsElt2
 * @version 2.0
 * 
 *
 */
public class Worm {
	
	/**
	 * Create a new worm.
	 * @pre The given initial direction must be a valid direction.
	 *		| isValidDirection(direction)
	 * @param x
	 * @param y
	 * @param direction
	 * @param radius
	 * @param name
	 * @effect the new x-coordinate is equal to x.
	 * 		| new.getX() == x
	 * @effect the new y-coordinate is equal to y.
	 * 		| new.getY() == y
	 * @effect the new direction is equal to direction.
	 * 		| new.getDirection() == direction
	 * @effect the new radius is equal to radius.
	 * 		| new.getRadius() == radius
	 * @effect the new name is equal to name.
	 * 		| new.getName() == name
	 * @effect the new currentAP is equal to the maxAP of the given worm.
	 *      | new.getCurrentAP() == new.getMaxAP()
	 * @effect the new hit points are equal to to maxHitpoints possible for the given worm.
	 *      | new.getHitPoints()== new.getMaxHitPoints()
	 * @throws IllegalRadiusException
	 *         The given radius is not valid.
	 *         | (! isValidRadius(radius))
	 * @throws IllegalNameException
	 *         The given name is not valid
	 *         | (! isValidName(name))
	 */
	@Raw
	public Worm(double x, double y, double direction, double radius, String name) 
			throws IllegalRadiusException, IllegalNameException{
		if (! isValidRadius(radius))
			throw new IllegalRadiusException(radius);
		if (! isValidName(name))
			throw new IllegalNameException(name);
		this.setX(x);
		this.setY(y);
		this.setDirection(direction);
		this.setRadius(radius);
		this.setName(name);
		this.setCurrentAP(this.getMaxAP());
		this.setHitPoints(this.getMaxHitPoints());
	}
	

	/**
	 * Method to return the x-coordinate of the given worm.
	 */
	@Basic
	public double getX(){
		return x;
	}
	
	/**
	 * Set the x-coordinate of the location of the given worm to a given value x.
	 * @param x
	 * @post the new x-coordinate is equal to x.
	 * 		| new.getX() == x
	 */
	@Raw
	public void setX(double x){
		this.x = x;
	}
	
	/**
	 * Variable registering the x-coordinate of the location of the given worm.
	 */
	private double x = 0;
	
	/**
	 * Method to return the y-coordinate of the given worm.
	 */
	@Basic
	public double getY(){
		return y;
	}
	
	/**
	 * Set the y-coordinate of the location of the given worm to a given value y.
	 * @param y
	 * @post the new y-coordinate is equal to y.
	 * 		| new.getY() == y
	 */
	@Raw
	public void setY(double y){
		this.y = y;
	}
	
	/**
	 * Variable registering the y-coordinate of the location of the given worm.
	 */
	private double y = 0;
	
	/**
	 * Method to check whether the given coordinate is not equal to NaN
	 * @param coordinate
	 * @return True if and only if coordinate does not equal Nan
	 * 			| result == (coordinate != Double.Nan)
	 */
	public boolean isValidCoordinate(double coordinate){
		return (coordinate != Double.NaN);
	}
	
	/**
	 * Check whether the worm can move the given number of steps.
	 * @param steps
	 * @return True if and only if the new AP of the worm is equal to or higher than zero.
	 *         | return == (newAP >= 0)
	 */
	public boolean canMove(int steps){
		double theta = this.getDirection();
		int newAP = (int) Math.round(this.getCurrentAP() - (steps*(Math.abs(Math.cos(theta)) + 4*(Math.abs(Math.sin(theta))))));
		return (newAP >= 0);
	}
	
	/**
	 * Method to move the given worm a given number of steps in it's current direction
	 * if his action points allow it
	 * @param steps
	 *        amount of steps the worm has to be moved
	 * @post the new position of the worm has to be equal to the old position plus the given number of steps in his 
	 *       current direction
	 *       
	 * @post the amount of action points the given worm has left has to be higher than or equal to zero
	 * @throws IllegalAPException
	 *         The current AP of the worm is not sufficient to move the number of steps provided.
	 *         | (! canMove(steps))
	 */
	public void move(int steps) throws IllegalAPException {
		if (! this.canMove(steps))
			throw new IllegalAPException(this.getCurrentAP(),this);
		double theta = this.getDirection();
		int newAP = (int) Math.round(this.getCurrentAP() - (steps*(Math.abs(Math.cos(theta)) + 4*(Math.abs(Math.sin(theta))))));
		double distance = this.getRadius()*steps;
		double xDistance = distance*Math.cos(theta);
		double yDistance = distance*Math.sin(theta);
		this.setX(this.getX() + xDistance);
		this.setY(this.getY() + yDistance);
		this.setCurrentAP(newAP);
	}
	
	/**
	 * Method to calculate and return the inital speed of the worm.
	 * @return The initial speed of the worm.
	 *        | return == initialSpeed
	 */
	private double getInitialSpeed(){
		double mass = this.getMass();
		double force = 5.0*this.getCurrentAP() + mass*g;
		double initialSpeed = (force/mass * 0.5);
		return initialSpeed;
	}
	
	/**
	 * Check whether the current worm has sufficient AP to jump.
	 * @return True if and only if the current AP of the given worm is greater than zero.
	 * 			| result == (this.getCurrentAP() > 0)
	 */
	public boolean canJumpAP(){
		return (this.getCurrentAP() > 0);
	}
	
	/**
	 * Check whether the direction of the given worm is valid to jump.
	 * @return True if and only if the current direction is equal to or in between zero and PI.
	 *    		| return == ((this.getDirection() >= 0) && (this.getDirection() <= Math.PI))
	 */
	public boolean canJumpDirection(){
		return  ((this.getDirection() >= 0) && (this.getDirection() <= Math.PI));
	}
	
	/**
	 * Method to make the given worm jump.
	 * @effect if the direction is between zero and PI, then the given worm will jump a calculated distance
	 *         based on his remaining action points and direction 
	 *         |new.getX() == distance + this.getX()
	 * @effect The new ap of the worm will be 0
	 *         |new.getCurrentAP==0
	 * @throws IllegalAPException
	 *        The worm has no AP left.
	 *        | this.getCurrentAP() <= 0
	 * @throws IllegalJumpDirectionException
	 * 		  The direction of the worm is not valid for jumping.
	 *        | (! this.canJumpDirection())
	 */
	public void jump() throws IllegalAPException, IllegalJumpDirectionException{
		if (! this.canJumpAP())
			throw new IllegalAPException(this.getCurrentAP(), this);
		if (! this.canJumpDirection())
			throw new IllegalJumpDirectionException(this.getDirection(),this);
		double initialSpeed = this.getInitialSpeed();
		double distance = (Math.pow(initialSpeed, 2)*Math.sin(2.0*this.getDirection())/g);
		this.setX(distance + this.getX());
		this.setCurrentAP(0);
	}
	
	/**
	 * Method to calculate the time it takes to jump for the given worm with the remaining action points.
	 * @return returns the time it takes to jump.
	 *        | return == (distance/(initialSpeed*Math.cos(this.getDirection())))
	 * @throws IllegalAPException
	 *        The worm has no AP left.
	 *        | this.getCurrentAP() <= 0
	 * @throws IllegalJumpDirectionException
	 * 		  The direction of the worm is not valid for jumping.
	 *        | (! this.canJumpDirection())
	 */
	public double jumpTime() throws IllegalAPException, IllegalJumpDirectionException{
		if (! this.canJumpAP())
			throw new IllegalAPException(this.getCurrentAP(), this);
		if (! this.canJumpDirection())
			throw new IllegalJumpDirectionException(this.getDirection(),this);
		double Jumptime = 0;
		double initialSpeed = this.getInitialSpeed();
		double initialSpeedY = initialSpeed * Math.sin(this.getDirection());
		if (initialSpeedY != 0)
			Jumptime = (2*initialSpeedY)/g;
		return Jumptime;
	}
	
	/**
	 * Calculate the position of the worm, t seconds after he jumps.
	 * @param t
	 * 		  the time after which te position is calculated
	 * @return Returns the position of the given worm t seconds after the jump.
	 * 			| return == {newX,newY}
	 * @throws IllegalAPException
	 *        The worm has no AP left.
	 *        | this.getCurrentAP() <= 0
	 * @throws IllegalJumpDirectionException
	 * 		  The direction of the worm is not valid for jumping.
	 *        | (! this.canJumpDirection())
	 * @throws IllegalTimeException
	 *        The time given is not valid for the given worm.
	 *        | (! this.canHaveAsTime(t))
	 */
	public double[] jumpStep(double t) throws IllegalAPException, IllegalJumpDirectionException, IllegalTimeException{
		if (! this.canJumpAP())
			throw new IllegalAPException(this.getCurrentAP(), this);
		if (! this.canJumpDirection())
			throw new IllegalJumpDirectionException(this.getDirection(),this);
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
	 * Check whether the given method jumpstep for a given worm van have the given time as input
	 * @param t
	 * @return True if and only if the time t is higher or equal zero 
	 *         and lower or equal time the maximum time for a jump for the given worm.
	 *         |return == (t>=0)&&(t=<this.jumpTime)
	 */
	public boolean canHaveAsTime(double t){
		if ((t<0)||(t>this.jumpTime()))
			return false;
		return true;
		}
	
	/**
	 * The gravitational constant.
	 */
	public static final double g = 9.80665;
	
	
	/**
	 * Method to return the direction of the given worm.
	 */
	@Basic
	public double getDirection(){
		return direction;
	}
	/**
	 * Check whether the given direction is a valid direction for a worm.
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
	 * Check whether the given worm can turn over the given angle.
	 * @param angle
	 *        The angle that has to be checked
	 * @return True if and only if the absolute value of the given angle is 
	 *         less than 2 times PI and the new action points of the given worm are higher than or equal to zero.
	 *         | return == abs(angle) < 2*Math.PI)) && 
	 *		   | (Math.round(this.getCurrentAP() - abs(angle)/(2*Math.PI) * 60) >= 0)
	 *         
	 */
	@Raw
	public boolean canTurn(double angle){
		return ((Math.abs(angle) < 2*Math.PI)) && 
				 (Math.round(this.getCurrentAP() - Math.abs(angle)/(2.0*Math.PI) * 60.0) >= 0);
	}
	
	/**
	 * @pre angle must be a valid angle
	 *      |isValidAngle(angle) == true
	 * @param angle
	 * @post If the sum of the current direction and the angle is smaller than 2*PI, then the new direction
	 *       is equal to this sum.
	 *       | new.getDirection() == this.getDirection() + angle
	 * @post If the sum of the current direction and the angle is greater than 2*PI, then the new direction
	 *       is equal to this sum minus 2*Pi .
	 *       | new.getDirection() == this.getDirection() + angle - 2*Math.PI
	 * @post If the sum of the current direction and the angle is less than 2*PI, then the new direction
	 *       is equal to this sum plus 2*PI
	 * @post the current action point of the given worm must be higher than or equal to zero
	 *       |new.getCurrentAP() >= 0 
	 */
	public void turn(double angle){
		assert this.canTurn(angle);
		double newDirection = this.getDirection() + angle;
		int newAP = (int) Math.round(this.getCurrentAP() - Math.abs(angle)/(2.0*Math.PI) * 60.0);
		if (newDirection >= 2*Math.PI)
			newDirection = newDirection - 2*Math.PI;
		if (newDirection < 0)
			newDirection = newDirection + 2*Math.PI;
		setDirection(newDirection);	
		setCurrentAP(newAP);
	}
	
	/**
	 * Set the direction of the given worm to the given value of direction.
	 * @pre 	direction must be a valid direction.
	 * 			| isValidDirection(direction)
	 * @param direction
	 *        The new direction of the given worm.
	 * @post the new direction is equal to direction.
	 * 		| new.getDirection() == direction
	 */
	@Raw
	public void setDirection(double direction){
		assert isValidDirection(direction);
		this.direction = direction;
	}
	
	/**
	 * The direction of the given worm.
	 */
	private double direction = 0;
	
	/**
	 * Method to return the minimal radius of any worm.
	 */
	public static double getMinimalRadius(){
		return minimalRadius;
	}
	
	/**
	 * The minimal radius any worm must have.
	 */
	private static double minimalRadius = 0.25;
	
	/**
	 * Method to return the radius of the given worm.
	 */
	@Basic
	public double getRadius(){
		return radius;
	}
	
	/**
	 * Check whether the given radius is valid.
	 * @param radius
	 * @return 	True if and only if the radius is greater than 0.25
	 * 			| radius >= 0.25
	 */
	@Raw
	public static boolean isValidRadius(double radius){
		return radius >= 0.25;
	}
	
	/**
	 * Set the radius of the given worm to the given value of radius.
	 * @param radius
	 *        The new radius of the given worm.
	 * @post the new radius is equal to radius.
	 * 		| new.getRadius() == radius
	 * @post the hit points and current AP of the given worm do not exceed their respective maximum.
	 *      |if (this.getHitPoints()>this.getMaxHitPoints())
	 *		| this.setHitPoints(this.getMaxHitPoints());
	 *      |if (this.getCurrentAP()>this.getMaxAP())
	 *		|this.setCurrentAP(this.getMaxAP());
	 * @throws IllegalRadiusException
	 * 		   The radius is smaller than the minimal radius
	 * 		   | radius < getMinimalRadius()
	 */
	@Raw
	public void setRadius(double radius) throws IllegalRadiusException {
		if (radius < getMinimalRadius())
			throw new IllegalRadiusException(radius);
		this.radius = radius;
		if (this.getCurrentAP()>this.getMaxAP())
			this.setCurrentAP(this.getMaxAP());
		if (this.getHitPoints()>this.getMaxHitPoints())
			this.setHitPoints(this.getMaxHitPoints());
		
	}
	
	/**
	 * The radius of the given worm
	 */
	private double radius;
	
	/**
	 * Method to return the name of the given worm.
	 */
	@Basic
	public String getName(){
		return name;
	}
	
	/**
	 * Check whether the given name is valid.
	 * @param name
	 * @return 	True if and only if the name contains only valid characters
	 */
	@Raw
	public static boolean isValidName(String name){
		char[] chars = name.toCharArray();
		if (!(Character.isUpperCase(name.charAt(0))))
			return false;
		if (! (name.length() >= 2))
			return false;
		for (char c : chars){
			if (!(Character.isLetterOrDigit(c) || Character.isWhitespace(c) || c =='\'' || c=='"' ))
				return false;
		}
		return true;
	}
	
	/**
	 * Set the name of the given worm to the given name.
	 * @param name
	 *        The new name of the given worm.
	 * @post the new name is equal to name.
	 * 		| new.getName() == name
	 * @throws IllegalNameException
	 * 		  The given name is not is not a valid name
	 * 		  | (! isValidName(name))
	 */
	@Raw
	public void setName(String name) throws IllegalNameException {
		if (! isValidName(name))
			throw new IllegalNameException(name);
		this.name = name;
	}
	
	/**
	 * The name of the given worm.
	 */
	private String name;
	
	public static final double density = 1062;
	
	/**
	 * Method to return the mass of the given worm.
	 * @return Returns the mass of the given worm.
	 * 			| return ==  density*(4/3)*Math.PI*(Math.pow(this.getRadius(),3))
	 */
	public double getMass(){
		double mass = density*(4.0/3.0)*Math.PI*(Math.pow(this.getRadius(),3));
		return mass;			
	}
	
	/**
	 * Method to return the maximum Action Points of the given worm.
	 * @return Returns the maximum Action Points of the given worm.
	 * 			| return == (int) Math.round(this.getMass())
	 */
	public int getMaxAP(){
		int maxAP;
		long possibleMaxAP = Math.round(this.getMass());
		if (possibleMaxAP >= Integer.MAX_VALUE)
			maxAP = Integer.MAX_VALUE;
		else
			maxAP = (int) possibleMaxAP;
		return maxAP;
	}
	
	/**
	 * Method to return the current Action Points of the given worm.
	 */
	public int getCurrentAP(){
		return currentAP;
	}
	
	/**
	 * Check whether the currentAP is valid for the given worm.
	 * @param currentAP
	 * @return True if and only if the current AP is greater than null and less than the max AP for the given worm.
	 *         | return == (! (currentAP < 0) || (currentAP > this.getMaxAP()))
	 */
	public boolean isValidCurrentAP(int currentAP){
		return (! ((currentAP < 0) || (currentAP > this.getMaxAP())));
	}
	
	/**
	 * Set the current Action Points of the given worm to a given value currentAP.
	 * @param currentAP
	 * @post the new current Action Points is equal to currentAP.
	 * 		| new.getCurrentAP() == currentAP
	 * @throws IllegalAPException
	 * 		  The AP provided is not valid for the given worm.
	 *        | (! isValidCurrentAP(currentAP))
	 */
	protected void setCurrentAP(int currentAP) throws IllegalAPException {
		if (! isValidCurrentAP(currentAP))
			throw new IllegalAPException(currentAP,this);
		this.currentAP = currentAP;
	}
	
	/**
	 * The current Action Points of the given worm.
	 */
	private int currentAP;
	
	/**
	 * Return the world of this worm.
	 */
	@Basic @Raw
	public World getWorld(){
		return this.world;
	}
	
	/**
	 * Method to check whether this worm can have the given world as its world.
	 * @param world
	 * @return True if and only if the world is not null.
	 * 			| return == (world != null)
	 */
	public boolean canHaveAsWorld(World world){
		return (world != null);
	}
	
	/**
	 * Method to check whether the given worm already has a world.
	 * @return True if and only if this.world is not null.
	 * 		| return == (getWorld() != null)
	 */
	public boolean hasWorld(){
		return (getWorld() != null);
	}
	
	/**
	 * Method to return the maximum Hit Points of the given worm.
	 * @return Returns the maximum Hit Points of the given worm.
	 * 			| return == (int) Math.round(this.getMass())
	 */
	public int getMaxHitPoints(){
		int maxHitPoints;
		long possibleMaxHitPoints = Math.round(this.getMass());
		if (possibleMaxHitPoints >= Integer.MAX_VALUE)
			maxHitPoints = Integer.MAX_VALUE;
		else
			maxHitPoints = (int) possibleMaxHitPoints;
		return maxHitPoints;
		
	}
	/**
	 * Method that returns the hitpoints of a given worm.
	 * @return returns the hit points of the given worm
	 *         |return == this.hitPoints
	 */
	public int getHitPoints(){
		return this.hitPoints;
	}
	
	/**
	 * Set the current Hit Points of the world to the given value hitPoints
	 * @param hitPoints
	 * @post the new hit points are equal to hitPoints.
	 *      |new.getHitPoints() == hitPoints
	 */
	@Basic
	public void setHitPoints(int hitPoints){
		this.hitPoints=hitPoints;
	}
	
	/**
	 * Checks whether the given worm is alive
	 * @return returns true if and only if the hit points of the worm are higher than zero.
	 *        |return == this.getHitPoints()>0
	 */
	public boolean isAlive(){
		return (this.getHitPoints()>0);
	}	
	/**
	 * the current hitPoints of the given Worm.
	 */
	private int hitPoints;
	
	/**
	 * Method to set the World of this worm to the given world.
	 * @param world
	 * @post If the world is valid, the new World of this worm is the given world.
	 * 		| new.getWorld() == world
	 */
	public void setWorldTo(World world){
		if ((world == null) || (! canHaveAsWorld(world)))
			throw new IllegalWorldException(world);
		if (hasWorld())
			throw new IllegalStateException();
	}
	
	/**
	 * Variable registering the world of this worm.
	 */
	private World world;
}
