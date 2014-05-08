package worms.model.expressions;

import worms.model.Worm;

public class WormEntityExpression extends WormExpression {
	
	private Worm worm;
	
	public WormEntityExpression(Worm worm){
		this.worm = worm;
	}
	
	public Worm getWorm(){
		return this.worm;
	}

}
