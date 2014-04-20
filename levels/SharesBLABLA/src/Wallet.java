

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of wallets involving ...
 *
 * @author  ...
 * @version 1.0
 */
public class Wallet {
	
	/**
	 * Initialize this new wallet.
	 */
	public Wallet() {
		
	}
	
	/**
	 * Return a boolean indicating whether or not this wallet
	 * is terminated.
	 */
	@Basic
	@Raw
	public boolean isTerminated() {
		return this.isTerminated;
	}

	/**
	 * Terminate this wallet.
	 *
	 * @post    This wallet is terminated.
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

}
