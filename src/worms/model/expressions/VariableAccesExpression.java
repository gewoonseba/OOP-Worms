package worms.model.expressions;


import worms.model.types.Type;


public class VariableAccesExpression extends Expression {
	
	public VariableAccesExpression(String name){
		this.name = name;
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
	
	public String getVariable(){
		return this.name;
	}


	@Override
	public Type getValue() {
		// TODO Object?
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
