package worms.model.expressions;
import worms.model.Worm;
import worms.model.Team;
import worms.model.types.*;

public class SameTeamExpression extends BooleanExpression {
	
	public SameTeamExpression(Expression entity){
		this.entity= entity;
	}

	@Override
	public BooleanType getValue() {
		if (SelfWormExpression.getWorm().getTeam()== null)
			return new BooleanType(false);
		
		return new BooleanType(((Entity<Worm>) this.entity.getValue()).getValue().getTeam().getName() == SelfWormExpression.getWorm().getTeam().getName());
	}

	@Override
	public boolean equals(Object other) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public final Expression getEntity(){
		return this.entity;
	}

	private final Expression entity;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SameTeamExpression clone() {
		return new SameTeamExpression(getEntity());
	}

}
