package worms.model;

import java.util.ArrayList;
import java.util.List;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

public class Team {
	public Team(String name){
		this.setName(name);
	}
	
	/**
	 * Method to return the name of the given team.
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
			if (!(Character.isLetter(c)))
				return false;
		}
		return true;
	}
	
	/**
	 * Set the name of the given team to the given name.
	 * @param name
	 *        The new name of the given team.
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
	
	/**
	 * Method to set the World of this team to the given world.
	 * @param world
	 * 		The world that should be set as the world of this worm.
	 * @post If the world is valid, the new World of this team is the given world.
	 * 		| new.getWorld() == world
	 * @throws IllegalWorldException
	 * 		This worm cannot have the given world as its world.
	 * 		| ! canHaveAsWorld(world)
	 * @throws IllegalStateException
	 * 		This team already has a world.
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
	 * Return the world of this team.
	 */
	@Basic @Raw
	public World getWorld(){
		return this.world;
	}
	
	/**
	 * Method to check whether this team can have the given world as its world.
	 * @param world
	 * 		The world to be checked.
	 * @return True if and only if the world is not null and the given world does not already contains this team.
	 * 			| return == (world != null) && (! world.hasAsWorm(this))
	 */
	public boolean canHaveAsWorld(World world){
		return (world != null) && (! world.hasAsTeam(this));
	}
	
	/**
	 * Method to check whether the given Team already has a world.
	 * @return True if and only if this.world is not null.
	 * 		| return == (getWorld() != null)
	 */
	public boolean hasWorld(){
		return (getWorld() != null);
	}
	
	/**
	 * Method to remove the world of this team and remove this team from the world it belonged to.
	 * @throws NullPointerException
	 * 		This team has no world.
	 * 		| ! hasWorld()
	 */
	public void removeWorld() throws NullPointerException {
		if (! hasWorld())
			throw new NullPointerException();
		World formerWorld = getWorld();
		this.world = null;
		formerWorld.removeAsTeam(this);
	}
	
	/**
	 * Variable registering the world of this team.
	 */
	private World world;

	/**
	 * Method to check whether the team can have the given worm as one of its worms.
	 * @param worm
	 * @return False if the given worm is null.
	 * 			| return == (worm != null) && (! hasAsWorm(worm))
	 */
	public boolean canHaveAsWorm(Worm worm){
		return (worm != null) && (! hasAsWorm(worm));
	}
	
	/**
	 * Method to check if the given worm is already in this team.
	 * @param worm
	 * @return True if and only if the given worm is in worms.
	 * 			| return == worms.contains(worm)
	 */
	public boolean hasAsWorm(Worm worm){
		return worms.contains(worm);
	}
	
	/**
	 * Method to add a given worm to the team.
	 * @param worm
	 * 		 the worm to be added
	 * @post If the worm is valid, this team has the given worm as one of its worms.
	 * 		| worms.contains(worm)
	 * @post If the worm is not valid, no worm will be added.
	 * 		| new.worms == this.worms
	 * @throws IllegalWormException
	 * 		This team cannot have the given worm as one of its worms.
	 * 		| ! canHaveAsWorm(worm)
	 * @throws IllegalStateException
	 * 		The given worm does not have this teams world as its world, or this team already contains the given worm.
	 * 		| (worm.getWorld() != this)
	 * @throws IllegalStateException
	 * 		The given worm does not have this team as its team.
	 * 		| (! worm.getTeam() == this )
	 */
	public void addAsWorm(Worm worm) throws IllegalWormException, IllegalStateException{
		if(! canHaveAsWorm(worm))
			throw new IllegalWormException(worm);
		if (worm.getWorld() != this.getWorld())
			throw new IllegalStateException();
		if (worm.getTeam() != this)
			throw new IllegalStateException();
		worms.add(worm);
	}
	
	/**
	 * Method to remove the given worm from this Team.
	 * @param worm
	 * @throws IllegalWormException
	 * 		The given worm is not effective, or this team does not contain the given worm.
	 * 		| (worm == null) || (! hasAsWorm(worm))
	 * @throws IllegalStateException
	 * 		The given worm still has a team.
	 * 		| worm.hasWorld()
	 */
	public void removeAsWorm(Worm worm) throws IllegalWormException, IllegalStateException {
		if ((worm == null) || (! hasAsWorm(worm)))
			throw new IllegalWormException(worm);
		if (worm.hasTeam())
			throw new IllegalStateException();
		worms.remove(worm);
	}
	
	/**
	 * Method to return a list of the worms in this Team.
	 */
	@Basic
	public List<Worm> getWorms(){
		return this.worms;
	}
	
	/**
	 * Variable registering a list of the worms currently in this team.
	 */
	private final List<Worm> worms = new ArrayList<Worm>();
}
