package worms.model.statements;
import worms.model.*;
import worms.model.expressions.SelfWormExpression;

public class ForEach extends Statement {
	
	public enum ForeachType {
		WORM, FOOD, ANY
	}
	
	public ForeachType type;
	public String name;
	public Statement body;

	
	public ForEach(ForeachType type,String name,Statement body) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void executeStatement() {
		switch (type) {
		case WORM:
			for (Worm w:SelfWormExpression.getWorm().getWorld().getWorms()){
				
			}
			
		}
		

	}

}
