package worms.model.statements;

import worms.model.expressions.Expression;

public class Print extends Statement {
	
	public Print (Expression<?> print1) {
		this.print1 = print1;
	}

	@Override
	public String toString() {
		return "print" + print1.toString();
	}
	
	public void executeStatement(){
		System.out.println(print1.toString());
	}
	
	private final Expression<?> print1;

}
