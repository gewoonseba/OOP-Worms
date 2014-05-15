package worms.model.types;

public class DoubleType extends Type{
	public DoubleType(){
		this.setValue(0);
	}
	public DoubleType(double value){
		setValue(value);
	}
	
	@Override
	public Double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	private double value;
	
	@Override
	public boolean equals(Type first) {
		if (! (first instanceof DoubleType))
			return false;
		if (! (this.getValue() == ((DoubleType) first).getValue()))
			return false;
		return true;
	}
}

