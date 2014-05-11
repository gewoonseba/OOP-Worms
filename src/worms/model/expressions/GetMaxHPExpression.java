package worms.model.expressions;

import worms.model.Worm;
import worms.model.types.Entity;

public class GetMaxHPExpression extends Expression<Double> {

    private final Worm worm;
	
	public GetMaxHPExpression(EntityExpression<Worm> worm) {
		this.worm = worm.getValue().getValue();
	}
	
	@Override
	public boolean hasAsSubExpression(Expression<Double> expression) {
		// TODO Auto-generated method stub
		return false;
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

	@Override
	public Double getValue() {
		return (double) worm.getMaxHitPoints();
	}

}
