package worms.model.statements;
import worms.model.*;
import worms.model.expressions.SelfWormExpression;
import worms.model.programs.ProgramFactory.ForeachType;
import worms.model.types.*;

public class ForEach extends Statement {
	
	public boolean executed=false;

	
	public ForeachType type;
	public String name;
	private Statement body;

	
	public ForEach(ForeachType type,String name,Statement body) {
		this.type = type;
		this.body = body;
		this.name = name;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void executeStatement() {
		this.executed=false;
		switch (type) {
		case WORM:
			Entity<Worm> e=new Entity<Worm>();
			for (Worm w:SelfWormExpression.getWorm().getWorld().getWorms()){
				if (SelfWormExpression.getWorm().getProgram().getstatementCount()>=1000)
					break;
				e.setValue(w);
				SelfWormExpression.getWorm().getProgram().getGlobals().put(name,e );
				body.executeStatement();
			}
		case FOOD:
			Entity<Food> f=new Entity<Food>();
			for (Food w:SelfWormExpression.getWorm().getWorld().getFood()){
				if (SelfWormExpression.getWorm().getProgram().getstatementCount()>=1000)
					break;
				f.setValue(w);
				SelfWormExpression.getWorm().getProgram().getGlobals().put(name, f);
				body.executeStatement();
			}
		case ANY:
			Entity<Food> v=new Entity<Food>();
			for (Food w:SelfWormExpression.getWorm().getWorld().getFood()){
				if (SelfWormExpression.getWorm().getProgram().getstatementCount()>=1000)
					break;
				v.setValue(w);
				SelfWormExpression.getWorm().getProgram().getGlobals().put(name, v);
				body.executeStatement();
			}
			Entity<Worm> a=new Entity<Worm>();
			for (Worm w:SelfWormExpression.getWorm().getWorld().getWorms()){
				if (SelfWormExpression.getWorm().getProgram().getstatementCount()>=1000)
					break;
				a.setValue(w);
				SelfWormExpression.getWorm().getProgram().getGlobals().put(name,a );
				body.executeStatement();
			}
			
			
		}
		SelfWormExpression.getWorm().getProgram().increaseCount();
		this.executed=true;

	}
	
	@Override
	public boolean isexecuted() {
		return this.executed;
	}
	@Override
	public void setExecuted(boolean bool) {
		this.executed=bool;
		
	}
	
	public Statement getBody(){
		return this.body;
	}
	
}
