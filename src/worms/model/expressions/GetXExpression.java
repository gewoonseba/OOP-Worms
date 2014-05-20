package worms.model.expressions;

import worms.model.Food;
import worms.model.Worm;
import worms.model.types.DoubleType;
import worms.model.types.Entity;

public class GetXExpression extends Expression {

	private Expression entity;
	
	public GetXExpression(Expression entity) {
		this.entity = entity;
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
		if (entity instanceof SelfWormExpression){
			this.entity= new EntityExpression<Worm>((new SelfWormExpression()).getValue());}

		if ((entity.getValue().getValue()) instanceof Worm)
			return new DoubleType(((Worm)((Entity<?>) entity.getValue()).getValue()).getX());
		return new DoubleType(((Food)((Entity<?>) entity.getValue()).getValue()).getX());
	}

}
