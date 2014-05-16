package worms.model.expressions;
import worms.model.Worm;
import worms.model.Team;
import worms.model.types.*;

public class SameTeamExpression extends BooleanExpression {
	
	private final Expression entity;
	
	public SameTeamExpression(Expression entity){
		this.entity= entity;
	}

	@Override
	public BooleanType getValue() {
		if (SelfWormExpression.getWorm().getTeam()== null)
			return new BooleanType(false);
		
		return new BooleanType(((Entity<Worm>) this.entity.getValue()).getValue().getTeam() == SelfWormExpression.getWorm().getTeam());
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
