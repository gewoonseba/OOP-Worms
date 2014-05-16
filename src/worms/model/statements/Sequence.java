package worms.model.statements;

import java.util.List;

public class Sequence extends Statement {
	
	public Sequence(List<Statement> statements){
		this.statements = statements;
	}

	@Override
	public String toString() {
		return "s*";
	}
	
	public void executeStatement() {
		int i = 0;
		while (i <statements.size()){
			statements.get(i).executeStatement();
			i+=1;
		}
	}
	
	private List<Statement> statements;

}
