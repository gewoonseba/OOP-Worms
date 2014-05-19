package worms.model.statements;

public abstract class ActionStatement extends Statement {

	@Override
	public abstract String toString();

	
	public abstract void executeStatement();
	
	public abstract boolean enoughAp();

}
