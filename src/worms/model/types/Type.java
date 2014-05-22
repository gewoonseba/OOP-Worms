package worms.model.types;

public abstract class Type {
	
	public abstract boolean equals(Type first);
	
	public abstract Object getValue();

    public abstract Type clone();

}
