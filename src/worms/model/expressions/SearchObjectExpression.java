package worms.model.expressions;

import worms.model.*;
import worms.model.types.Entity;
public class SearchObjectExpression extends Expression {
	
	public SearchObjectExpression(Expression e) {
		this.e =e;
	}

	private final Expression e;
	private Worm nearestWorm;
	private Food nearestFood;
	private Object nearestObject;
	
	private final Expression getE(){
		return this.e;
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
	public Entity<?> getValue() {
		// TODO Eleganter?

		nearestWorm=null;
		nearestObject=null;
		nearestFood=null;
		double angleIncrease=((Double)e.getValue().getValue());
		Worm worm = SelfWormExpression.getWorm();
		for (Worm w:worm.getWorld().getWorms()){
			if (w!=worm){
				double distance = (Math.sqrt(Math.pow((w.getY()-worm.getY()),2)+
						Math.pow((w.getX()-worm.getX()),2)));
				if (distance<=w.getRadius()){
					if (nearestWorm==null)
						nearestWorm = w;
					else if (distance<(Math.sqrt(Math.pow((nearestWorm.getY()-worm.getY()),2)+
							Math.pow((nearestWorm.getX()-worm.getX()),2))))
						nearestWorm=w;
					continue;}
				double direction = worm.getDirection();
				while (direction+angleIncrease<0)
					direction+=2*Math.PI;
				double alfa = Math.asin((w.getY()-worm.getY())/distance);
				if (((w.getX()>worm.getX()&&w.getY()>worm.getY())))
					alfa = alfa;
				else if (((w.getX()<worm.getX()&&w.getY()>worm.getY())))
					alfa = Math.PI-alfa;
				else if (((w.getX()<worm.getX()&&w.getY()<worm.getY())))
					alfa = Math.PI-alfa;
				else if ((w.getX()>worm.getX()&&w.getY()<worm.getY()))
					alfa = 2*Math.PI+alfa;
				if ((alfa+Math.abs(Math.asin(w.getRadius()/distance))>=(direction+angleIncrease))
						&&(alfa-Math.abs(Math.asin(w.getRadius()/distance))<=(direction+angleIncrease))){
					if (nearestWorm==null)
						nearestWorm = w;
					else if (distance<(Math.sqrt(Math.pow((nearestWorm.getY()-worm.getY()),2)+
							Math.pow((nearestWorm.getX()-worm.getX()),2))))
						nearestWorm=w;}
			}
		}
		for (Food w:worm.getWorld().getFood()){
			double distance = (Math.sqrt(Math.pow((w.getY()-worm.getY()),2)+
					Math.pow((w.getX()-worm.getX()),2)));
			if (distance<=Food.getRadius()){
				if (nearestFood==null)
					nearestFood = w;
				else if (distance<(Math.sqrt(Math.pow((nearestFood.getY()-worm.getY()),2)+
						Math.pow((nearestWorm.getX()-worm.getX()),2))))
					nearestFood=w;
				continue;}
			double direction = worm.getDirection();
			while (direction+angleIncrease<0)
				direction+=2*Math.PI;
			double alfa = Math.asin((w.getY()-worm.getY())/distance);
			if (((w.getX()>worm.getX()&&w.getY()>worm.getY())))
				alfa = alfa;
			else if (((w.getX()<worm.getX()&&w.getY()>worm.getY())))
				alfa = Math.PI-alfa;
			else if (((w.getX()<worm.getX()&&w.getY()<worm.getY())))
				alfa = Math.PI-alfa;
			else if ((w.getX()>worm.getX()&&w.getY()<worm.getY()))
				alfa = 2*Math.PI+alfa;
			if ((alfa+Math.abs(Math.asin(Food.getRadius()/distance))>=(direction+angleIncrease))
					&&(alfa-Math.abs(Math.asin(Food.getRadius()/distance))<=(direction+angleIncrease))){
				if (nearestFood==null)
					nearestFood = w;
				else if (distance<(Math.sqrt(Math.pow((nearestFood.getY()-worm.getY()),2)+
						Math.pow((nearestFood.getX()-worm.getX()),2))))
					nearestFood=w;}
		}
		if (nearestWorm == null && nearestFood == null)
			return null;
		if (nearestWorm == null)
			return new Entity<>(nearestFood);
		
		if (nearestFood == null)
			return new Entity<>(nearestWorm);	
		nearestObject = nearestWorm;
		if (Math.sqrt(Math.pow((nearestFood.getY()-worm.getY()),2)+
				Math.pow((nearestFood.getX()-worm.getX()),2))<
				(Math.sqrt(Math.pow((nearestWorm.getY()-worm.getY()),2)+
						Math.pow((nearestWorm.getX()-worm.getX()),2))))
			nearestObject= nearestFood;
		return new Entity<>(nearestObject);
	}

	@Override
	public SearchObjectExpression clone() {
		return new SearchObjectExpression(getE());
	}




	

}
