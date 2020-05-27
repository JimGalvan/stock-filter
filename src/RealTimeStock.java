import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class RealTimeStock {

	private static final String API_KEY = "4A834PJKASDC1TEP";

	private static Double stockPrice = 0.0; 
	private static int counter = 0;


	@SuppressWarnings("deprecation")
	public static Double getStockPrice(String stock) throws IOException {
		
		// Counter added in order to fix api call time issue
		++counter; 
		
		if (counter == 6) {
			try {
				System.out.println("Wait one minute for the program to load another 5 stock prices");
				TimeUnit.MINUTES.sleep(1);
				counter = 1;
			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("test");
			}
		}
		
		String rootURL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=";
		rootURL += stock + "&outputsize=compact&apikey=" + API_KEY;
		System.out.println(stock);

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

		return stockPrice;
	}
}
