import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class RealTimeStock {

	private static final String API_KEY_OPTIONAL = "4A834PJKASDC1TEP";
	private static final String API_KEY = "4fc3e808176890c29f14ad43b2c283bc";

	private static Double stockPrice = 0.0; 

	/* This optional API method was made in order to get BTC-USD, VRSN and YHOO 
	   stock prices that are not available in the default API */
	
	public static void optionalStockAPI(String stock) throws IOException {

		String rootURL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=";
		rootURL += stock + "&outputsize=compact&apikey=" + API_KEY_OPTIONAL;

		URL request = new URL(rootURL);
		InputStream openStream = request.openStream();
		String response = IOUtils.toString(openStream);

		JSONObject root = new JSONObject(response);
		JSONObject metaData = (JSONObject) root.get("Meta Data");
		
		// Get the last refreshed date to get the most updated prices
		String lastDate = (String) metaData.get("3. Last Refreshed");

		JSONObject dailyData = (JSONObject) root.get("Time Series (Daily)");
		JSONObject dataByLastDate = (JSONObject) dailyData.get(lastDate);

		// Cast object to string in order to get the data
		String priceClose = (String) dataByLastDate.get("1. open");

		stockPrice = Double.valueOf(priceClose);

	}

	@SuppressWarnings("deprecation")
	public static Double getStockPrice(String stock) throws IOException {

		String rootURL = "https://financialmodelingprep.com/api/v3/stock/real-time-price/";
		rootURL += stock + "?apikey=" + API_KEY;

		URL request = new URL(rootURL);
		InputStream openStream = request.openStream();
		String response = IOUtils.toString(openStream);

		JSONObject root = new JSONObject(response);

		try {
			stockPrice = (Double) root.get("price");
		} catch (JSONException e) {
			optionalStockAPI(stock);
		}

		return stockPrice;
	}
}
