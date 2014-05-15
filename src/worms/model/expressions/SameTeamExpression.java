package worms.model.expressions;
import worms.model.Worm;
import worms.model.Team;
import worms.model.types.*;

public class SameTeamExpression extends BooleanExpression {
	
	private final Team team;
	
	public SameTeamExpression(Expression entity){
		team= ((Entity<Worm>) entity.getValue()).getValue().getTeam();
	}

	@Override
	public BooleanType getValue() {
		return new BooleanType(team == SelfWormExpression.getWorm().getTeam());
	}

	

	@Override
	public boolean equals(Object other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
