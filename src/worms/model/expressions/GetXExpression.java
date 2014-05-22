package worms.model.expressions;

import worms.model.Food;
import worms.model.Worm;
import worms.model.types.DoubleType;
import worms.model.types.Entity;

public class GetXExpression extends OperationExpression {

	public GetXExpression(Expression entity) {
		super(entity);
	}

	@Override
	public String toString() {
		return ("getX(" + getEntity().toString() + ")");
	}

	@Override
	public DoubleType getValue() {
		Expression entity = getEntity();
		if (entity instanceof SelfWormExpression){
			entity= new EntityExpression<Worm>((new SelfWormExpression()).getValue());}
		if ((entity.getValue().getValue()) instanceof Worm)
			return new DoubleType(((Worm)((Entity<?>) entity.getValue()).getValue()).getX());
		return new DoubleType(((Food)((Entity<?>) entity.getValue()).getValue()).getX());
	}

	@Override
	public GetXExpression clone() {
		return new GetXExpression(getEntity());
	}

}
