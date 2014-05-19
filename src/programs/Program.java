package programs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import worms.gui.game.IActionHandler;
import worms.model.expressions.SelfWormExpression;
import worms.model.statements.*;
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
	
	private boolean inForEach;
	
	public boolean wellFormed(){
		inForEach=false;
		Statement i=statement;
		if (i instanceof Sequence){
			if (!(isWellFormedSequence(((Sequence)i).getStatements())))
				return false;}
		else if (i instanceof ForEach){
			if (!(isWellFormedForEach(((ForEach)i).getBody())))
				return false;}
		else if (i instanceof While)
			if(!isWellFormedWhile(((While)i).getBody()))
				return false;
		else if (i instanceof If)
			if (!isWellFormedIf(((If)i).getStatements()))
				return false;
		return true;
	
	}
	
	public boolean isWellFormedWhile(Statement i){
		if (inForEach && (i instanceof ActionStatement))
			return false;
		else if (i instanceof Sequence){
			if (!(isWellFormedSequence(((Sequence)i).getStatements())))
				return false;}
		else if (i instanceof ForEach){
			if (!(isWellFormedForEach(((ForEach)i).getBody())))
				return false;}
		else if (i instanceof While)
			if(!isWellFormedWhile(((While)i).getBody()))
				return false;
		else if (i instanceof If)
			if (!isWellFormedIf(((If)i).getStatements()))
				return false;
		return true;
		
	}
	
	public boolean isWellFormedSequence(List<Statement> body){
		for (Statement i: body){
			if (inForEach && (i instanceof ActionStatement))
				return false;
			else if (i instanceof Sequence){
				if (!(isWellFormedSequence(((Sequence)i).getStatements())))
					return false;}
			else if (i instanceof ForEach){
				if (!(isWellFormedForEach(((ForEach)i).getBody())))
					return false;}
			else if (i instanceof While)
				if(!isWellFormedWhile(((While)i).getBody()))
					return false;
			else if (i instanceof If)
				if (!isWellFormedIf(((If)i).getStatements()))
					return false;
			}
		return true;
		
	}
	public boolean isWellFormedForEach(Statement i){
		inForEach=true;
		if (i instanceof Sequence){
			boolean a=(isWellFormedSequence(((Sequence)i).getStatements()));
			inForEach=false;
			return a;}
		else if (i instanceof ForEach){
			boolean a=(isWellFormedForEach(((ForEach)i).getBody()));
			inForEach=false;
			return a;}
		else if (i instanceof While){
			boolean a=(isWellFormedWhile(((While)i).getBody()));
			inForEach=false;
			return a;}
		else if (i instanceof If){
			boolean a=(isWellFormedIf(((If)i).getStatements()));
			inForEach=false;
			return a;}
		inForEach= false;
		return true;
	}
	
	public boolean isWellFormedIf(List<Statement> body){
		for (Statement i: body){
			if (inForEach && (i instanceof ActionStatement))
				return false;
			else if (i instanceof Sequence){
				if (!(isWellFormedSequence(((Sequence)i).getStatements())))
					return false;}
			else if (i instanceof ForEach){
				if (!(isWellFormedForEach(((ForEach)i).getBody())))
					return false;}
			else if (i instanceof While)
				if(!isWellFormedWhile(((While)i).getBody()))
					return false;
			else if (i instanceof If)
				if (!isWellFormedIf(((If)i).getStatements()))
					return false;
			}
		return true;
		
	}

}
