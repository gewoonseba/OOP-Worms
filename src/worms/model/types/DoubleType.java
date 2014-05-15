package worms.model.types;

public class DoubleType extends Type{
	public DoubleType(){
	}
	public DoubleType(double value){
		setValue(value);
	}
	
	public double getValue() {
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

