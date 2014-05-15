package worms.model.expressions;

import worms.model.Worm;
import worms.model.types.DoubleType;
import worms.model.types.Entity;

public class GetDirectionExpression extends Expression {

	private final Expression worm;
	
	public GetDirectionExpression(Expression worm) {
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
		Entity<Worm> entity = (Entity<Worm>) worm.getValue();
		return new DoubleType(entity.getValue().getDirection());
	}

}
