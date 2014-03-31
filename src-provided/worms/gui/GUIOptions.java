package worms.gui;

public class GUIOptions {
	
	/**
	 * Disable full screen mode
	 * Default: false
	 * 
	 * Full screen can also be disabled from the command line by providing the -window argument
	 */
	public boolean disableFullScreen = false;
	
	/**
	 * Random seed for the game
	 * Default: 3
	 * 
	 * Can also be set from the command line with the -seed argument
	 */
	public long randomSeed = 3;

	/**
	 * Enable quick worm selection by clicking the mouse
	 * Default: false
	 *  
	 * Can also be enabled from the command line with the -clickselect argument
	 */
	public boolean enableClickToSelect = false;
}