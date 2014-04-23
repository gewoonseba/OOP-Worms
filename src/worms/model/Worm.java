package worms.model;

import java.util.*;

import be.kuleuven.cs.som.annotate.*;


/**
 * A class of Worms. A Worm has a position (x,y), a direction (expressed as a radian value), 
 * a radius (in metres), a mass (calculated according to the radius) and a number of action points. 
 * A Worm can turn, move, jump and change its name. A worm shall belong to one world and one world only. A worm can belong
 * to a Team, but it doesn't have to belong to a team. Furthermore a Worm shall possess weapons and is able to shoot these weapons.
 * 
 * @author Sebastian Stoelen 2BbiCwsElt2, Matthias Maeyens 2BbiCwsElt2
 * @version 2.0
 * 
 *
 */
public class Worm {
	
	/**
	 * Create a new worm.
	 * @Pre The given initial direction must be a valid direction.
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
	 * @post the worm has "Rifle" and "Bazooka" as its only weapons.
	 * 		| new.weapons = {"Rifle","Bazooka"}
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
		this.setX(x);
		this.setY(y);
		this.setDirection(direction);
		this.setRadius(radius);
		this.setName(name);
		this.setCurrentAP(this.getMaxAP());
		this.setHitPoints(this.getMaxHitPoints());
		this.addAsWeapon("Rifle");
		this.addAsWeapon("Bazooka");
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
	
	//TODO: Formal specification
	/**
	 * Check whether the worm can move one step.
	 * @return True if and only if searchFitLocation can find a fit location in the worms current direction.
	 * 		| 
	 */
	public boolean canMove(){
		double currentDistance = getRadius();
		double[] newLocation = null;
		while (newLocation == null && currentDistance >= 0.1){
			newLocation = searchFitLocation(currentDistance);
			currentDistance -= 0.01;
		}
		if (newLocation == null)
			return false;
		return true;
	}
	
	/**
	 * Method to search a location which is adjacent to impassable terrain on a quarter circle, a given distance from the
	 * center of the Worm. The method shall search for fit locations, beginning in the current  direction of the Worm. When
	 * none is found, the method shall alternately add or remove 0.0175 radians from the optimal angle.
	 * @param distance
	 * 		The at which the method will search for adjacent locations.
	 * @return The coordinate which is adjacent to impassable terrain and closest to the optimal angle, if one is found. 
	 * 		   If none is found, the method will return null.
	 */
	public double[] searchFitLocation(double distance) {
		double thetaUp = this.getDirection();
		double thetaDown = this.getDirection();
		double tempX = this.getX() + distance*Math.cos(this.getDirection());
		double tempY = this.getY() + distance*Math.sin(this.getDirection());
		while ((Math.abs(thetaUp-getDirection()) < 0.7875) && (! this.getWorld().isAdjacent(tempX,tempY,this.getRadius()))){
			thetaUp += 0.0175;
			tempX = this.getX() + distance*Math.cos(thetaUp);
		    tempY = this.getY() + distance*Math.sin(thetaUp);
	        if(!(this.getWorld().isAdjacent(tempX, tempY,getRadius()))){	
				thetaDown -= 0.0175;
			    tempX = this.getX() + distance*Math.cos(thetaDown);
		        tempY = this.getY() + distance*Math.sin(thetaDown); 
	        }
	    }
		if (this.getWorld().isAdjacent(tempX,tempY,getRadius())){

			return new double[] {tempX,tempY};	
		}
		return null;
	}
	
	//TODO:implementation using slope + currentDistance - 0.01?
	/**
	 * Method to move the worm one step, in its current direction.
	 */
	public void move() throws IllegalAPException{
		double currentDistance = getRadius();
		double[] newLocation = null;
		while (newLocation == null && currentDistance>=0.1){
			newLocation = searchFitLocation(currentDistance);
			currentDistance -= 0.01;
		}
		if (!(newLocation == null)){
			double newX = newLocation[0];
			double newY = newLocation[1];
			double slope = Math.atan((getY() - newY)/(getX() - newX));
			int newAP = (int) Math.round(getCurrentAP() - (Math.abs(Math.cos(slope)) + Math.abs(4*Math.sin(slope))));
			if (newAP >= 0){
				setX(newX);
				setY(newY);
				setCurrentAP(newAP);
			}
			else
				throw new IllegalAPException(getCurrentAP(),this);
		}
		if (! getWorld().isAdjacent(getX(),getY(),getRadius()))
				fall();
		eatFood();
	}
	
	//TODO: specification 'if'
	/**
	 * Method to make the given worm fall until he hits impassable terrain, or is no longer within the boundries of its world.
	 * The worm shall lose hitpoints, according to the distance he has fallen.
	 * @effect If there is impassable terrain under the worm, the worm shall move to that location, without changing its x coordinate
	 * 		and only by lowering its y coordinate.
	 * 		| isAdjacent(new.getY(),new.getX(),new.getRadius())
	 * 		| new.getY() < this.getY()
	 * @effect If there is impassable terrain under the worm, the worm shall lose hitPoints. The worm shall lose three times the
	 * 		amount of meters it has fallen (rounded down) worth of hitPoints.
	 * 		| new.getHitPoints() == this.getHitPoints() - 3*distance
	 * @effect If the new hitPoints are less than zero, the hitpoints will be set to zero. The worm will thus leave
	 * 		the world he belonged to.
	 * 		| if(this.getHitPoints() - 3*distance < 0)
	 * 		|	new.getHitPoints() == 0
	 * @effect If there is no impassable terrain under the worm, its hitPoints will be set to zero. The worm will thus leave the world
	 * 		he belonged to.
	 * 		| setHitPoints(0)
	 */
	public void fall(){
		double oldY = getY();
		while (! getWorld().isAdjacent(getX(),getY(),getRadius()) && ! getWorld().isOutOfBounds(getX(), getY(),getRadius()))
			fallPixel();
		if (! getWorld().isOutOfBounds(getX(), getY(),getRadius())){
			int distance = (int) (oldY - getY());
			int newHitPoints = getHitPoints() - 3*distance;
			if (newHitPoints >= 0)
				setHitPoints(newHitPoints);
		}
		else
			setHitPoints(0);
	}
	
	/**
	 * Method to make the given worm fall down the distance of one pixel.
	 * @effect The new y coordinate will be its old y coordinate, minus the distance of one pixel.
	 * 		| new.getY() = this.getY() - getWorld.getHeightScale();
	 */
	public void fallPixel(){
		double distance = getWorld().getHeightScale();
		setY(getY() - distance);
	}
	
	/**
	 * Method to calculate and return the initial speed of the worm.
	 * @return The initial speed of the worm.
	 *        | return == (5.0*this.getCurrentAP() + mass*g)/getMass()*0.5
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
		return (this.getCurrentAP()>0);
	}
	
	/**
	 * Check whether the direction of the given worm is valid to jump.
	 * @return True if and only if the current direction is equal to or in between zero and PI.
	 *    		| return == ((this.getDirection() >= 0) && (this.getDirection() <= Math.PI))
	 */
	public boolean canJumpDirection(){
		return  ((this.getDirection() >= 0) && (this.getDirection() <= Math.PI));
	}
	
	//TODO: Formal specification second @effect
	/**
	 * Method to make the given worm jump.
	 * @param timeStep
	 * 		  An elementary time interval.
	 * @effect If the distance between the current position of the worm and the new position is less than its radius, 
	 * 		   the worm shall not move.
	 * 		   | if (getWorld().getDistance(getX(),getY(),tempX,tempY) > getRadius())
	 * 		   |	new.getX() == this.getX()
	 * 		   |	new.getY() == this.getY()
	 * @effect If the direction is between zero and PI, then the given worm will jump to the first location, adjacent to
	 * 		   impassable terrain, it encounters on its trajectory, which is calculated according to its direction and current AP.
	 *         |new.getX() == distance + this.getX()
	 * @effect The new AP of the worm will be 0
	 *         |new.getCurrentAP() == 0
	 * @throws IllegalAPException
	 *        The worm has no AP left.
	 *        | this.getCurrentAP() <= 0
	 */
	public void jump(double timeStep) throws IllegalAPException{
		if (! this.canJumpAP())
			throw new IllegalAPException(this.getCurrentAP(), this);
		double time = timeStep;
		double[] tempCoordinates = jumpStep(time);
		double tempX = tempCoordinates[0];
		double tempY = tempCoordinates[1];
		while ((! this.getWorld().isAdjacent(tempX,tempY,getRadius())) && (! getWorld().isOutOfBounds(tempX,tempY,getRadius()) && this.getWorld().isPassable(tempX, tempY, getRadius()))){
			time += timeStep;
			tempCoordinates = jumpStep(time);
			tempX = tempCoordinates[0];
			tempY = tempCoordinates[1];
		}
		if (getWorld().isOutOfBounds(tempX, tempY,getRadius()))
			terminate();
		else {
			if (getWorld().getDistance(getX(),getY(),tempX,tempY) > getRadius()){
				setX(tempX);
				setY(tempY);
			}
			setCurrentAP(0);	
		}
	}
	
	//TODO:Formal specification
	/**
	 * Method to calculate the time it takes to jump for the given worm with the remaining action points.
	 * @param timeStep
	 * 		  An elementary time interval.
	 * @return Returns the time it takes to jump.
	 *        | return == (distance/(initialSpeed*Math.cos(this.getDirection())))
	 * @throws IllegalAPException
	 *        The worm has no AP left.
	 *        | this.getCurrentAP() <= 0
	 */
	public double jumpTime(double timeStep) throws IllegalAPException{
		if (! this.canJumpAP())
			return 0;
			
		double jumpTime = (0.1*this.getRadius())/getInitialSpeed();
		double[] tempCoordinates = jumpStep(jumpTime);
		double tempX = tempCoordinates[0];
		double tempY = tempCoordinates[1];
		
		while ((! this.getWorld().isAdjacent(tempX,tempY,getRadius())) && (! getWorld().isOutOfBounds(tempX,tempY,getRadius()) && this.getWorld().isPassable(tempX, tempY, getRadius()))){
			jumpTime += timeStep;
			tempCoordinates = jumpStep(jumpTime);
			tempX = tempCoordinates[0];
			tempY = tempCoordinates[1];
		}
		if (getWorld().getDistance(getX(),getY(),tempX,tempY) < getRadius()){
			jumpTime = 0;}
		return jumpTime;
	}
	
	/**
	 * Calculate the position of the worm, t seconds after he jumps.
	 * @param t
	 * 		  the time after which the position is calculated
	 * @return Returns the position of the given worm t seconds after the jump.
	 * 			| return == {newX,newY}
	 * @throws IllegalAPException
	 *        The worm has no AP left.
	 *        | this.getCurrentAP() <= 0
	 * @throws IllegalTimeException
	 *        The time given is not valid for the given worm.
	 *        | (! this.canHaveAsTime(t))
	 */
	public double[] jumpStep(double t) throws IllegalAPException, IllegalTimeException{
		//if (! this.canJumpAP())
			//throw new IllegalAPException(this.getCurrentAP(), this);
		if (! this.canHaveAsTime(t))
			throw new IllegalTimeException(t, this);
		double initialSpeed = this.getInitialSpeed();
		double theta = this.getDirection();
		double initialSpeedX = initialSpeed * Math.cos(theta);
		double initialSpeedY = initialSpeed * Math.sin(theta);
		double newX = this.getX() + (initialSpeedX * t);
		double newY = this.getY() + (initialSpeedY * t -0.5*g*Math.pow(t,2));
		double[] stepArray = {newX,newY};
		//this.eatFood();
		return stepArray;
	}
	
	/**
	 * Check whether the method jumpStep() for a given worm can have the given time as input
	 * @param t
	 * @return True if and only if the time t is higher or equal zero 
	 *         |return == (t>=0)
	 */
	public boolean canHaveAsTime(double t){
		return (t >= 0);
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
	 * Method to calculate the new AP after shooting the current Weapon.
	 * @return If the current weapon is a rifle, the new AP is the current AP minus 10
	 * 		|if (this.weapons.get(getCurrentWeaponIndex()) ==  "Rifle")
	 *		|	return == this.getCurrentAP() - 10
	 * @return If the current weapon is a bazooka, the new AP is the current AP minus 50
	 * 		| if (this.weapons.get(getCurrentWeaponIndex()) == "Bazooka")
	 * 		|	return == this.getCurrentAP() - 50
	 */
	public int getShootAP(){
		int newAP = getCurrentAP();
		if (this.weapons.get(getCurrentWeaponIndex()) ==  "Rifle")
			newAP -= 10;
		if (this.weapons.get(getCurrentWeaponIndex()) == "Bazooka")
			newAP -= 50;
		return newAP;	
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
	 * Method to set the World of this worm to the given world.
	 * @param world
	 * 		The world that should be set as the world of this worm.
	 * @post If the world is valid, the new World of this worm is the given world.
	 * 		| new.getWorld() == world
	 * @throws IllegalWorldException
	 * 		This worm cannot have the given world as its world.
	 * 		| ! canHaveAsWorld(world)
	 * @throws IllegalStateException
	 * 		This worm already has a world.
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
	 * Return the world of this worm.
	 */
	@Basic @Raw
	public World getWorld(){
		return this.world;
	}
	
	/**
	 * Method to check whether this worm can have the given world as its world.
	 * @param world
	 * 		The world to be checked.
	 * @return True if and only if the world is not null and the given world does not already contains this worm.
	 * 			| return == (world != null) && (! world.hasAsWorm(this))
	 */
	public boolean canHaveAsWorld(World world){
		return (world != null) && (! world.hasAsWorm(this));
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
	 * Method to remove the world of this worm and remove this worm from the world it belonged to.
	 * @throws NullPointerException
	 * 		This worm has no world.
	 * 		| ! hasWorld()
	 */
	public void removeWorld() throws NullPointerException {
		if (! hasWorld())
			throw new NullPointerException();
		World formerWorld = getWorld();
		this.world = null;
		formerWorld.removeAsWorm(this);
	}
	
	/**
	 * Variable registering the world of this worm.
	 */
	private World world;
	
	/**
	 * Method to set the World of this worm to the given team.
	 * @param team
	 * 		The team that should be set as the team of this worm.
	 * @post If the team is valid, the new World of this worm is the given team.
	 * 		| new.getTeam() == team
	 * @throws IllegalTeamException
	 * 		This worm cannot have the given team as its team.
	 * 		| ! canHaveAsTeam(team)
	 * @throws IllegalStateException
	 * 		This worm already has a team.
	 * 		| hasTeam()
	 */
	public void setTeamTo(Team team) throws IllegalTeamException, IllegalStateException{
		if (! canHaveAsTeam(team))
			throw new IllegalTeamException(team);
		if (hasTeam())
			throw new IllegalStateException();
		this.team = team;
	}
	
	/**
	 * Return the team of this worm.
	 */
	@Basic @Raw
	public Team getTeam(){
		return this.team;
	}
	
	/**
	 * Return the name of the team this worm belongs to returns null if this worms has no team.
	 * @return Return the name of the team of the given worm or null.
	 */
	public String getTeamName(){
		if (!hasTeam())
			return null;
		return this.getTeam().getName();
	}
	
	/**
	 * Method to check whether this worm can have the given team as its team.
	 * @param team
	 * 		The team to be checked.
	 * @return True if and only if the team is not null and the given team does not already contains this worm.
	 * 			| return == (team != null) && (! team.hasAsWorm(this))
	 */
	public boolean canHaveAsTeam(Team team){
		return (team != null) && (! team.hasAsWorm(this));
	}
	
	/**
	 * Method to check whether the given worm already has a team.
	 * @return True if and only if this.team is not null.
	 * 		| return == (getTeam() != null)
	 */
	public boolean hasTeam(){
		return (getTeam() != null);
	}
	
	/**
	 * Method to remove the team of this worm and remove this worm from the team it belonged to.
	 * @throws NullPointerException
	 * 		This worm has no team.
	 * 		| ! hasTeam()
	 */
	public void removeTeam() throws NullPointerException {
		if (! hasTeam())
			throw new NullPointerException();
		Team formerTeam = getTeam();
		this.team = null;
		formerTeam.removeAsWorm(this);
	}
	
	/**
	 * Variable registering the team of this worm.
	 */
	private Team team;
	
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
	 * Set the current Hit Points of the worm to the given value hitPoints
	 * @param hitPoints
	 * @post the new hit points are equal to hitPoints.
	 *      |new.getHitPoints() == hitPoints
	 * @effect if hitPoints = zero, the method shall check if there is a winnar and the worm shall leave the world it belonged to
	 * 		and its current world will be set to null. 
	 * 		| ! new.getWorld().hasAsWorm(this)
	 * 		| new.getWorld() == null
	 */
	@Basic
	public void setHitPoints(int hitPoints){
		if (! isValidHitPoints(hitPoints))
			throw new IllegalArgumentException();
		this.hitPoints = hitPoints;
		if (hitPoints == 0) {
			getWorld().getWinner();
			removeWorld();
		}

	}
	
	/**
	 * Method to check if the given hitPoints is a valid number of hitPoints
	 * @param hitPoints
	 * 		The hitPoints to be checked.
	 * @return True if and only if hitPoints is greater than or equal to zero and less than or equal to the maximum hitPoints.
	 * 		| return == ((hitPoints >= 0) && (hitPoints <= getMaxHitPoints()))
	 */
	public boolean isValidHitPoints(int hitPoints){
		return ((hitPoints >= 0) && (hitPoints <= getMaxHitPoints()));
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
	 * Method to terminate this worm, setting its terminated value to true.
	 */
	public void terminate(){
		terminated = true;
	}
	
	/**
	 * Variable registering whether or not this worm is terminated.
	 */
	private boolean terminated = false;
	
	/**
	 * Method to check if the given weapon is a valid weapon.
	 * @param weapon
	 * 		The weapon to be checked.
	 * @return True if and only if the given weapon is "Rifle" or "Bazooka"
	 * 		| retrun == ((weapon == "Rifle") || (weapon == "Bazooka"))
	 */
	public boolean isValidWeapon(String weapon){
		return ((weapon == "Rifle") || (weapon == "Bazooka"));
	}
	
	/**
	 * Method to check whether the given propulsion yield is valid.
	 * @param p
	 * 		The propulsion yield to be checked.
	 * @return True if and only if p belongs to the interval [0,100]
	 * 		| return == ((p >= 0) && (p <= 100))
	 */
	public boolean isValidPropulsionYield(int yield){
		return ((yield >= 0) && (yield <= 100));
	}

	/**
	 * Method to return the projectile this worm is currently shooting, if any.
	 */
	@Basic
	public Projectile getProjectile(){
		return this.projectile;
	}

	/**
	 * Method to shoot the current weapon with the given propulsion yield.
	 * @param p
	 * 		The given propulsion yield.
	 * @effect The current AP of the worm shall be reduced with the appropriate amount.
	 * 		| new.getCurrentAP() == this.getShootAP()
	 * @throws IllegalArgumentException
	 * 		The given propulsion yield is not valid.
	 * 		| ! isValidPropulsionYield(yield)
	 * @throws IllegalAPException
	 * 		The worm doesn't have enough AP left to shoot its current weapon.
	 * 		| getShootAP() < 0
	 */
	public void shoot(int yield) throws IllegalArgumentException, IllegalAPException{
		if (! isValidPropulsionYield(yield))
			throw new IllegalArgumentException();
		int newAP = getShootAP();
		if (newAP < 0)
			throw new IllegalAPException(getCurrentAP(), this);
		double[] location = getProjectileLocation();
		double direction = this.getDirection();
		double mass = getProjectileMass();
		Projectile projectile = new Projectile(location[0],location[1],direction,mass,yield);
		this.projectile = projectile;
		projectile.setWormTo(this);
		setCurrentAP(newAP);
	}
	
	/**
	 * Method to return the location a new projectile should be initialized.
	 * @return The coordinates at the circumference of the worm, following the current direction of the worm.
	 * 		| return = new double[] {(getX() + distance*Math.cos(direction)),(getY() + distance*Math.sin(direction))}
	 */
	public double[] getProjectileLocation(){
		double direction = getDirection();
		double distance = getRadius();
		double newX = (getX() + distance*Math.cos(direction));
		double newY = (getY() + distance*Math.sin(direction));
		return new double[] {newX,newY};
	}
	
	/**
	 * Method to return the mass a new projectile of the current weapon.
	 * @return 0.01 if the weapon is a rifle
	 * 		|if (this.weapons.get(getCurrentWeaponIndex()) == "Rifle")
	 *		|	return = 0.01;
	 * @return 0.3 if the weapon is a bazooka
	 * 		|if (this.weapons.get(getCurrentWeaponIndex()) == "Bazooka")
	 *		|	return = 0.3;
	 */
	public double getProjectileMass(){
		double mass = 0;
		if (this.weapons.get(getCurrentWeaponIndex()) == "Rifle")
			mass = 0.01;
		if (this.weapons.get(getCurrentWeaponIndex()) == "Bazooka")
			mass = 0.3;
		return mass;
	}
	
	/**
	 * Variable registering the projectile this worm is currently shooting, if any.
	 */
	private Projectile projectile;
	
	/**
	 * @Pre The weapon to be added must be a valid weapon.
	 * 		| isValidWeapon(weapon)
	 * Method to add a given weapon to the weapons of this worm.
	 * @param weapon
	 * 		The weapon to be added.
	 * @throws IllegalArgumentException
	 * 		The given weapon is not a valid weapon.
	 * 		| ! isValidWeapon(weapon)
	 */
	public void addAsWeapon(String weapon) throws IllegalArgumentException{
		if  (! isValidWeapon(weapon))
			throw new IllegalArgumentException();
		weapons.add(weapon);
	}

	/**
	 * Variable containing a list of the weapons this worm currently has.
	 */
	private final List<String> weapons = new ArrayList<String>();
	
	/**
	 * Method to select the next weapon of this worm.
	 * @post the currentWeaponIndex will be increased by 1
	 * 		| new.currentWeaponIndex = this.currentWeaponIndex + 1
	 * @post if the new currentWeaponIndex is equal to the size of weapons, the new currentWeaponIndex
	 * 		will be set to zero.
	 * 		| if (new.currentWeaponIndex() == weapons.size())
	 * 		|	new.currentWeaponIndex = 0
	 */
	public void selectNextWeapon(){
		currentWeaponIndex += 1;
		if (currentWeaponIndex == weapons.size())
			currentWeaponIndex = 0;
	}
	
	public String getCurrentWeapon(){
		if (getCurrentWeaponIndex() == 0)
			return "Rifle";
		if (getCurrentWeaponIndex() == 1)
			return "Bazooka";
		return null;
	}
	
	/**
	 * Method to get the index of the current weapon of the worm.
	 */
	@Basic
	public int getCurrentWeaponIndex(){
		return this.currentWeaponIndex;
	}
	/**
	 * Variable registering the index of the current weapon of this worm.
	 */
	private int currentWeaponIndex = 0;
	
	
	/**
	 * Method for a worm to see if he's in range of food and to potentially eat it.
	 */
	public void eatFood(){
		for (int i=0;i<this.getWorld().getFood().size()-1; i++){
			double foodX = this.getWorld().getFood().get(i).getX();
			double foodY = this.getWorld().getFood().get(i).getY();
			double wormX = this.getX();
			double wormY = this.getY();
			if (Math.sqrt(Math.pow((foodX - wormX), 2) + Math.pow((foodY -wormY), 2))<(this.getRadius()+0.20)){
				this.setRadius(1.1*(this.getRadius()));
				this.getWorld().removeAsFood(this.getWorld().getFood().get(i));
				this.getWorld().getFood().get(i).terminate();
			}
		}
	}
}



