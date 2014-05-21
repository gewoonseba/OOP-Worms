package worms.model.expressions;
import worms.model.types.*;
import worms.model.Worm;

public class SelfWormExpression extends Expression {

	public SelfWormExpression(){
		
	}

	public SelfWormExpression(Worm worm){
		setWorm(worm);
	}
   
	public static Worm getWorm() {
		return worm;
	}
	
	private static Worm worm;

	@Override
	public Entity<Worm> getValue() {
		Entity<Worm> wormpie =new Entity<Worm>();
	    wormpie.setValue(getWorm());
		return wormpie;
	}

	public static void setWorm(Worm worm) {
		SelfWormExpression.worm = worm;
	}

	@Override
	public Expression clone() {
		return new SelfWormExpression(getWorm());
	}

	@Override
	public String toString() {
		return "self";
	}
	



}
