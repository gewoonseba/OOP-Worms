package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

public class IllegalTeamException extends RuntimeException {
	/**
	 * Initialize this new illegal team exception with the given team.
	 * 
	 * @param team
	 * @post The worm of this new illegal team exception is equal to the given team.
	 *      | new.getTeam() == team
	 * @effect This new illegal team exception is further initialized 
	 *        as a new runtime exception involving no diagnostic message and no cause
	 *        | super()
	 */
	public IllegalTeamException(Team team){
		this.team = team;
	}
	
	/**
	 * Return the team of this illegal team exception.
	 */
	@Basic @Raw @Immutable
	public Team getTeam(){
		return team;
	}
	
	/**
	 * Variable registering the team of this illegal team exception.
	 */
	private final Team team;

}
