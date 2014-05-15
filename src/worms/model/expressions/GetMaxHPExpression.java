package worms.model.expressions;

import worms.model.Worm;
import worms.model.types.DoubleType;
import worms.model.types.Entity;

public class GetMaxHPExpression extends Expression {

	private final Expression worm;
	
	public GetMaxHPExpression(Expression worm) {
		if (worm instanceof SelfWormExpression)
			this.worm= new EntityExpression<Worm>((new SelfWormExpression()).getValue());
		else
			this.worm = worm;
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

	@Override
	public DoubleType getValue() {
		return new DoubleType((double) ((Entity<Worm>) worm.getValue()).getValue().getMaxHitPoints());
	}

}
