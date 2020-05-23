
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.Predicate;

public class StockUtil {

	public static StockInfo getPrice(final String ticker) {
		return new StockInfo(ticker, prices.get(ticker));
	}

	public static Predicate<StockInfo> isPriceLessThan(final double price) {
		return stockInfo -> stockInfo.price < price;
	}

	public static StockInfo pickHigh(final StockInfo stockInfo1, final StockInfo stockInfo2) {
		return stockInfo1.price > stockInfo2.price ? stockInfo1 : stockInfo2;
	}
	
	public static Double priceValue(String stock) throws IOException {
				
		return RealTimeStock.getStockPrice(stock);
		
	}

	static Map<String, Double> prices = new HashMap<String, Double>() {
		{
			try {
				put("AMD", priceValue("AMD"));
				put("HPQ", priceValue("HPQ"));
				put("IBM", priceValue("IBM"));
				put("TXN", priceValue("TXN"));
				put("VMW", priceValue("VMW"));
				put("XRX", priceValue("XRX"));
				put("AAPL", priceValue("AAPL"));
				put("ADBE", priceValue("ADBE"));
				put("AMZN", priceValue("AMZN"));
				put("CRAY", priceValue("CRAY"));
				put("CSCO", priceValue("CSCO"));
				put("SNE", priceValue("SNE"));
				put("GOOG", priceValue("GOOG"));
				put("INTC", priceValue("INTC"));
				put("INTU", priceValue("INTU"));
				put("MSFT", priceValue("MSFT"));
				put("ORCL", priceValue("ORCL"));
//				put("TIBX", priceValue("TIBX")); // not available in any of the two APIs 
				put("VRSN", priceValue("VRSN"));
				put("YHOO", priceValue("YHOO"));
				put("BTC-USD", priceValue("BTC-USD"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	};
}
