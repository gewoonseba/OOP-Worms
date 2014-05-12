package worms.model.expressions;
import worms.model.*;
import worms.model.types.Entity;
public class SearchObjectExpression extends Expression<Object> {
	
	private final double angleIncrease;
	private Worm nearestWorm;
	private Food nearestFood;
	private Object nearestObject;
	
    public SearchObjectExpression(DoubleExpression e) {
    	this.angleIncrease =e.getValue();
	}

	

	@Override
	public boolean isMutable() {
		// TODO Auto-generated method stub
		return false;
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
	public Object getValue() {
		// TODO Eleganter?
		for (Worm w:SelfWormExpression.getWorm().getWorld().getWorms()){
			if (w!=SelfWormExpression.getWorm()){
				if (((w.getY()-SelfWormExpression.getWorm().getY())
						/(w.getY()-SelfWormExpression.getWorm().getY())==
						Math.tan(SelfWormExpression.getWorm().getDirection()+angleIncrease))){
					if (nearestWorm==null)
						nearestWorm = w;
					else if (Math.sqrt(Math.pow((w.getY()-SelfWormExpression.getWorm().getY()),2)+
							Math.pow((w.getX()-SelfWormExpression.getWorm().getX()),2))<
							(Math.sqrt(Math.pow((nearestWorm.getY()-SelfWormExpression.getWorm().getY()),2)+
									Math.pow((nearestWorm.getX()-SelfWormExpression.getWorm().getX()),2))))
						nearestWorm=w;
				}
			}
		}
		for (Food w:SelfWormExpression.getWorm().getWorld().getFood()){
			if (((w.getY()-SelfWormExpression.getWorm().getY())
					/(w.getY()-SelfWormExpression.getWorm().getY())==
					Math.tan(SelfWormExpression.getWorm().getDirection()+angleIncrease))){
				if (nearestFood==null)
					nearestFood = w;
				else if (Math.sqrt(Math.pow((w.getY()-SelfWormExpression.getWorm().getY()),2)+
						Math.pow((w.getX()-SelfWormExpression.getWorm().getX()),2))<
						(Math.sqrt(Math.pow((nearestFood.getY()-SelfWormExpression.getWorm().getY()),2)+
								Math.pow((nearestFood.getX()-SelfWormExpression.getWorm().getX()),2))))
						nearestFood=w;
			}
		}
		nearestObject = nearestWorm;
		if (Math.sqrt(Math.pow((nearestFood.getY()-SelfWormExpression.getWorm().getY()),2)+
				Math.pow((nearestFood.getX()-SelfWormExpression.getWorm().getX()),2))<
				(Math.sqrt(Math.pow((nearestWorm.getY()-SelfWormExpression.getWorm().getY()),2)+
						Math.pow((nearestWorm.getX()-SelfWormExpression.getWorm().getX()),2))))
			nearestObject= nearestFood;
		return nearestObject;
	}



	@Override
	public boolean hasAsSubExpression(Expression<Object> expression) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
