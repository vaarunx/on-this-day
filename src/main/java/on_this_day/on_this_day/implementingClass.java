package on_this_day.on_this_day;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class implementingClass implements TodaysHistoryRetriever {
	public static TodaysHistoryInfo history = new TodaysHistoryInfo();
	
//	public static void main(String[] args) {
//		CustomDate date = new CustomDate();
//		date.setDay(11);
//		date.setMonth(03);
//		int currentDay = date.getDay();
//		int currentMonth = date.getMonth();
//		HttpClient client = HttpClient.newHttpClient();
//		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.wikimedia.org/feed/v1/wikipedia/en/onthisday/all/" + currentMonth + "/" + currentDay)).build();
//		client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).thenApply(App::parse).join();	
//	}

	@Override
	public TodaysHistoryInfo get(CustomDate date) {
		TodaysHistoryInfo history = new TodaysHistoryInfo();

//		CustomDate date = new CustomDate();
//		date.setDay(2);
//		date.setMonth(03);
		int currentDay = date.getDay();
		int currentMonth = date.getMonth();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.wikimedia.org/feed/v1/wikipedia/en/onthisday/all/" + currentMonth + "/" + currentDay)).build();
		client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).thenApply(App::parse).join();	
		return history;

		// TODO Auto-generated method stub
		
	}
	
	

}
