package worms.model.expressions;

import worms.model.Food;
import worms.model.Worm;
import worms.model.types.DoubleType;
import worms.model.types.Entity;

public class GetRadiusExpression extends OperationExpression {

	public GetRadiusExpression(Expression entity) {
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
