package worms.model;

import java.util.Random;

import be.kuleuven.cs.som.annotate.*;

/**
 * 
 * @author Sebastian
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
		this.width = width;
	}
	
	public double getWidth(){
		return width;
	}
	
	public boolean isValidWidth(double width){
		return (width >= 0) && (width <= maxWidth);
	}
	
	private final double width;
	
	public static void setMaxWidth(double maxWidth){
		World.maxWidth = maxWidth;
	}
	
	private static double maxWidth = Double.MAX_VALUE;
	
	public double getHeigth(){
		return heigth;
	}
	
	public boolean isValidHeigth(double heigth){
		return (heigth >= 0) && (heigth <= maxHeigth);
	}
	
	private final double heigth;
	
	public static void setMaxHeigth(double maxHeigth){
		World.maxHeigth = maxHeigth;
	}
	
	private static double maxHeigth = Double.MAX_VALUE;

}
