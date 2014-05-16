package worms.model.expressions;
import worms.model.Food;
import worms.model.Worm;
import worms.model.types.*;

public class GetYExpression extends Expression {
	
	private Expression entity;
	
	public GetYExpression(Expression worm){
	
		this.entity = (Expression) worm;
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
		if (((Entity<?>)entity.getValue()).getValue() instanceof Worm)
			return new DoubleType(((Worm)((Entity<?>) entity.getValue()).getValue()).getY());
		return new DoubleType(((Food)((Entity<?>) entity.getValue()).getValue()).getY());
	}

	

}
