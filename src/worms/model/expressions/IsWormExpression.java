package worms.model.expressions;
import worms.model.types.*;
import worms.model.Worm;

public class IsWormExpression<E> extends BooleanExpression {
	
	private final Expression entity;
	
	public IsWormExpression(Expression entity) {
		this.entity = entity;
	}

	@Override
	public BooleanType getValue() {
		System.out.println("huppa");
		if (entity==null || entity.getValue()==null)
			return new BooleanType(false);
		return new BooleanType(entity.getValue().getValue() instanceof Worm);	
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

}
