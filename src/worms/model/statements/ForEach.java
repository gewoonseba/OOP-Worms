package worms.model.statements;
import worms.model.*;
import worms.model.expressions.SelfWormExpression;
import worms.model.programs.ProgramFactory.ForeachType;
import worms.model.types.*;

public class ForEach extends Statement {

	
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
			Entity<Worm> e=new Entity<Worm>();
			for (Worm w:SelfWormExpression.getWorm().getWorld().getWorms()){
				e.setValue(w);
				SelfWormExpression.getWorm().getProgram().getGlobals().put(name,e );
				body.executeStatement();
			}
		case FOOD:
			Entity<Food> f=new Entity<Food>();
			for (Food w:SelfWormExpression.getWorm().getWorld().getFood()){
				f.setValue(w);
				SelfWormExpression.getWorm().getProgram().getGlobals().put(name, f);
				body.executeStatement();
			}
		case ANY:
			Entity<Food> v=new Entity<Food>();
			for (Food w:SelfWormExpression.getWorm().getWorld().getFood()){
				v.setValue(w);
				SelfWormExpression.getWorm().getProgram().getGlobals().put(name, v);
				body.executeStatement();
			}
			Entity<Worm> a=new Entity<Worm>();
			for (Worm w:SelfWormExpression.getWorm().getWorld().getWorms()){
				a.setValue(w);
				SelfWormExpression.getWorm().getProgram().getGlobals().put(name,a );
				body.executeStatement();
			}
			
			
		}
		

	}

}
