package worms.model.expressions;

import worms.model.Worm;
import worms.model.types.DoubleType;
import worms.model.types.Entity;

public class GetAPExpression extends OperationExpression {

    public GetAPExpression(Expression entity) {
		super(entity);
	}

	@Override
	public String toString() {
		return ("getAP(" + getEntity().toString() +")");
	}

	@Override
	public DoubleType getValue() {
		return new DoubleType((double) ((Entity<Worm>) getEntity().getValue()).getValue().getCurrentAP());
	}

	@Override
	public GetAPExpression clone() {
		return new GetAPExpression(getEntity());
	}
}
