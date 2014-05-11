package worms.model.expressions;

import be.kuleuven.cs.som.annotate.Model;

public class LessThanOrEqual extends BooleanCompareExpression {

	@Model
	public LessThanOrEqual(DoubleExpressions left, DoubleExpressions right) {
		value= left.getValue()<=right.getValue();
		}
		
    private boolean value;
	
	@Override
	public Boolean getValue() {
		return value;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public boolean equals(Object other) {
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getOperatorSymbol() {
		// TODO Auto-generated method stub
		return "<=";
	}

}
