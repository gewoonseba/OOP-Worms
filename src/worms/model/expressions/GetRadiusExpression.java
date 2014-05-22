package worms.model.expressions;

import worms.model.Food;
import worms.model.Worm;
import worms.model.types.DoubleType;

public class GetRadiusExpression extends OperationExpression {

	public GetRadiusExpression(Expression entity) {
		super(entity);
	}

	@Override
	public String toString() {
		return ("getRadius(" + getEntity().toString() +")");
	}

	@Override
	public DoubleType getValue() {
		Object actualEntity = getEntity().getValue().getValue();
		if (actualEntity instanceof Worm)
			return  new DoubleType(((Worm) actualEntity).getRadius());
		return new DoubleType(Food.getRadius());
	}

	@Override
	public GetRadiusExpression clone() {
		return new GetRadiusExpression(getEntity());
	}

}
