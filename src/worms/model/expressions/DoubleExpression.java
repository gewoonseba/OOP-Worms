package worms.model.expressions;

import worms.model.types.DoubleType;


public abstract class DoubleExpression extends Expression{

	
    @Override
	public abstract DoubleType getValue();
}
