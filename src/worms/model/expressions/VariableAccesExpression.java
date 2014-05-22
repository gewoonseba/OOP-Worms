package worms.model.expressions;


import worms.model.types.Type;


public class VariableAccesExpression extends Expression {
	
	public VariableAccesExpression(String name){
		this.name = name;
	}

	@Override
	public String toString() {
		return ("variableAcces(" + getName() +")");
	}
	
	public String getVariable(){
		return this.name;
	}


	@Override
	public Type getValue() {
		return SelfWormExpression.getWorm().getProgram().getGlobals().get(name);
	}
	
	public final String getName(){
		return this.name;
	}

	private final String name;

	@Override
	public VariableAccesExpression clone() {
		return new VariableAccesExpression(getName());
	}


}
