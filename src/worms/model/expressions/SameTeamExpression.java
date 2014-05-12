package worms.model.expressions;
import worms.model.Worm;
import worms.model.Team;

public class SameTeamExpression extends BooleanExpressions {
	
	private final Team team;
	
	public SameTeamExpression(EntityExpression<Worm> entity){
		team= entity.getValue().getValue().getTeam();
	}

	@Override
	public Boolean getValue() {
		return team == SelfWormExpression.getWorm().getTeam();
	}

	@Override
	public boolean isMutable() {
		// TODO Auto-generated method stub
		return false;
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
