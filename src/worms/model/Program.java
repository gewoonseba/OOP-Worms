package worms.model;

import java.util.ArrayList;
import java.util.List;

public class Program {
	
	public Program() {
		
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

}
