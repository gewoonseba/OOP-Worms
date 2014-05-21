package worms.model.expressions;

import worms.model.Worm;
import worms.model.types.DoubleType;
import worms.model.types.Entity;

public class GetMaxHPExpression extends OperationExpression {

	public GetMaxHPExpression(Expression entity) {
		super(entity);
	}

	@Override
	public String toString() {
		return ("getMaxHP(" + getEntity().toString() + ")");
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
