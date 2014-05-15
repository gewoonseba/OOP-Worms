package worms.model.expressions;
import worms.model.Food;
import worms.model.Worm;
import worms.model.types.*;

public class GetYExpression extends Expression {
	
	private final Expression worm;
	
	public GetYExpression(Expression worm){
		if (worm instanceof SelfWormExpression)
			this.worm= new EntityExpression<Worm>((new SelfWormExpression()).getValue());
		else
			this.worm = (Expression) worm;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DoubleType getValue() {
		if (((Entity<?>)worm.getValue()).getValue() instanceof Worm)
			return new DoubleType(((Worm)((Entity<?>) worm.getValue()).getValue()).getY());
		return new DoubleType(((Food)((Entity<?>) worm.getValue()).getValue()).getY());
	}

	

}
