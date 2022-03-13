package onThisDay.onThisDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;

interface TodaysHistoryRetriever {
	   
	public TodaysHistoryInfo get(CustomDate date) throws MalformedURLException, IOException, ParseException;
	      
	}

public class Main extends App implements TodaysHistoryRetriever {
	
	public static TodaysHistoryInfo history = new TodaysHistoryInfo();

	//Uncomment the following lines to check the module
	public static void main(String[] args) throws MalformedURLException, IOException, ParseException {
		
		Main m = new Main();
		CustomDate d = new CustomDate();		
		d.setDay(12);
		d.setMonth(3);
		d.setYear(2022);
		history = m.get(d);
		for(int i = 0 ; i < history.selected_array.size(); i++) {
		System.out.println(history.selected_array.get(i).text);
	}
	}

	//Calling the Java API
	@Override
	public TodaysHistoryInfo get(CustomDate date) throws MalformedURLException , IOException, ParseException{
		TodaysHistoryInfo history = new TodaysHistoryInfo();
		givenDate = date;
		int currentDay = date.getDay();
		int currentMonth = date.getMonth();
		
		//Calling the API
		URL url = new URL("https://api.wikimedia.org/feed/v1/wikipedia/en/onthisday/all/" + currentMonth + "/"  + currentDay);
		HttpURLConnection huc= (HttpURLConnection) url.openConnection();
        huc.setRequestMethod("GET"); //Setting the REQUEST method
        huc.setRequestProperty("Accept","application/json");
        if(huc.getResponseCode() != 200){
           throw new RuntimeException("Request Failed with code : "+huc.getResponseCode()); //Checking the RESPONSE
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(huc.getInputStream()));
        String json_data;
        String responseBody = "";
        while ((json_data = bufferedReader.readLine()) != null){
        	responseBody +=json_data ; //Adding the API Response to a string to pass to the Parse function
        }
        App parse = new App(); // Creating an object of the class Parse
        history = parse.parse(responseBody); //Calling the parse method
        huc.disconnect();

		return history;

		
	}

}
