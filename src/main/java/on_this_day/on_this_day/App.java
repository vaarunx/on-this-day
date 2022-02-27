package on_this_day.on_this_day;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class App 
{
	public static void main(String[] args) {
		CustomDate date = new CustomDate();
		date.setDay(27);
		date.setMonth(02);
		int currentDay = date.getDay();
		int currentMonth = date.getMonth();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.wikimedia.org/feed/v1/wikipedia/en/onthisday/all/" + currentMonth + "/" + currentDay)).build();
		client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).thenApply(App::parse).join();	
	}
	
	public static String parse(String responseBody) {
		JSONObject events = new JSONObject(responseBody);
		JSONArray selected = events.getJSONArray("selected");
		ArrayList<Selected> selected_array = new ArrayList<Selected>();
		for(int i = 0; i < selected.length(); i++) {
			 JSONObject each_selected = selected.getJSONObject(i);
			 String jSONString = each_selected.toString();
			 GsonBuilder builder = new GsonBuilder();
			 Gson gson = builder.create();
			 Selected Conv_obj = gson.fromJson(jSONString, Selected.class);
			 selected_array.add(i,Conv_obj);
		}
		// Checking
		for(int i=0; i<selected_array.size();i++) {
			System.out.println(selected_array.get(i).text); //title
			System.out.println(selected_array.get(i).year); //year
			for(int j=0; j<selected_array.get(i).pages.size();j++) {
				System.out.println(selected_array.get(i).pages.get(j).extract);	//extract of each page
				System.out.println(selected_array.get(i).pages.get(j).content_urls.desktop.page); // url
				System.out.println(selected_array.get(i).pages.get(j).thumbnail.source); //image
			}
			break;
		}
		return null;
	}
}