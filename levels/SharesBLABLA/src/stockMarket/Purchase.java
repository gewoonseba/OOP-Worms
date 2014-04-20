package stockMarket;


import MoneyAmount;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of purchases involving ...
 * 
 * @invar  Each purchase can have its number of items as number of items.
 *         | canHaveAsNbItems(getNbItems())
 * @invar  The highest price of each purchase must be a valid highest price for any
 *         purchase.
 *         | isValidHighestPrice(getHighestPrice())
 *
 * @author  ...
 * @version 1.0
 */
public class Purchase {

	/**
	 * Initialize this new purchase with given number of items and
	 * given highest price.
	 * 
	 * @param  nbItems
	 *         The number of items for this new purchase.
	 * @param  highestPrice
	 *         The highest price for this new purchase.
	 * @effect The number of items of this new purchase is set to the given number of items.
	 *         | setNbItems(nbItems)
	 * @effect The highest price of this new purchase is set to the given highest price.
	 *         | setHighestPrice(highestPrice)
	 */
	@Raw
	public Purchase(int nbItems, MoneyAmount highestPrice)
			throws IllegalArgumentException {
		setNbItems(nbItems);
		setHighestPrice(highestPrice);
	}

	/**
	 * Return a boolean indicating whether or not this purchase
	 * is terminated.
	 */
	@Basic
	@Raw
	public boolean isTerminated() {
		return this.isTerminated;
	}

	/**
	 * Terminate this purchase.
	 *
	 * @post    This purchase is terminated.
	 *          | new.isTerminated()
	 */
	public void terminate() {
		// To be completed.
		this.isTerminated = true;
	}

	/**
	 * Variable registering whether this person is terminated.
	 */
	private boolean isTerminated;

	/**
	 * Return the number of items of this purchase.
	 */
	@Basic
	@Raw
	public int getNbItems() {
		return this.nbItems;
	}

	/**
	 * Check whether this purchase can have the given number of items
	 * as its number of items.
	 *  
	 * @param  nbItems
	 *         The number of items to check.
	 * @return ...
	 *         | result == ...  
	 */
	@Raw
	public boolean canHaveAsNbItems(int nbItems) {
		return true;
	}

	/**
	 * Set the number of items of this purchase to the given number of items.
	 * 
	 * @param  nbItems
	 *         The new number of items for this purchase.
	 * @pre    This purchase can have the given number of items as its number of items.
	 *         | canHaveAsNbItems(nbItems)
	 * @post   The number of items of this purchase is equal to the given
	 *         number of items.
	 *         | new.getNbItems() == nbItems
	 */
	@Raw
	public void setNbItems(int nbItems) {
		assert canHaveAsNbItems(nbItems);
		this.nbItems = nbItems;
	}

	/**
	 * Variable registering the number of items of this purchase.
	 */
	private int nbItems;

	/**
	 * Return the highest price of this purchase.
	 */
	@Basic
	@Raw
	public MoneyAmount getHighestPrice() {
		return this.highestPrice;
	}

	/**
	 * Check whether the given highest price is a valid highest price for
	 * any purchase.
	 *  
	 * @param  highestPrice
	 *         The highest price to check.
	 * @return ...
	 *         | result == ...  
	 */
	public static boolean isValidHighestPrice(MoneyAmount highestPrice) {
		return true;
	}

	/**
	 * Set the highest price of this purchase to the given highest price.
	 * 
	 * @param  highestPrice
	 *         The new highest price for this purchase.
	 * @post   The highest price of this purchase is equal to the given
	 *         highest price.
	 *         | new.getHighestPrice() == highestPrice
	 * @throws IllegalArgumentException
	 *         The given highest price is not a valid highest price for any
	 *         purchase.
	 *         | ! isValidHighestPrice(highestPrice)
	 */
	@Raw
	public void setHighestPrice(MoneyAmount highestPrice)
			throws IllegalArgumentException {
		if (!isValidHighestPrice(highestPrice))
			throw new IllegalArgumentException();
		this.highestPrice = highestPrice;
	}

	/**
	 * Variable registering the highest price of this purchase.
	 */
	private MoneyAmount highestPrice;
    
	public Share getShare() {
		return this.share;
	}
	
	public boolean canHaveAsShare(Share share){
		return true;
	}
	
	public void sethare(Share share){
		assert canHaveAsShare(share);
		
	}
}   
