package worms.model.types;

public class BooleanType extends Type {
	public BooleanType(Boolean value){
		setValue(value);
	}
	
	public BooleanType(){
		
	}
	
	@Override
	public Boolean getValue() {
		return value;
	}

	public void setValue(Boolean value) {
		this.value = value;
	}

	private Boolean value;

	@Override
	public boolean equals(Type first) {
		if (! (first instanceof BooleanType))
			return false;
		if (! (this.getValue() == ((BooleanType) first).getValue()))
			return false;
		return true;
	}
	
	public BooleanType clone(){
		return new BooleanType(getValue());
	}
	

}
