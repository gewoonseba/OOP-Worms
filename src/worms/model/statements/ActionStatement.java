package worms.model.statements;

public abstract class ActionStatement extends Statement {

	@Override
	public abstract String toString();

	
	public abstract void executeStatement();

}
