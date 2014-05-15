package worms.model.expressions;

import worms.model.types.Type;

public abstract class Expression implements Cloneable {

	


	

	/**
	 * Return a textual representation of this expression.
	 *
	 * @return The resulting string is non-empty.
	 *       | result.length() > 0
	 */
	@Override
	public abstract String toString();
	
	public abstract Type getValue();


}