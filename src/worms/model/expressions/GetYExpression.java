package worms.model.expressions;

import worms.model.Food;
import worms.model.Worm;
import worms.model.types.DoubleType;

public class GetYExpression extends OperationExpression {

	public GetYExpression(Expression entity) {
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
			return  new DoubleType(((Worm) actualEntity).getY());
		return new DoubleType(((Food) actualEntity).getY());
	}

	@Override
	public GetYExpression clone() {
		return new GetYExpression(getEntity());
	}

}
