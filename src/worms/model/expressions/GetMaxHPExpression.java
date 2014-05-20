package worms.model.expressions;

import worms.model.Worm;
import worms.model.types.DoubleType;
import worms.model.types.Entity;

public class GetMaxHPExpression extends OperationExpression {

	public GetMaxHPExpression(Expression entity) {
		super(entity);
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
		return new DoubleType((double) ((Entity<Worm>) getEntity().getValue()).getValue().getMaxHitPoints());
	}

	@Override
	public GetMaxHPExpression clone() {
		return new GetMaxHPExpression(getEntity());
	}

}
