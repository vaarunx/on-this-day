package on_this_day.on_this_day;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;

interface TodaysHistoryRetriever {
	   
	public TodaysHistoryInfo get(CustomDate date);
	      
	}

public class implementingClass extends App implements TodaysHistoryRetriever {
	
	public static TodaysHistoryInfo history = new TodaysHistoryInfo();

	//Uncomment the following lines to check the module
//	public static void main(String[] args) {
//		
//		implementingClass m = new implementingClass();
//		CustomDate d = new CustomDate();		
//		d.setDay(12);
//		d.setMonth(3);
//		d.setYear(2022);
//		history = m.get(d);
//		System.out.println(history);
//	}

	//Running the API Asynchronously
	@Override
	public TodaysHistoryInfo get(CustomDate date) {
		TodaysHistoryInfo history = new TodaysHistoryInfo();
		given_date = date;
		int currentDay = date.getDay();
		int currentMonth = date.getMonth();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.wikimedia.org/feed/v1/wikipedia/en/onthisday/all/" + currentMonth + "/" + currentDay)).build();
		client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).thenApply(t -> {
			try {
				return App.parse(t);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return t;
		}).join();	
		return history;

		
	}

}
