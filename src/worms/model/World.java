package worms.model;

import java.lang.reflect.Array;
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
		this.passableMap = passableMap;
		assert isValidWidth(width);
		assert isValidWidth(width);
		this.width = width;
		this.height = height;
		this.randomGenerator = random;
		this.centerX = this.getPixelWidth()/2;
		this.centerY = this.getPixelHeight()/2;
	}
	
	/**
	 * Variable registering the random generator of this world.
	 */
	private Random randomGenerator;
	
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
	public boolean isOutOfBounds(double x, double y,double radius){
		return ( (x-radius < 0) || (x+radius > getWidth()) || (y-radius < 0) || (y+radius > getHeight()) );
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
		double newY = (getPixelHeight()-y)*getHeightScale();
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
	public double[] searchAdjacentFrom(double x, double y,double radius){
		boolean change = false;
		int tempX = coordinatesToPixels(x,y)[0];
		int tempY = coordinatesToPixels(x,y)[1];
		
		while ((! isPixelAdjacent(tempX,tempY,radius)) ){
			//System.out.println("zoeken naar geschikte locatie");
			//System.out.println(tempX);
			//System.out.println(tempY);
			change = false;
			if (tempX < centerX){
				tempX += 1;
				change = true;
				}
		    if (tempX > centerX){
				tempX -= 1;
		        change = true;}
			if ((tempY < centerY) && (! isPixelAdjacent(tempX,tempY,radius))  ){
				tempY += 1;
			    change = true;}
			if ((tempY > centerY) && (! isPixelAdjacent(tempX,tempY,radius)) ){
				tempY -= 1;
				change = true;}
			if (change == false)
				return null;
			}
		x = pixelsToCoordinates((int) tempX,(int) tempY)[0];
		y = pixelsToCoordinates((int) tempX,(int) tempY)[1];
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
	 */
		public boolean isPixelAdjacent(int x,int y, double radius){
			if (!isPixelPassable(x, y, radius))
				return false;
			int maxDistance=(int) Math.round((1.1*radius)/getWidthScale());
			double minDistance= radius/getWidthScale();
			int pixelX = x + Math.round(maxDistance);
			int pixelY = y;
			double immPixelX = x;
			int change = 0;
			while(true){
				if (Math.abs(pixelX-immPixelX)>maxDistance+0.1){
					return false;
				}
				if (passableMap[pixelY][pixelX]==false){
					return true;
				}
				do {
					change+=1;
				} while(Math.sqrt((pixelX-immPixelX)*(pixelX-immPixelX)+(change)*(change))<minDistance);
				while(Math.sqrt((immPixelX-pixelX)*(immPixelX-pixelX)+(change)*(change))<=maxDistance+0.1){
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
	 * @return returns a boolean that is true if and only if there is an impassable location
	 *       between the radius and 1.1*radius.
	 */
		public boolean isAdjacent(double x,double y, double radius){
			if (!isPassable(x, y, radius))
				return false;
			double newX = x + 1.1*radius;
			double newY = y;
			int pixelX = coordinatesToPixels(newX, newY)[0];
			int immPixelX = coordinatesToPixels(x, newY)[0];
			int pixelY = coordinatesToPixels(newX, newY)[1];
			int change = 0;
			double maxDistance= (1.1*radius)/getWidthScale();
			double minDistance= radius/getWidthScale();
			if ((immPixelX + maxDistance>(getPixelWidth()-1) || immPixelX - maxDistance< 0 || pixelY + maxDistance>(getPixelHeight()-1) || pixelY-maxDistance<0))
				return false;
			while(true){
				if (Math.abs(pixelX-immPixelX)>maxDistance+0.1){
					return false;
				}
				if (passableMap[pixelY][pixelX]==false){
					return true;
				}
				do {
					change+=1;
				} while(Math.sqrt((pixelX-immPixelX)*(pixelX-immPixelX)+(change)*(change))<minDistance);
				while(Math.sqrt((immPixelX-pixelX)*(immPixelX-pixelX)+(change)*(change))<=maxDistance+0.1){
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
		public boolean isPixelPassable(int x, int y, double radius){
			
			int maxDistance=(int) Math.round((radius)/getWidthScale());
			int pixelX = x + maxDistance;
			int pixelY = y;
			if ((x + maxDistance>(getPixelWidth()-1) || x - maxDistance< 0 || pixelY + maxDistance>(getPixelHeight()-1) || pixelY-maxDistance<0))
				return false;
			double immPixelX = x;
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
	 */
	public boolean isPassable(double x, double y, double radius){
		double newX = x + radius;
		double newY = y;
		int pixelX = coordinatesToPixels(newX, newY)[0];
		int immPixelX = coordinatesToPixels(x, newY)[0];
		int pixelY = coordinatesToPixels(newX, newY)[1];
		return this.isPixelPassable(immPixelX,pixelY,radius);
		}
//		int change = 0;
//		double maxDistance= radius/getHeightScale(); 
//		while(true){
//			System.out.println("1keer");
//			if (Math.abs(pixelX-immPixelX)>maxDistance+0.01){
//				System.out.println("pasableklaar");
//				return true;
//			}
//			
//			if (passableMap[pixelY][pixelX]==false){
//				return false;
//			}
//			change=1;
//			while(Math.sqrt((pixelX-immPixelX)*(pixelX-immPixelX)+(change)*(change))<maxDistance){
//				if (passableMap[pixelY+change][pixelX]==false){
//					return false;
//				}
//				if (passableMap[pixelY - change][pixelX]==false){
//					return false;
//				}
//				change+=1;
//			}
//			pixelX-=1;
//			change=0;
//		}		
//	}


	//TODO: specification
	/**
	 * Method to create a new Worm and put it at a random location in this world.
	 * @effect The new worm is created with a random x and y coordinate (which results in an adjacent position), the minimal radius, direction set to zero
	 * 		and a name with a number, which is a representation of how many worms are already in this world.
	 * 		| new Worm worm = createWorm(randomX,randomY,0,Worm.getMinimalRadius(),"player x")
	 */
	public void addNewWorm() {
		double[] location = null;
		double radius = Worm.getMinimalRadius();
		do {
		double randomX = randomGenerator.nextFloat()*getWidth();
		double randomY = randomGenerator.nextFloat()*getHeight();
		radius = Worm.getMinimalRadius();
		if (!(randomX + radius>getWidth() || randomX-radius<0 || randomY+radius>getHeight() || randomY-radius<0)){
			location = searchAdjacentFrom(randomX, randomY, radius);}
		} while (location == null);
		int number = worms.size() + 1;
		String playerNumber = "Player ".concat(Integer.toString(number));
		createWorm(location[0], location[1], 0, radius, playerNumber);
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
	public Worm createWorm(double x,double y,double direction,double radius,String name){
		Worm worm = new Worm(x,y,direction,radius,name);
		worm.setWorldTo(this);
		addAsWorm(worm);
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
	}
	/**
	 * 
	 * 
	 * 
	 * 
	 * @return
	 */
	public Worm getCurrentWorm(){
		return this.getWorms().get(currentTurn);
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
	 * 		| if (( ! worms.size() = 1) && ( counter != worms.size() -1
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
	 */
	public boolean isGameFinished(){
		if (getWinner()!=null)
			return true;
		return false;
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
	
	//TODO: postconditions
	/**
	 * Method to add a new food at a random location in this world.
	 * @effect The new food is initialized with a random x and y.
	 * 		| food = new Food(randomX,randomY)
	 */
	public void addNewFood() {
		double[] location = null;
		double radius = Food.getRadius();
		do {
		double randomX = randomGenerator.nextFloat()*getWidth();
		double randomY = randomGenerator.nextFloat()*getHeight();
		radius = Food.getRadius();
		if (!(randomX + radius>getWidth() || randomX-radius<0 || randomY+radius>getHeight() || randomY-radius<0)){
			location = searchAdjacentFrom(randomX, randomY, radius);}
		} while (location == null);
		createFood(location[0], location[1]);
	}
	//TODO: Postconditions
	/**
	 * Method to create a new food and add it to this world.
	 * @param x
	 * 		The x coordinate from which an adjacent position should be found.
	 * @param y
	 * 		The y coordinate from which an adjacent position should be found.
	 * @effect The new food is intialized with the given x and y
	 * 		| food = new Food(x,y)
	 * @post The world of the food is this world
	 * 		| food.getWorld() == this
	 * @post This world contains the given food
	 * 		| this.food.contains(food)	
	 */
	public Food createFood(double x,double y){
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
		if (!food.hasWorld())
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

	//TODO: IllegalArgumentEception, Postconditions
		/**
		 * Method to create a new team and add it to this world.
		 * @param x
		 * 		The x coordinate from which an adjacent position should be found.
		 * @param y
		 * 		The y coordinate from which an adjacent position should be found.
		 * @throws IllegalArgumentException
		 * 		No adjacent position can be found starting from (x,y)
		 * 		| searchAdjacentFrom(x,y) == null
		 */
		public void createTeam(String name) throws IllegalArgumentException{
			if (name == null || team.size()==10)
				throw new IllegalArgumentException();
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
	 * 		| this.team.contains(team)
	 */
	public boolean hasAsTeam(Team team){
		return this.team.contains(team);
	}
	
	/**
	 * Method to add the given team to this world.
	 * @param team
	 * 		The team to be added
	 * @post If the team is valid, this world has the given team as one of its team.
	 * 		| this.team.contains(team)
	 * @post If the team is not valid, no team will be added.
	 * 		| new.team == this.team
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
		this.team.add(team);
	}
	
	/**
	 * Method to remove a given team from this world.
	 * @param team
	 * 		The team to be removed.
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
		this.team.remove(team);
	}
	
	/**
	 * Method to return a list of all the team in this world.
	 */
	public List<Team> getTeam(){
		return this.team;
	}
	/**
	 * Variable registering all the team currently in this world.
	 */
	private final List<Team> team = new ArrayList<Team>();
	
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

	/**
	 * Variable to determine which worms turn it is.
	 */
	private int currentTurn;
	
	/**
	 * 
	 * @return
	 */
	public Projectile getActiveProjectile(){
		return this.activeProjectile;
	}
	/**
	 * 
	 * @param projectile
	 */
	public void setActiveProjectile(Projectile projectile){
		this.activeProjectile=projectile;	
	}
	/**
	 * 
	 */
	private Projectile activeProjectile;
}
