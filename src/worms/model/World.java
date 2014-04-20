package worms.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import be.kuleuven.cs.som.annotate.*;

/**
 * 
 * @author Sebastian Stoelen, Matthias Maeyens
 * @ version 1.0
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
		assert isValidWidth(width);
		assert isValidWidth(width);
		this.width = width;
		this.height = height;
		this.passableMap = passableMap;
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
	public boolean isValidWidth(double width){
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
	 * 		| new.maxWidth = maxWidth         //hoe new formuleren?
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
		return passableMap.length;
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
	public boolean isValidHeight(double height){
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
		return passableMap[0].length;
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
	private final int centerX = getPixelWidth()/2;
	
	/**
	 * Variable registering the vertical center of the world, in pixels.
	 */
	private final int centerY = getPixelHeight()/2;
	
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
	public double getDistance(double x,double y,double newX,double newY){
		return Math.sqrt(Math.pow((newX - x), 2) + Math.pow((newY -y), 2));
	}
	
	/**
	 * Method to check wheter the given coordinate is outside the boundaries of this world.
	 * @param x
	 * 		The x coordinate to be checked.
	 * @param y
	 * 		The y coordinate to be checked.
	 * @return False if x or y is less than zero, or if x is greater than the width or y greater than the height.
	 * 		result == ( (x < 0) || (x > getWidth()) || (y < 0) || (y > getHeight()) )
	 */
	public boolean isOutOfBounds(double x, double y){
		return ( (x < 0) || (x > getWidth()) || (y < 0) || (y > getHeight()) );
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
	public double[] pixelsToCoordinates(int x,int y){
		double newX = x*getWidthScale();
		double newY = (getPixelHeight() - y)*getHeightScale();
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
	public int[] coordinatesToPixels(double x,double y){
		int newX = (int) Math.round(x/getWidthScale());
		int newY = (int) Math.round(y/getHeightScale());
		newY = getPixelHeight() - newY;
		return new int[] {newX,newY};
	}
	
	/**
	 * Method to search for a position adjacent to impassable terrain, beginning from a given position.
	 * @param tempX
	 * @param tempY
	 * @return A double array, containing the position that is adjacent, if one is found, and null if none is found.
	 */
	public double[] searchAdjacentFrom(double tempX, double tempY,double radius){
		tempX = coordinatesToPixels(tempX,tempY)[0];
		tempY = coordinatesToPixels(tempX,tempY)[1];
		while (! isAdjacent(tempX,tempY,radius)){
			if (tempX < centerX)
				tempX += 1;
			if (tempX > centerX)
				tempX -= 1;
			if ((tempY < centerY) && (! isAdjacent(tempX,tempY,radius)))
				tempY += 1;
			if ((tempY > centerY) && (! isAdjacent(tempX,tempY,radius)))
				tempY -= 1;
			else 
				return null;
			}
		tempX = pixelsToCoordinates((int) tempX,(int) tempY)[0];
		tempY = pixelsToCoordinates((int) tempX,(int) tempY)[1];
		return new double[] {tempX,tempY};
		
	}
	
	//TODO: IllegalArgumentException, Postconditions
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
	 * @throws IllegalArgumentException
	 * 		No adjacent position can be found starting from (x,y)	
	 * 		| searchAdjacentFrom(x,y) == null
	 */
	public void createWorm(double x,double y,double direction,double radius,String name) throws IllegalArgumentException{
		double[] location = searchAdjacentFrom(x,y,radius);
		if (location == null)
			throw new IllegalArgumentException();
		Worm worm = new Worm(location[0],location[1],direction,radius,name);
		worm.setWorldTo(this);
		addAsWorm(worm);
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
	
	//TODO: IllegalArgumentEception, Postconditions
	/**
	 * Method to create a new food and add it to this world.
	 * @param x
	 * 		The x coordinate from which an adjacent position should be found.
	 * @param y
	 * 		The y coordinate from which an adjacent position should be found.
	 * @throws IllegalArgumentException
	 * 		No adjacent position can be found starting from (x,y)
	 * 		| searchAdjacentFrom(x,y) == null
	 */
	public void createFood(double x,double y) throws IllegalArgumentException{
		double[] location = searchAdjacentFrom(x,y,Food.getRadius());
		if (location == null)
			throw new IllegalArgumentException();
		Food food = new Food(location[0],location[1]);
		food.setWorldTo(this);
		addAsFood(food);
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
	 * method to see if an object with a given radius is passable.
	 * @param x
	 *       |the x coordinate of the center of the object.
	 * @param y
	 *       |the y coordinate of the center of the object.
	 * @param radius
	 *       |the radius of the object.
	 * @return returns a boolean that is true if and only if there is no impassable location
	 *       in the given radius.
	 */
	public boolean isPassable(double x, double y, double radius){
		double newX = x + radius;
		double newY = y;
		int pixelX = coordinatesToPixels(newX, newY)[0];
		int immPixelX = pixelX;
		int pixelY = coordinatesToPixels(newX, newY)[1];
		int change = 0;
		double maxDistance= radius/getHeightScale(); 
		while(true){
			if (pixelX-immPixelX>maxDistance){
				return true;
			}
			if (passableMap[pixelX][pixelY]==false){
				return false;
			}
			change=1;
			while(Math.sqrt((pixelX-immPixelX)*(pixelX-immPixelX)+(change)*(change))<maxDistance){
				if (passableMap[pixelX][pixelY+change]==false){
					return false;
				}
				if (passableMap[pixelX][pixelY-change]==false){
					return false;
				}
				change+=1;
			}
			pixelX+=1;
			change=0;
		}		
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
	 * @post If the food is valid, this world has the given food as one of its food.
	 * 		| this.food.contains(food)
	 * @post If the food is not valid, no food will be added.
	 * 		| new.food == this.food
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
 * method to see if an object with a given radius is adjacent to impassable terrain.
 * @param x
 *       |the x coordinate of the center of the object.
 * @param y
 *       |the y coordinate of the center of the object.
 * @param radius
 *       |the radius of the object.
 * @return returns a boolean that is true if and only if there is an impassable location
 *       between the radius and 1.1*radius.
 */
	public boolean isAdjacent(double x,double y, double radius){
		double newX= x + 1.1*radius;
		double newY= y;
		int pixelX= coordinatesToPixels(newX, newY)[0];
		int immPixelX = pixelX;
		int pixelY= coordinatesToPixels(newX, newY)[1];
		int change = 0;
		double maxDistance= (1.1*radius)/getWidthScale();
		double minDistance= radius/getWidthScale();
		while(true){
			if (Math.sqrt((pixelX-immPixelX)*(pixelX-immPixelX)+(change)*(change))>maxDistance){
				pixelX+=1;
				if (pixelX-immPixelX>maxDistance){
					return false;
				}
				if (passableMap[pixelX][pixelY]==false){
					return true;
				}
			}
			do {
				change+=1;
			} while(Math.sqrt((pixelX-immPixelX)*(pixelX-immPixelX)+(change)*(change))<minDistance);
			while(Math.sqrt((pixelX-immPixelX)*(pixelX-immPixelX)+(change)*(change))<=maxDistance){
				
				if (passableMap[pixelX][pixelY+change]==false){
					return true;
				}
				if (passableMap[pixelX][pixelY-change]==false){
					return true;
				}
				change+=1;
			}
			change=0;
		}	
	}
	
	/**
	 * Variable to determine which worms turn it is.
	 */
	private int currentTurn;
	
	/**
	 * Method to start the next turn.
	 */
	public void startNextTurn(){
		currentTurn+=1;
		if (currentTurn>=worms.size()){
			currentTurn=0;
		}
		Worm currentWorm = worms.get(currentTurn);
		if (currentWorm.getHitPoints()+10>currentWorm.getMaxHitPoints()){
			currentWorm.setHitPoints(currentWorm.getMaxHitPoints());
		}
		else{
			currentWorm.setHitPoints(currentWorm.getHitPoints()+10);
		}
		currentWorm.setCurrentAP(currentWorm.getMaxAP());
	}
	
	/**
	 * method to start the game
	 */
	public void startGame(){
		currentTurn = 0;
	}
}
