package worms.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import programs.Program;
import worms.model.expressions.SelfWormExpression;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of a World. A world has a width, a height, a passableMap and a random generator.
 * A world also has a collection of worm, teams and food. They can be empty.
 * It also has methods to create a Worm and food on a random adjacent location.
 * 
 * 
 * code repository:https://github.com/sebastianstoelen/OOP-Worms
 * @author Sebastian Stoelen 2BbiCwsElt2, Matthias Maeyens 2BbiCwsElt2
 * @version 1.0
 *
 */
public class World {

	/**
	 * Create a new World.
	 * 
	 * @param width
	 * @param height
	 * @param passableMap
	 * @param random
	 */
	@Raw
	public World(double width, double height, boolean[][] passableMap, Random random) {
		this.passableMap = passableMap;
		assert isValidWidth(width);
		assert isValidHeight(height);
		this.width = width;
		this.height = height;
		this.randomGenerator = random;
		this.centerX = this.getPixelWidth()/2;
		this.centerY = this.getPixelHeight()/2;
	}
	
	/**
	 * Returns the randomGenerator of this world.
	 */
	@Basic
	public Random getRandomGenerator(){
		return this.randomGenerator;
	}
	/**
	 * Variable registering the random generator of this world.
	 */
	private Random randomGenerator;
	
	/**
	 * Returns the passableMap of this world.
	 */
	@Basic
	public boolean[][] getPassableMap(){
		return this.passableMap;
	}
	/**
	 * Variable registering the passableMap of this World.
	 */
	private boolean[][] passableMap;
	
	/**
	 * Method to return the width of the current World.
	 */
	public double getWidth(){
		return width;
	}
	
	/**
	 * Method to check whether the given width is greater than zero and less than maxWidth.
	 * @param width
	 * @return 	True if and only if width is grater than zero an less than maxWidth.
	 * 			| return == (width >= 0) && (width <= maxWidth)
	 */
	public static boolean isValidWidth(double width){
		return (width >= 0) && (width <= maxWidth);
	}
	
	/**
	 * Variable registering the width of the World.
	 */
	private final double width;
	
	/**
	 * Method to set maxWidth to the given maxWidth.
	 * @param maxWidth
	 * @post The new maximum width is equal to maxWidth
	 * 		| new.maxWidth = maxWidth         
	 */
	public static void setMaxWidth(double maxWidth){
		World.maxWidth = maxWidth;
	}
	
	/**
	 * Variable registering the maximum width of any World.
	 */
	private static double maxWidth = Double.MAX_VALUE;
	
	/**
	 * Method to return the amount of pixels on the horizontal axis of the World.
	 * @return The length of passableMap
	 * 		| return == passableMap.length
	 */
	public int getPixelWidth(){
		return passableMap[0].length;
	}
	
	/**
	 * Method to calculate the conversion factor from horizontal pixels to horizontal coordinates.
	 * @return The quotient of the width and the pixelWidth
	 * 		| return == getWidth()/getPixelWidth()
	 */
	public double getWidthScale(){
		return (getWidth()/getPixelWidth());
	}
	
	/**
	 * Method to return the height of the given world.
	 */
	public double getHeight(){
		return height;
	}
	
	/**
	 * Method to check whether the given height is greater than zero and less than maxHeight.
	 * @param height
	 * @return 	True if and only if height is grater than zero an less than maxHeight.
	 * 			| return == (height >= 0) && (height <= maxHeight)
	 */
	public static boolean isValidHeight(double height){
		return (height >= 0) && (height <= maxHeight);
	}
	
	/**
	 * Variable registering the height of the current World.
	 */
	private final double height;
	
	/**
	 * Method to set the maximum Height to the given maxHeight.
	 * @param maxHeight
	 * @post The new maxHeight is equal to maxHeight
	 * 		| new.maxHeight == maxHeight
	 */
	public static void setMaxHeight(double maxHeight){
		World.maxHeight = maxHeight;
	}
	
	/**
	 * Variable registering the maximum height of any World.
	 */
	private static double maxHeight = Double.MAX_VALUE;
	
	/**
	 * Method to return the amount of pixels on the vertical axis of the World.
	 * @return The length of the first array of passableMap
	 * 		| return == passableMap[0].length
	 */
	public int getPixelHeight(){
		return passableMap.length;
	}
	
	/**
	 * Method to calculate the conversion factor from vertical pixels to vertical coordinates.
	 * @return The quotient of the height and the pixelHeight
	 * 		| return == getHeight()/getPixelHeight()
	 */
	public double getHeightScale(){
		return (getHeight()/getPixelHeight());
	}
	
	/**
	 * Variable registering the horizontal center of the world, in pixels.
	 */
	private final int centerX;
	
	/**
	 * Variable registering the vertical center of the world, in pixels.
	 */
	private final int centerY;
	
	/**
	 * Method to calculate the distance between two coordinates.
	 * @param x
	 * 		The first x coordinate
	 * @param y
	 * 		The first y coordinate
	 * @param newX
	 * 		The second x coordinate
	 * @param newY
	 * 		The second y coordinate
	 * @return The square root of the sum of the square of the differences between x coordinates and y coordinates. 
	 * 		return == Math.sqrt(Math.pow((newX - x), 2) + Math.pow((newY -y), 2))
	 */
	public static double getDistance(double x,double y,double newX,double newY){
		return Math.sqrt(Math.pow((newX - x), 2) + Math.pow((newY -y), 2));
	}
	
	/**
	 * Method to check whether the given coordinate is outside the boundaries of this world.
	 * @param x
	 * 		The x coordinate to be checked.
	 * @param y
	 * 		The y coordinate to be checked.
	 * @return False if x or y is less than zero, or if x is greater than the width or y greater than the height.
	 * 		result == ( (x-radius < (-getWidthScale()/2)) || (x+radius > getWidth()-(getWidthScale()/2)) 
				|| (y-radius < (getHeightScale()/2)) 
				|| (y+radius > getHeight()+(getHeightScale()/2)) || Double.isNaN(x) || Double.isNaN(y) );
	 */
	public boolean isOutOfBounds(double x, double y,double radius){
		return ( (x-radius < 0) || (x+radius >= getWidth()) 
				|| (y-radius < 0) 
				|| (y+radius >= getHeight()) || Double.isNaN(x) || Double.isNaN(y) );
	}
	
	/**
	 * Method to convert a given pixel to a coordinate.
	 * @param x
	 * 		The column in which the pixel is located
	 * @param y
	 * 		The row in which the pixel is located
	 * @return x is multiplied with the width scale. y is first mirrored around the center row 
	 * 			and then multiplied with the height scale. The result is returned as an array of doubles.
	 * 			| return == {x*getWidthScale(),(getPixelHeight() - y)*getHeightScale()}
	 */
	private double[] pixelsToCoordinates(int x,int y){
		double newX = x*getWidthScale()+getWidthScale()/2;
		double newY = (getPixelHeight()-y)*getHeightScale()+getHeightScale()/2;
		return new double[] {newX,newY};
	}
	
	/**
	 * Method to convert a given coordinate to a pixel.
	 * @param x
	 * 		The x-coordinate
	 * @param y
	 * 		The y-coordinate
	 * @return x is divided by its scale. y is divided by its scale and then mirrored around the center row.
	 * 			the result is rounded to an integer and returned as an array of integer values.
	 * 			| return == {Math.round(x/getWidthScale()),getPixelHeight() - Math.round(y/getHeightScale())}
	 */
	private int[] coordinatesToPixels(double x,double y){
		int newX = (int) Math.floor(x/getWidthScale());
		int newY = (int) Math.floor(y/getHeightScale());
		newY = (getPixelHeight()-1) - newY;
		return new int[] {newX,newY};
	}
	
	/**
	 * Method to search for a position adjacent to impassable terrain, beginning from a given position.
	 * @param x
	 * @param y
	 * @return A double array, containing the position that is adjacent, if one is found, and null if none is found.
	 */
	private double[] searchAdjacentFrom(double x, double y,double radius){
		boolean change = false;;
		while ((! isAdjacent(x,y,radius)) ){
			change = false;
			if (coordinatesToPixels(x, y)[0] < centerX){
				x += getWidthScale();
				change = true;
				}
		    if (coordinatesToPixels(x, y)[0] > centerX){
				x -= getWidthScale();
		        change = true;}
			if ((coordinatesToPixels(x, y)[1] < centerY) && (! isAdjacent(x,y,radius))){
				y -= getHeightScale();
			    change = true;}
			if ((coordinatesToPixels(x, y)[1] > centerY) && (! isAdjacent(x,y,radius)) ){
				y += getHeightScale();
				change = true;}
			if (change == false)
				return null;
			}
		return new double[] {x,y};
		
	}
	
	/**
	 * method to see if an object with a given radius is adjacent to impassable terrain.
	 * @param x
	 *       |the x coordinate of the center of the object.
	 * @param y
	 *       |the y coordinate of the center of the object.
	 * @param radius
	 *       |the radius of the object.
	 * @return returns a boolean that is true if and only if there is an impassable location
	 *       between the radius and 1.1*radius.
	 *       |if{
	 *       |for each(newX,newY) for which holds that (getDistance(x,y,newX,newY) < maxDistance
	 *       | and getDistance(x,y,newX,newY) < minDistance)
	 *       | => passableMap[newY][newX]==true:return false}
	 *       |else: return true
	 */
		private boolean isPixelAdjacent(int x,int y, double radius){
			if (!isPixelPassable(x, y, radius))
				return false;
			int maxDistance=(int) Math.round((1.1*radius)/getWidthScale());
			double minDistance= radius/getWidthScale();
			int pixelX = x + Math.round(maxDistance);
			int pixelY = y;
			double immPixelX = x;
			int change = 0;
			while(true){
				if (Math.abs(pixelX-immPixelX)>maxDistance+0.01){
					return false;
				}
				if (passableMap[pixelY][pixelX]==false){
					return true;
				}
				do {
					change+=1;
				} while(Math.sqrt((pixelX-immPixelX)*(pixelX-immPixelX)+(change)*(change))<minDistance);
				while(Math.sqrt((immPixelX-pixelX)*(immPixelX-pixelX)+(change)*(change))<=maxDistance+0.01){
					if (passableMap[pixelY - change][pixelX]==false){
						return true;
					}
					if (passableMap[pixelY + change][pixelX]==false){
						return true;
					}
					change+=1;
				}
				change=0;
				pixelX-=1;
			}	
		}

	/**
	 * method to see if an object with a given radius is adjacent to impassable terrain.
	 * @param x
	 *       |the x coordinate of the center of the object.
	 * @param y
	 *       |the y coordinate of the center of the object.
	 * @param radius
     *       |the radius of the object.
	 * @return |if{
     *       |for each(newX,newY) for which holds (getDistance(x,y,newX,newY) < 1.1*radius
     *       | and getDistance(x,y,newX,newY) > radius)
     *       | => passableMap[coordinatesToPixels(newX, newY)[1]][coordinatesToPixels(newX, newY)[0]]
     *       |==true:return false}
	 *       |else: return true
	 */
		public boolean isAdjacent(double x,double y,double radius){
			if (!isPassable(x,y,radius)){
				return false;}
			if (isOutOfBounds(x, y,1.1* radius)||isOutOfBounds(x, y,1.1* radius)){
				return false;}
			double change=0.0;
			double maxDistance = 1.1 * radius;
			double minDistance = radius;
			double xchange=maxDistance;
			while(true){
				if (Math.abs(xchange)>maxDistance){
					return false;}
				if (passableMap[coordinatesToPixels(x+xchange, y)[1]][coordinatesToPixels(x+xchange, y)[0]]==false)
					return true;
				do {change+=maxDistance/(Math.ceil((radius/(getHeightScale()/3))));
					}while((Math.sqrt((xchange)*(xchange)+(change)*(change))<minDistance-0.01));
				while ((Math.sqrt((xchange)*(xchange )+(change)*(change))<=maxDistance )){
					if (passableMap[coordinatesToPixels(x + xchange, y+change)[1]][coordinatesToPixels(x + xchange, y+ change)[0]]==false)
						return true;
					if (passableMap[coordinatesToPixels(x + xchange, y-change)[1]][coordinatesToPixels(x + xchange, y- change)[0]]==false)
						return true;
					change+=maxDistance/(Math.ceil((radius/(getHeightScale()/3))));
				}
				xchange-=maxDistance/(Math.ceil((radius/(getWidthScale()/2))));
				change =0;
			}
		}
		
	/**
	 * method to see if an object with a given radius is passable.
	 * @param x
	 *       |the x coordinate of the center of the object.
	 * @param y
	 *       |the y coordinate of the center of the object.
	 * @param radius
	 *       |the radius of the object.
	 * @return returns a boolean that is true if and only if there is no impassable location
	 *       in the given radius.
	 *       |if{
	 *       |for each(newX,newY) where getDistance(x,y,newX,newY) < maxDistance
	 *       | => passableMap[newY][newX]==true:return true}
	 *       |else: return false
	 */
		private boolean isPixelPassable(int x, int y, double radius){
            double maxDistance=radius/getWidthScale();
			
			int pixelX = x;
			int pixelY = y;
			double immPixelX = x-maxDistance;
			int change = 0;
			while(true){
				if (Math.abs(pixelX-immPixelX)>maxDistance+0.01){
					return true;
				}
				if (passableMap[pixelY][pixelX]==false){
					return false;
				}
				change=1;
				while(Math.sqrt((pixelX-immPixelX)*(pixelX-immPixelX)+(change)*(change))<maxDistance){
					if (passableMap[pixelY+change][pixelX]==false){
						return false;
					}
					if (passableMap[pixelY - change][pixelX]==false){
						return false;
					}
					change+=1;
				}
				pixelX-=1;
				change=0;
			}		
		}

	/**
	 * method to see if an object with a given radius is passable.
	 * @param x
	 *       |the x coordinate of the center of the object.
	 * @param y
	 *       |the y coordinate of the center of the object.
	 * @param radius
	 *       |the radius of the object.
	 * @return returns a boolean that is true if and only if there is no impassable location
	 *       in the given radius.
	 *       |if{
     *       |for each(newX,newY) for which holds getDistance(x,y,newX,newY) < radius
     *       | => passableMap[coordinatesToPixels(newX, newY)[1]][coordinatesToPixels(newX, newY)[0]]
     *       |==true:return true}
	 *       |else: return false
	 */
	public boolean isPassable(double x, double y, double radius){
		if (isOutOfBounds(x, y, radius))
			return false;
		double change=0.0;
		double maxDistance = radius;
		double xchange=-maxDistance;
		while(true){
			if (Math.abs(xchange)>maxDistance){
				return true;}
			if (passableMap[coordinatesToPixels(x+xchange, y)[1]][coordinatesToPixels(x+xchange, y)[0]]==false)
				return false;
			change+=maxDistance/(Math.ceil((radius/(getHeightScale()))));
			while ((Math.sqrt((xchange)*(xchange )+(change)*(change))<=maxDistance )){
				if (change!=maxDistance){
					if (passableMap[coordinatesToPixels(x + xchange, y+change)[1]][coordinatesToPixels(x + xchange, y+ change)[0]]==false)
						return false;}
				if (passableMap[coordinatesToPixels(x + xchange, y-change)[1]][coordinatesToPixels(x + xchange, y- change)[0]]==false)
					return false;
				change+=maxDistance/(Math.ceil((radius/(getHeightScale()))));
			}
			xchange+=maxDistance/(Math.ceil((radius/(getWidthScale()))));
			change =0;
		}
	}

	//TODO: specification
	/**
	 * Method to create a new Worm and put it at a random location in this world.
	 * @effect The new worm is created with a random x and y coordinate (which results in an adjacent position), the minimal radius, direction set to zero
	 * 		and a name with a number, which is a representation of how many worms are already in this world.
	 * 		| new Worm worm = createWorm(randomX,randomY,0,Worm.getMinimalRadius(),"player x")
	 */
	public void addNewWorm(Program program) {
		double[] location = null;
		double radius = Worm.getMinimalRadius();
		do {
		double randomX = randomGenerator.nextFloat()*getWidth();
		double randomY = randomGenerator.nextFloat()*getHeight();
		radius = Worm.getMinimalRadius();
		if (!(isOutOfBounds(randomX, randomY, radius))){
			location = searchAdjacentFrom(randomX, randomY, radius);}
		} while (location == null);
		int number = worms.size() + 1;
		String playerNumber = "Player ".concat(Integer.toString(number));
		createWorm(location[0], location[1], 0, radius, playerNumber,program);
	}
	
	//TODO: Postconditions
	/**
	 * Method to create a worm and add it to this world.
	 * @param x
	 * 		The x coordinate from which an adjacent position is to be found.
	 * @param y
	 * 		The y coordinate from which an adjacent position is to be found.
	 * @param direction
	 * 		The direction the new worm should have.
	 * @param radius
	 * 		The radius the new worm should have.
	 * @param name
	 * 		The name the new worm should have.
	 * @effect The new worm is initialized with x,y,direction,radius and name.
	 * 		| worm = new Worm(x,y,direction,radius,name)
	 * @post The world of the new worm is this world.
	 * 		| new.worm.getWorld() == this
	 * @post The new worm belongs to this world.
	 * 		| this.worms.contains(worm)
	 */
	public Worm createWorm(double x,double y,double direction,double radius,String name, Program program) throws IllegalArgumentException
	,IllegalRadiusException,IllegalNameException{
		if (!Worm.isValidRadius(radius))
			throw new IllegalRadiusException(radius);
		if (isOutOfBounds(x, y, radius))
			throw new IllegalArgumentException("These coordinates won't work");
		if (!Worm.isValidName(name))
			throw new IllegalNameException(name);
		Worm worm = new Worm(x,y,direction,radius,name,program);
		worm.setWorldTo(this);
		addAsWorm(worm);
		if (this.getTeam().size()>0)
			worm.setTeamTo(getTeam().get(this.getTeam().size()-1));
		return worm;
	}
	
	/**
	 * Method to check whether the world can have the given worm as one of its worms
	 * @param worm
	 * @return False if the given worm is null.
	 * 			| return == (worm != null)
	 */
	public boolean canHaveAsWorm(Worm worm){
		return (worm != null);
	}
	
	/**
	 * Method to check if the given worm is already in this world.
	 * @param worm
	 * @return True if and only if the given worm is in worms.
	 * 			| return == worms.contains(worm)
	 */
	public boolean hasAsWorm(Worm worm){
		return worms.contains(worm);
	}
	
	/**
	 * Method to add a given worm to the world.
	 * @param worm
	 * 		 the worm to be added
	 * @post If the worm is valid, this world has the given worm as one of its worms.
	 * 		| worms.contains(worm)
	 * @post If the worm is not valid, no worm will be added.
	 * 		| new.worms == this.worms
	 * @throws IllegalWormException
	 * 		This world cannot have the given worm as one of its worms.
	 * 		| ! canHaveAsWorm(worm)
	 * @throws IllegalStateException
	 * 		The given worm does not have this world as its world, or this world already contains the given worm.
	 * 		| (worm.getWorld() != this) || (hasAsWorm(worm))
	 */
	public void addAsWorm(Worm worm) throws IllegalWormException, IllegalStateException{
		if(! canHaveAsWorm(worm))
			throw new IllegalWormException(worm);
		if ((worm.getWorld() != this) || (hasAsWorm(worm)))
			throw new IllegalStateException();
		worms.add(worm);
	}
	
	/**
	 * Method to remove the given worm from this world.
	 * @param worm
	 * @throws IllegalWormException
	 * 		The given worm is not effective, or this world does not contain the given worm.
	 * 		| (worm == null) || (! hasAsWorm(worm))
	 * @throws IllegalStateException
	 * 		The given worm still has a world.
	 * 		| worm.hasWorld()
	 */
	public void removeAsWorm(Worm worm) throws IllegalWormException, IllegalStateException {
		if ((worm == null) || (! hasAsWorm(worm)))
			throw new IllegalWormException(worm);
		if (worm.hasWorld())
			throw new IllegalStateException();
		worms.remove(worm);
		if (currentTurn>=worms.size()){
			currentTurn=0;
		}
	}
	
	/**
	 * Method to return the worm that is currently able to perform actions.
	 * @return The worm with the same index as currentTurn
	 * 		| getWorms().get(currentTurn)
	 */
	public Worm getCurrentWorm() throws IndexOutOfBoundsException{
		if (getCurrentTurn()>=getWorms().size())
			throw new IndexOutOfBoundsException("currentTurn is too high.");
		return getWorms().get(currentTurn);
	}
	
	/**
	 * Method to return the name of the winner of the game, or the name of the only team that is left. 
	 * If none of those two are existent, the method shall return null.
	 * @return if there is only one worm left, the method returns the name of that worm.
	 * 		| if (worms.size() == 1)
	 *		|	return ==  worms.get(0).getName()
	 * @return if there is only one team left, the method returns the name of that team.
	 * 		| while ((i  <= worms.size() - 1) && (worms.get(i).getTeamName() == worms.get(i+1).getTeamName()))
	 *		|	counter += 1;
	 * 		| if counter == worms.size() - 1
	 * 		| 	return == worms.ge(0).getTeamName()
	 * @return in the case where there is no victor or victorious team, the method returns null.
	 * 		| if (( ! worms.size() = 1) && ( counter != worms.size() -1)
	 * 		|	return == null
	 */
	public String getWinner(){
		if (worms.size() == 1)
			return worms.get(0).getName();
		int i = 0;
		while (i  <= worms.size() - 2){
			if (worms.get(i).getTeamName() != worms.get(i+1).getTeamName())
				return null;
			i += 1;
		}
		return worms.get(0).getTeamName();	
	}
	/**
	 * Method to check whether the game is finished.
	 * @return returns true if and only if getWinner != null
	 * 		| return == (!(getWinner() == null))
	 */
	public boolean isGameFinished(){
		return (!(getWinner() == null));
	}
	
	/**
	 * Method to return a list of the worms in this world.
	 */
	@Basic
	public List<Worm> getWorms(){
		return this.worms;
	}
	
	/**
	 * Variable registering a list of the worms currently in this world.
	 */
	private final List<Worm> worms = new ArrayList<Worm>();
	
	/**
	 * Method to add a new food at a random location in this world.
	 * @effect The new food is initialized with a random x and y.
	 * 		|  createFood(randomX,randomY)
	 */
	public void addNewFood() {
		double[] location = null;
		double radius = Food.getRadius();
		do {
		double randomX = randomGenerator.nextFloat()*getWidth();
		double randomY = randomGenerator.nextFloat()*getHeight();
		radius = Food.getRadius();
		if (!(isOutOfBounds(randomX,randomY,radius))){
			location = searchAdjacentFrom(randomX, randomY, radius);}
		} while (location == null);
		createFood(location[0], location[1]);
	}
	
	/**
	 * Method to create a new food and add it to this world.
	 * @param x
	 * 		The x coordinate from which an adjacent position should be found.
	 * @param y
	 * 		The y coordinate from which an adjacent position should be found.
	 * @effect The new food is initialized with the given x and y
	 * 		| food = new Food(x,y)
	 * @post The world of the food is this world
	 * 		| food.getWorld() == this
	 * @post This world contains the given food
	 * 		| new.food.contains(food)	
	 */
	public Food createFood(double x,double y){
		if (isOutOfBounds(x, y, Food.getRadius()))
			throw new IllegalArgumentException();
		Food food = new Food(x,y);
		food.setWorldTo(this);
		addAsFood(food);
		return food;
	}
	
	/**
	 * Method to check whether this world can have the given food as one of its food.
	 * @param food
	 * @return True if and only if food is not null.
	 * 		| return == !(food == null)
	 */
	public boolean canHaveAsFood(Food food){
		return (!(food == null));
	}
	
	/**
	 * Method to check whether this world already contains the given food.
	 * @param food
	 * @return True if and only the given food is in food.
	 * 		| this.food.contains(food)
	 */
	public boolean hasAsFood(Food food){
		return this.food.contains(food);
	}
	
	/**
	 * Method to add the given food to this world.
	 * @param food
	 * 		The food to be added
	 * @post This world has the given food as one of its food.
	 * 		| new.food.contains(food)
	 * @throws IllegalFoodException
	 * 		This world cannot have the given food as one of its food.
	 * 		| ! canHaveAsFood(food)
	 * @throws IllegalStateException
	 * 		The food does not have this world as its world, or this world already contains the given food.
	 * 		| (food.getWorld() != this) || (hasAsFood(food))
	 */
	public void addAsFood(Food food) throws IllegalFoodException, IllegalStateException{
		if(! canHaveAsFood(food))
			throw new IllegalFoodException(food);
		if ((food.getWorld() != this) || (hasAsFood(food)))
			throw new IllegalStateException();
		this.food.add(food);
	}
	
	/**
	 * Method to remove a given food from this world.
	 * @param food
	 * 		The food to be removed.
	 * @post The new world will not have the given worm as one of its worms.
	 * 		| new.food.contains(food) == false
	 * @throws IllegalFoodException
	 * 		The given food is null, or this world does not contain the given worm.
	 * 		| (food == null) || (! hasAsFood(food))
	 * @throws IllegalStateException
	 * 		The given food still has a world.
	 * 		| food.hasWorld()
	 */
	public void removeAsFood(Food food) throws IllegalFoodException, IllegalStateException{
		if ((food == null) || (! hasAsFood(food)))
			throw new IllegalFoodException(food);
		if (food.hasWorld())
			throw new IllegalStateException();
		this.food.remove(food);
	}
	
	/**
	 * Method to return a list of all the food in this world.
	 */
	public List<Food> getFood(){
		return this.food;
	}
	
	/**
	 * Variable registering all the food currently in this world.
	 */
	private final List<Food> food = new ArrayList<Food>();

	/**
	 * Method to create a new team and add it to this world.
	 * @param name
	 * 		The name of the new team
	 * @effect The new team is created with the given name
	 * 		| new Team(name)
	 * @effect The teams world is set to this.
	 * 		| team.setWorldTo(this)
	 * @effect The new team is added to this world.
	 * 		| addAsTeam(team)
	 * @throws IllegalArgumentException
	 * 		The given name is not valid
	 * 		| ! Team.isValidName(name)
	 * @throws IllegalStateException
	 * 		The world already contains ten teams
	 * 		| teams.size() >= 10
	 */
	public void createTeam(String name) throws IllegalNameException{
		if (!Team.isValidName(name))
			throw new IllegalNameException(name);
		if (teams.size() >= 10)
			throw new IllegalStateException();
		Team team = new Team(name);
		team.setWorldTo(this);
		addAsTeam(team);
	}
		
	/**
	 * Method to check whether this world can have the given team as one of its team.
	 * @param team
	 * @return True if and only if team is not null.
	 * 		| return == !(team == null)
	 */
	public boolean canHaveAsTeam(Team team){
		return (!(team == null));
	}
		
	/**
	 * Method to check whether this world already contains the given team.
	 * @param team
	 * @return True if and only the given team is in team.
	 * 		| return == this.teams.contains(team)
	 */
	public boolean hasAsTeam(Team team){
		return this.teams.contains(team);
	}
	
	/**
	 * Method to add the given team to this world.
	 * @param team
	 * 		The team to be added
	 * @post This world has the given team as one of its team.
	 * 		| new.teams.contains(team)
	 * @throws IllegalTeamException
	 * 		This world cannot have the given team as one of its team.
	 * 		| ! canHaveAsTeam(team)
	 * @throws IllegalStateException
	 * 		The team does not have this world as its world, or this world already contains the given team.
	 * 		| (team.getWorld() != this) || (hasAsTeam(team))
	 */
	public void addAsTeam(Team team) throws IllegalTeamException, IllegalStateException{
		if(! canHaveAsTeam(team))
			throw new IllegalTeamException(team);
		if ((team.getWorld() != this) || (hasAsTeam(team)))
			throw new IllegalStateException();
		this.teams.add(team);
	}
	
	/**
	 * Method to remove a given team from this world.
	 * @param team
	 * 		The team to be removed.
	 * @post The new world will no longer have the given team as one of its teams.
	 * 		| new.teams.contains(team) == false
	 * @throws IllegalTeamException
	 * 		The given team is null, or this world does not contain the given worm.
	 * 		| (team == null) || (! hasAsTeam(team))
	 * @throws IllegalStateException
	 * 		The given team still has a world.
	 * 		| team.hasWorld()
	 */
	public void removeAsTeam(Team team) throws IllegalTeamException, IllegalStateException{
		if ((team == null) || (! hasAsTeam(team)))
			throw new IllegalTeamException(team);
		if (team.hasWorld())
			throw new IllegalStateException();
		this.teams.remove(team);
	}
	
	/**
	 * Method to return a list of all the team in this world.
	 */
	public List<Team> getTeam(){
		return this.teams;
	}
	/**
	 * Variable registering all the team currently in this world.
	 */
	private final List<Team> teams = new ArrayList<Team>();
	
	/**
	 * Method to start the next turn.
	 * @post currentTurn will be increased by one.
	 * 		| new.currentTurn == this.currentTurn + 1
	 * @post If currentTurn is greater than or equal to the size of worms, currentTurn will be set to zero.
	 * 		| if (currentTurn >= worms.size()){
	 *		|	new.currentTurn == 0;
	 * @effect The hitPoints of the current worm will be increased by 10
	 * 		| new.worms.get(new.currentTurn).setHitPoints(new.worms.get(new.currentTurn).getHitPoints() + 10)
	 * @effect If the sum of the current hitPoints and 10 is greater than the maxHitPoints, 
	 * 		the hitPoints of the current worm will be set to the maxHitPoints
	 * 		| if (new.worms.get(new.currentTurn).getHitPoints() + 10 > new.worms.get(new.currentTurn).getMaxHitPoints())
	 * 		| 	new.worms.get(new.currentTuren).setHitPointss(new.worms.get(new.currentTurn).getMaxHitPoints())
	 * 
	 */
	public void startNextTurn(){
		currentTurn += 1;
		if (currentTurn >= worms.size()){
			currentTurn = 0;
		}
		Worm currentWorm = worms.get(currentTurn);
		if (currentWorm.getHitPoints()+10>currentWorm.getMaxHitPoints()){
			currentWorm.setHitPoints(currentWorm.getMaxHitPoints());
		}
		else{
			currentWorm.setHitPoints(currentWorm.getHitPoints()+10);
		}
		currentWorm.setCurrentAP(currentWorm.getMaxAP());
		if (currentWorm.getProgram() != null){
			SelfWormExpression.setWorm(currentWorm);
			currentWorm.getProgram().executeProgram(); }
	}
	
	/**
	 * Method to start the game
	 * @post currentTurn is set to zero
	 * 		| new.currentTurn == 0
	 */
	public void startGame(){
		currentTurn = 0;
		Worm currentWorm = worms.get(currentTurn);
		if (currentWorm.getProgram() != null){
			SelfWormExpression.setWorm(currentWorm);
			currentWorm.getProgram().executeProgram();
		}
	}
	
	/**
	 * Method that returns the current turn.
	 */
	@Basic
	public int getCurrentTurn(){
		return this.currentTurn;
	}

	/**
	 * Variable to determine which worms turn it is.
	 */
	private int currentTurn;
	
	/**
	 * Method to return the active projectile.
	 */
	@Basic
	public Projectile getActiveProjectile(){
		return this.activeProjectile;
	}
	/**
	 * Method to set the activeProjectile to the given projectile.
	 * @param projectile
	 * @post the new active projectile is the given projectile.
	 * 		| new.getActiveProjectile() == projectile
	 */
	public void setActiveProjectile(Projectile projectile){
		this.activeProjectile=projectile;	
	}
	
	/**
	 * Variable registering the activeProjectile of this world.
	 */
	private Projectile activeProjectile;
}
