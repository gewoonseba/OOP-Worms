package worms.model.expressions;

import worms.model.Worm;

public class SelfWormExpression extends WormExpression {
	
	SelfWormExpression(Worm worm){
		this.worm=worm;
	}
   
	public Worm getWorm() {
		return this.worm;
	}
	
	private Worm worm;


}
