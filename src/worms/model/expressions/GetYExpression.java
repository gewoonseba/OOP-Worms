package worms.model.expressions;

import worms.model.Food;
import worms.model.Worm;
import worms.model.types.DoubleType;
import worms.model.types.Entity;

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
		Expression entity = getEntity();
		if (entity instanceof SelfWormExpression){
			entity= new EntityExpression<Worm>((new SelfWormExpression()).getValue());}
		if ((entity.getValue().getValue()) instanceof Worm)
			return new DoubleType(((Worm)((Entity<?>) entity.getValue()).getValue()).getY());
		return new DoubleType(((Food)((Entity<?>) entity.getValue()).getValue()).getY());
	}

	@Override
	public GetYExpression clone() {
		return new GetYExpression(getEntity());
	}

}
