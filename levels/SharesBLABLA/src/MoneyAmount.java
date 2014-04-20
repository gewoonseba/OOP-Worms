
import java.math.BigInteger;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of money amounts involving an amount in cents and
 * a currency.
 * 
 * @invar  The amount in cents of each money amount must be a valid amount in cents for any
 *         money amount.
 *         | isValidAmountInCents(getAmountInCents())
 * @invar  The currency of each money amount must be a valid currency for any
 *         money amount.
 *         | isValidCurrency(getCurrency())
 *
 * @author  ...
 * @version 1.0
 */
public class MoneyAmount {

	/**
	 * Initialize this new money amount with given amount in cents
	 * and given currency.
	 * 
	 * @param  amountInCents
	 *         The amount in cents for this new money amount.
	 * @param  currency
	 *         The currency for this new money amount.
	 * @effect The amount in cents of this new money amount is set to the given amount in cents.
	 *         | setAmountInCents(amountInCents)
	 * @effect The currency of this new money amount is set to the given currency.
	 *         | setCurrency(currency)
	 */
	@Raw
	public MoneyAmount(BigInteger amountInCents, Currency currency)
			throws IllegalArgumentException {
		setAmountInCents(amountInCents);
		setCurrency(currency);
	}
	
	/**
	 * Variable referencing a money amount of 0 EUR.
	 * @return	...
	 * 			| ...
	 */
	public static final MoneyAmount ZERO = new MoneyAmount(BigInteger.ZERO,Currency.EUR);

	/**
	 * Return the amount in cents of this money amount.
	 */
	@Basic
	@Raw
	public BigInteger getAmountInCents() {
		return this.amountInCents;
	}

	/**
	 * Check whether the given amount in cents is a valid amount in cents for
	 * any money amount.
	 *  
	 * @param  amountInCents
	 *         The amount in cents to check.
	 * @return ...
	 *         | result == ...  
	 */
	public static boolean isValidAmountInCents(BigInteger amountInCents) {
		return true;
	}

	/**
	 * Set the amount in cents of this money amount to the given amount in cents.
	 * 
	 * @param  amountInCents
	 *         The new amount in cents for this money amount.
	 * @post   The amount in cents of this money amount is equal to the given
	 *         amount in cents.
	 *         | new.getAmountInCents() == amountInCents
	 * @throws IllegalArgumentException
	 *         The given amount in cents is not a valid amount in cents for any
	 *         money amount.
	 *         | ! isValidAmountInCents(amountInCents)
	 */
	@Raw
	public void setAmountInCents(BigInteger amountInCents)
			throws IllegalArgumentException {
		if (!isValidAmountInCents(amountInCents))
			throw new IllegalArgumentException();
		this.amountInCents = amountInCents;
	}

	/**
	 * Variable registering the amount in cents of this money amount.
	 */
	private BigInteger amountInCents;

	/**
	 * Return the currency of this money amount.
	 */
	@Basic
	@Raw
	public Currency getCurrency() {
		return this.currency;
	}

	/**
	 * Check whether the given currency is a valid currency for
	 * any money amount.
	 *  
	 * @param  currency
	 *         The currency to check.
	 * @return ...
	 *         | result == ...  
	 */
	public static boolean isValidCurrency(Currency currency) {
		return true;
	}

	/**
	 * Set the currency of this money amount to the given currency.
	 * 
	 * @param  currency
	 *         The new currency for this money amount.
	 * @post   The currency of this money amount is equal to the given
	 *         currency.
	 *         | new.getCurrency() == currency
	 * @throws IllegalArgumentException
	 *         The given currency is not a valid currency for any
	 *         money amount.
	 *         | ! isValidCurrency(currency)
	 */
	@Raw
	public void setCurrency(Currency currency) throws IllegalArgumentException {
		if (!isValidCurrency(currency))
			throw new IllegalArgumentException();
		this.currency = currency;
	}

	/**
	 * Variable registering the currency of this money amount.
	 */
	private Currency currency;

}
