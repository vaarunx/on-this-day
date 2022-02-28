package on_this_day.on_this_day;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
		JSONArray event = events.getJSONArray("events");
		ArrayList<Event> event_array = new ArrayList<Event>();
		for(int i = 0; i < event.length(); i++) {
			 JSONObject each_event = event.getJSONObject(i);
			 String jSONString = each_event.toString();
			 GsonBuilder builder = new GsonBuilder();
			 Gson gson = builder.create();
			 Event Conv_obj = gson.fromJson(jSONString, Event.class);
			 event_array.add(i,Conv_obj);
		}
		JSONArray holiday = events.getJSONArray("holidays");
		ArrayList<Holiday> holiday_array = new ArrayList<Holiday>();
		for(int i = 0; i < holiday.length(); i++) {
			 JSONObject each_holiday = holiday.getJSONObject(i);
			 String jSONString = each_holiday.toString();
			 GsonBuilder builder = new GsonBuilder();
			 Gson gson = builder.create();
			 Holiday Conv_obj = gson.fromJson(jSONString, Holiday.class);
			 holiday_array.add(i,Conv_obj);
		}
		JSONArray birth = events.getJSONArray("births");
		ArrayList<Birth> birth_array = new ArrayList<Birth>();
		for(int i = 0; i < birth.length(); i++) {
			 JSONObject each_birth = birth.getJSONObject(i);
			 String jSONString = each_birth.toString();
			 GsonBuilder builder = new GsonBuilder();
			 Gson gson = builder.create();
			 Birth Conv_obj = gson.fromJson(jSONString, Birth.class);
			 birth_array.add(i,Conv_obj);
		}
		JSONArray deaths = events.getJSONArray("deaths");
		ArrayList<Death> deaths_array = new ArrayList<Death>();
		for(int i = 0; i < deaths.length(); i++) {
			 JSONObject each_death = deaths.getJSONObject(i);
			 String jSONString = each_death.toString();
			 GsonBuilder builder = new GsonBuilder();
			 Gson gson = builder.create();
			 Death Conv_obj = gson.fromJson(jSONString, Death.class);
			 deaths_array.add(i,Conv_obj);
		}
		// Checking
		for(int i=0; i<event_array.size();i++) {
			System.out.println(event_array.get(i).text); //title
			System.out.println(event_array.get(i).year); //year
			for(int j=0; j<event_array.get(i).pages.size();j++) {
				System.out.println(event_array.get(i).pages.get(j).extract);	//extract of each page
				System.out.println(event_array.get(i).pages.get(j).content_urls.desktop.page); // url
				System.out.println(event_array.get(i).pages.get(j).thumbnail.source); //image
			}
			break;
		}
		return null;
	}
}