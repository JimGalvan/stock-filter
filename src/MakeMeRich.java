
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MakeMeRich {
	public static final List<String> symbols = Arrays.asList("AMD", "HPQ", "IBM", "TXN", "VMW", "XRX", "AAPL", "ADBE",
			"AMZN", "CRAY", "CSCO", "SNE", "GOOG", "INTC", "INTU", "MSFT", "ORCL", "TIBX", "VRSN", "YHOO");

	public static void main(String[] args) {

		// 1. Print these symbols using a Java 8 for-each and lambdas

		symbols.stream().forEach(System.out::println);

		// 2. Use the StockUtil class to print the price of Bitcoin

		System.out.println(StockUtil.getPrice("BTC-USD"));

		// 3. Create a new List of StockInfo that includes the stock price

		List<String> keyValues = new ArrayList<String>(StockUtil.prices.keySet());

		List<StockInfo> stocks = new ArrayList<StockInfo>();

		keyValues.forEach(item -> stocks.add(StockUtil.getPrice(item)));

		// 4. Find the highest-priced stock under $500

		ArrayList<StockInfo> pricesList = stocks.stream()
				.filter(StockUtil.isPriceLessThan(500))
				.sorted((o1, o2) -> Double.compare(o1.getPrice(), o2.getPrice()))
				.collect(Collectors.toCollection(ArrayList::new));

		StockInfo stockInfo1 = pricesList.get(0);
		StockInfo stockInfo2 = pricesList.get(pricesList.size() - 1);

		StockInfo pickHigh = StockUtil.pickHigh(stockInfo1, stockInfo2);
		System.out.println("Highest prices under 500: " + pickHigh);

	}

}
