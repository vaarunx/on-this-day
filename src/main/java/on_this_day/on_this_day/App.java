package on_this_day.on_this_day;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import org.json.JSONArray;
import org.json.JSONObject;
 	

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) {
		LocalDate todayDate = LocalDate.now();
		int currentDay = todayDate.getDayOfMonth();
		int currentMonth = todayDate.getMonthValue();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.wikimedia.org/feed/v1/wikipedia/en/onthisday/all/" + currentMonth + "/" + currentDay)).build();
		//For just printing
		//client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).thenAccept(System.out::println).join();
		//For converting to JSON
		client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).thenApply(App::parse).join();
		
	}
	
	public static String parse(String responseBody) {
		JSONObject events = new JSONObject(responseBody);
		//System.out.println(events);
		JSONArray selected = events.getJSONArray("selected");
		//System.out.println(selected);

		for(int i = 0; i < selected.length(); i++) {
			 JSONObject each_selected = selected.getJSONObject(i);
			 
			 JSONArray pages = each_selected.getJSONArray("pages");
			 System.out.println(pages);

			 //int id = event.getInt("id");
			 
			// int userID = event.getInt("userId");
			// String title = event.getString("text");
			// System.out.println(title);
		}
		return null;
	}
}
