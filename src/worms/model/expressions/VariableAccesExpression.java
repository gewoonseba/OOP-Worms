package worms.model.expressions;

public class VariableAccesExpression extends Expression {
	
	private String name;
	
	public VariableAccesExpression(String name){
		this.name = name;
	}

	@Override
	public boolean hasAsSubExpression(Expression expression) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isMutable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean equals(Object other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public double getVariable(){
		return this.name;
	}

}
