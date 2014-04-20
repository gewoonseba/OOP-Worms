


import java.math.BigInteger;

import stockMarket.Share;

public class Experiment {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Share appleShare = new Share(new MoneyAmount(BigInteger.TEN,Currency.USD));
		
//		// Experiment 1
//		MoneyAmount applePrice = appleShare.getCurrentPrice();
//		applePrice.setAmountInCents(BigInteger.valueOf(-100));
//		System.out.println(appleShare.getCurrentPrice().getAmountInCents());
//		
//		// Experiment 2
//		MoneyAmount newPrice = new MoneyAmount(BigInteger.valueOf(200),Currency.USD);
//		appleShare.setCurrentPrice(newPrice);
//		newPrice.setCurrency(null);
//		System.out.println(appleShare.getCurrentPrice().getCurrency());

	}

}
