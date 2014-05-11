package worms.model.expressions;
import worms.model.types.*;
import worms.model.Worm;

public class SelfWormExpression extends WormExpression {
	
	SelfWormExpression(Worm worm){
		this.worm=worm;
	}
   
	public Worm getWorm() {
		return this.worm;
	}
	
	private final Worm worm;

	@Override
	public Entity<Worm> getValue() {
		Entity<Worm> wormpie =new Entity<Worm>();
	    wormpie.setValue(worm);
		return wormpie;
	}


}
