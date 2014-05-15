package worms.model.expressions;
import worms.model.Worm;
import worms.model.types.*;

public class GetYExpression extends Expression {
	
	private final EntityExpression<Worm> worm;
	
	public GetYExpression(EntityExpression<Worm> worm){
		this.worm = worm;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DoubleType getValue() {
		return new DoubleType(worm.getValue().getValue().getY());
	}

	

}
