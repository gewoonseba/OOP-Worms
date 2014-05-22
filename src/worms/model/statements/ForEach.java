package worms.model.statements;
import worms.model.*;
import worms.model.expressions.SelfWormExpression;
import worms.model.programs.ProgramFactory.ForeachType;
import worms.model.types.*;

public class ForEach extends Statement {
	
	public boolean executed=false;

	
	private final ForeachType type;
	private final String name;
	private final Statement body;

	
	public ForEach(ForeachType type,String name,Statement body) {
		this.type = type;
		this.body = body;
		this.name = name;
	}

	@Override
	public String toString() {
		return ("foreach(" + type.toString() + " " + name + ":" + body.toString() + ")");
	}

	@Override
	public void executeStatement() {
		this.executed=false;
		if (type==ForeachType.WORM){
			Entity<Worm> e=new Entity<Worm>();
			for (Worm w:SelfWormExpression.getWorm().getWorld().getWorms()){
				if (SelfWormExpression.getWorm().getProgram().getstatementCount()>=1000)
					return;
				e.setValue(w);
				SelfWormExpression.getWorm().getProgram().getGlobals().put(name,e );
				if (body instanceof ActionStatement){
					if (!((ActionStatement)body).enoughAp()){
						SelfWormExpression.getWorm().getProgram().stop();
						return;
						}
				}
				body.executeStatement();
			}
		}
		if (type==ForeachType.FOOD){
			Entity<Food> f=new Entity<Food>();
			for (Food w:SelfWormExpression.getWorm().getWorld().getFood()){
				if (SelfWormExpression.getWorm().getProgram().getstatementCount()>=1000)
					return;
				f.setValue(w);
				SelfWormExpression.getWorm().getProgram().getGlobals().put(name, f);
				if (body instanceof ActionStatement){
					if (!((ActionStatement)body).enoughAp()){
						SelfWormExpression.getWorm().getProgram().stop();
						return;
						}
				}
				body.executeStatement();
			}
		}
		if (type==ForeachType.ANY){
			Entity<Food> v=new Entity<Food>();
			for (Food w:SelfWormExpression.getWorm().getWorld().getFood()){
				if (SelfWormExpression.getWorm().getProgram().getstatementCount()>=1000)
					return;
				v.setValue(w);
				SelfWormExpression.getWorm().getProgram().getGlobals().put(name, v);
				if (body instanceof ActionStatement){
					if (!((ActionStatement)body).enoughAp()){
						SelfWormExpression.getWorm().getProgram().stop();
						return;
						}
				}
				body.executeStatement();
			}
			Entity<Worm> a=new Entity<Worm>();
			for (Worm w:SelfWormExpression.getWorm().getWorld().getWorms()){
				if (SelfWormExpression.getWorm().getProgram().getstatementCount()>=1000)
					return;
				a.setValue(w);
				SelfWormExpression.getWorm().getProgram().getGlobals().put(name,a );
				if (body instanceof ActionStatement){
					if (!((ActionStatement)body).enoughAp()){
						SelfWormExpression.getWorm().getProgram().stop();
						return;
						}
				}
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
