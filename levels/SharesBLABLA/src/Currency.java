
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

import be.kuleuven.cs.som.annotate.*;

/**
 * An enumeration introducing different currencies used to
 * express amounts of money.
 *   In its current form, the class only supports the Euro,
 *   the United States Dollar and the Japanese Yen.
 * 
 * @version 2.0
 * @author  Eric Steegmans
 */
@Value
enum Currency {

	EUR('Û'), USD('$'), JPY('\u00a5');

	/**
	 * Initialize this currency with the given symbol.
	 *
	 * @param  symbol
	 *         The symbol for this new currency.
	 * @post   The symbol for this new currency is equal to the
	 *         given symbol.
	 *       | new.getSymbol() == symbol
	 */
	@Raw
	private Currency(char symbol) {
		this.symbol = symbol;
	}

	/**
	 * Return the symbol for this currency.
	 */
	@Basic
	@Raw
	@Immutable
	public char getSymbol() {
		return this.symbol;
	}

	/**
	 * Variable storing the symbol for this currency.
	 */
	private final char symbol;

	/**
	 * Return the value of 1 unit of this currency in the other
	 * currency.
	 * 
	 * @param  other
	 *         The currency to convert to.
	 * @return The resulting exchange rate is positive.
	 *       | result.signum() == 1
	 * @return If this currency is the same as the other
	 *         currency, BigDecimal.ONE is returned.
	 *       | if (this == other)
	 *       |   then (result == BigDecimal.ONE)
	 * @return If this currency is not the same as the other
	 *         currency, the resulting exchange rate has the
	 *         precision as established by the currency context.
	 *       | if (this != other)
	 *       |   then (result.precision() ==
	 *       |             currencyContext.getPrecision())
	 * @return The resulting exchange rate is the inverse of the
	 *         exchange rate from the other currency to this
	 *         currency.
	 *       |  result.equals
	 *       |     (BigDecimal.ONE.divide
	 *       |       (other.toCurrency(this),currencyContext))
	 * @throws IllegalArgumentException
	 *         The given currency is not effective.
	 *       | (other == null)
	 */
	public BigDecimal toCurrency(Currency other)
			throws IllegalArgumentException {
		if (other == null)
			throw new IllegalArgumentException("Non effective currency!");
		if (exchangeRates[this.ordinal()][other.ordinal()] == null)
			exchangeRates[this.ordinal()][other.ordinal()] = BigDecimal.ONE
					.divide(exchangeRates[other.ordinal()][this.ordinal()],
							currencyContext);
		return exchangeRates[this.ordinal()][other.ordinal()];
	}

	/**
	 * Variable referencing a two-dimensional array registering
	 * exchange rates between currencies. The first level is
	 * indexed by the ordinal number of the currency to convert
	 * from; the ordinal number to convert to is used to index
	 * the second level.
	 */
	private static BigDecimal[][] exchangeRates = new BigDecimal[3][3];

	static {
		// Initialization of the upper part of the exchange table.
		// Other rates are computed and registered the first time
		// they are queried.
		exchangeRates[EUR.ordinal()][EUR.ordinal()] = BigDecimal.ONE;
		exchangeRates[EUR.ordinal()][USD.ordinal()] = new BigDecimal(
				BigInteger.valueOf(141880), 5);
		exchangeRates[EUR.ordinal()][JPY.ordinal()] = new BigDecimal(
				BigInteger.valueOf(136712), 3);
		exchangeRates[USD.ordinal()][USD.ordinal()] = BigDecimal.ONE;
		exchangeRates[USD.ordinal()][JPY.ordinal()] = new BigDecimal(
				BigInteger.valueOf(963577), 4);
		exchangeRates[JPY.ordinal()][JPY.ordinal()] = BigDecimal.ONE;
	}

	/**
	 * Variable referencing the mathematical context used in
	 * currency arithmetic.
	 *   @return The currency context has a precision of 6
	 *           digits.
	 *         | currencyContext.getPrecision() == 6
	 *   @return The currency context uses the rounding mode
	 *           HALF_DOWN.
	 *         | currencyContext.getRoundingMode() ==
	 *         |   RoundingMode.HALF_DOWN
	 */
	public static final MathContext currencyContext = new MathContext(6,
			RoundingMode.HALF_DOWN);

}