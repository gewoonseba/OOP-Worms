package worms.model.expressions;

public class VariableAccesExpression<E> extends Expression<E> {
	
	private final String name;
	
	public VariableAccesExpression(String name){
		this.name = name;
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
	
	public String getVariable(){
		return this.name;
	}


	@Override
	public E getValue() {
		// TODO Object?
		return  (E) SelfWormExpression.getWorm().getProgram().getGlobals().get(name);
	}



	@Override
	public boolean hasAsSubExpression(Expression<E> expression) {
		// TODO Auto-generated method stub
		return false;
	}

}
