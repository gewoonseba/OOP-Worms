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
	}
	
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
	 * 		| this.worms == new.worms
	 * @throws IllegalWormException
	 * 		This world cannot have the given worm as one of its worms.
	 * 		| ! canHaveAsWorm(worm)
	 * @throws IllegalStateException
	 * 		This world already contains the given worm, or the given worm does not have this world as its world.
	 * 
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
	
	private final List<Worm> worms = new ArrayList<Worm>();
	
	/**
	 * Method to search for a passable possition, beginning from a given position.
	 * @param tempX
	 * @param tempY
	 * @return
	 */
	public int[] searchPassableFrom(int tempX, int tempY){
		int[] wormPosition = {null,null};
		while (! isPassable(tempX,tempY)){
			if (tempX < midX)
				tempX += 1;
			if (tempX > midX)
				tempX -= 1;
			if ((tempY < midY) && (! isPassable(tempX,tempY)))
				tempY += 1;
			if ((tempY > midY) && (! isPassable(tempX,tempY)))
				tempY -= 1;
			else 
				return wormPosition;
			}
		wormPosition = {tempX,tempY};
		return wormPosition;
	}

}
