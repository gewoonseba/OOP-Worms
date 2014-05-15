package programs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import worms.gui.game.IActionHandler;
import worms.model.expressions.SelfWormExpression;
import worms.model.statements.Statement;
import worms.model.types.Type;
import worms.model.IllegalWormException;
import worms.model.Worm;

public class Program {
	
	public Program(String programText, IActionHandler handler, Map<String, Type> globals,Statement statement) {
		this.handler = handler;
		this.programText = programText;
		this.globals = globals;
		this.statement=statement;
	}
	
	public void addAsWorm(Worm worm) {
		if (! canHaveAsWorm(worm))
			throw new IllegalWormException(worm);
		if (worm.getProgram() != this)
			throw new IllegalStateException();
		worms.add(worm);
	}
	
	public boolean canHaveAsWorm(Worm worm){
		return (worm != null) && (! hasAsWorm(worm));
	}
	
	public boolean hasAsWorm(Worm worm){
		return worms.contains(worm);
	}
	
	public void removeAsWorm(Worm worm) throws IllegalWormException, IllegalStateException {
		if ((worm == null) || (! hasAsWorm(worm)))
			throw new IllegalWormException(worm);
		if (worm.hasProgram())
			throw new IllegalStateException();
		worms.remove(worm);
	}
	
	private final List<Worm> worms = new ArrayList<Worm>();
	
	private final String programText;
	
	public IActionHandler getHandler(){
		return this.handler;
	}
	
	private final IActionHandler handler;
	
	public Map<String, Type> getGlobals() {
		return globals;
	}
	
	private final Map<String, Type> globals;
	
	private final Statement statement;
	
	public void executeProgram(){
		statement.executeStatement();
	}

}
