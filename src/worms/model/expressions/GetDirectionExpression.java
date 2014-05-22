package worms.model.expressions;

import worms.model.Worm;
import worms.model.types.DoubleType;
import worms.model.types.Entity;

public class GetDirectionExpression extends OperationExpression {

    public GetDirectionExpression(Expression entity) {
		super(entity);
	}

	@Override
	public String toString() {
		return ("getDirection(" + getEntity().toString() + ")");
	}

	@Override
	public DoubleType getValue() {
		return new DoubleType((double) ((Entity<Worm>) getEntity().getValue()).getValue().getDirection());
	}

	@Override
	public GetDirectionExpression clone() {
		return new GetDirectionExpression(getEntity());
	}
}
