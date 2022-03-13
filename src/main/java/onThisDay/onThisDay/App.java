package onThisDay.onThisDay;

import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;


//Encryption Class
class AesEnc {
	private static final String SECRET_KEY = "4B2E8521A6945CD0BE701C7697AB268DC74AE1A581E89C4D21EBD6BD3D575813";
    private static final String SALT = "C056C8725AE0F2A1"; 
    private static final byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    
    //Encrypting
    public String encrypt(String strToEncrypt){
        try {
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey,ivspec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        }
        catch (Exception e) {
            System.out.println("Error while encrypting: "+ e.toString());
        }
        return null;
    }
    //Decrypting
    public String decrypt(String strToDecrypt){
	try {
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e) {
            System.out.println("Error while decrypting: "+ e.toString());
        }
        return null;
    }

}

//Class where we parse the API Response into the POJO class
public class App  
{
	public static TodaysHistoryInfo history = new TodaysHistoryInfo();
//	CustomDate date = new CustomDate();
	public static CustomDate givenDate = new CustomDate();
	
	public TodaysHistoryInfo parse(String responseBody) throws ParseException {
		JSONObject events = new JSONObject(responseBody);
		//System.out.println(responseBody);
		
		//"Selected" are curated set of events that occurred on the given date
		JSONArray selected = events.getJSONArray("selected");
		for(int i = 0; i < selected.length(); i++) {
			 JSONObject each_selected = selected.getJSONObject(i);
			 String jSONString = each_selected.toString();
			 GsonBuilder builder = new GsonBuilder();
			 Gson gson = builder.create();
			 Selected Conv_obj = gson.fromJson(jSONString, Selected.class);
			 history.selected_array.add(i,Conv_obj);
		}
		
		//'Events' are events that occurred on the given date that are not included in another type
		JSONArray event = events.getJSONArray("events");
		for(int i = 0; i < event.length(); i++) {
			 JSONObject each_event = event.getJSONObject(i);
			 String jSONString = each_event.toString();
			 GsonBuilder builder = new GsonBuilder();
			 Gson gson = builder.create();
			 Event Conv_obj = gson.fromJson(jSONString, Event.class);
			 history.events_array.add(i,Conv_obj);
		}
		//'Holidays' are fixed holidays celebrated on the given date
		JSONArray holiday = events.getJSONArray("holidays");
		for(int i = 0; i < holiday.length(); i++) {
			 JSONObject each_holiday = holiday.getJSONObject(i);
			 String jSONString = each_holiday.toString();
			 GsonBuilder builder = new GsonBuilder();
			 Gson gson = builder.create();
			 Holiday Conv_obj = gson.fromJson(jSONString, Holiday.class);
			 history.holidays_array.add(i,Conv_obj);
		}
		
		//'Birth' are notable people born on the given date
		JSONArray birth = events.getJSONArray("births");
		for(int i = 0; i < birth.length(); i++) {
			 JSONObject each_birth = birth.getJSONObject(i);
			 String jSONString = each_birth.toString();
			 GsonBuilder builder = new GsonBuilder();
			 Gson gson = builder.create();
			 Birth Conv_obj = gson.fromJson(jSONString, Birth.class);
			 history.births_array.add(i,Conv_obj);
		}
		
		//'Deaths' are notable people who died on the given date
		JSONArray deaths = events.getJSONArray("deaths");
		for(int i = 0; i < deaths.length(); i++) {
			 JSONObject each_death = deaths.getJSONObject(i);
			 String jSONString = each_death.toString();
			 GsonBuilder builder = new GsonBuilder();
			 Gson gson = builder.create();
			 Death Conv_obj = gson.fromJson(jSONString, Death.class);
			 history.deaths_array.add(i,Conv_obj);
		}
		
		//Epoch conversion
		String pattern = "yyyy-MM-dd";
		String dateStr = "";
		dateStr+=givenDate.getYear();
		dateStr+="-";
		dateStr+=givenDate.getMonth();
		dateStr+="-";
		dateStr+=givenDate.getDay();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = sdf.parse(dateStr);
		long epoch = date.getTime();

		//Database creation
		
		try{
		   Class.forName("org.sqlite.JDBC");
		   String url= "jdbc:sqlite:onThisDay.db";
		   Connection conn= null;
		   conn=DriverManager.getConnection(url);
		   DatabaseMetaData dbm = conn.getMetaData();
	        // check if "onThisDay" table is there
	        ResultSet tables = dbm.getTables(null, null, "history_information", null);
	       if (tables.next()) {
	       // Table exists
		       String sql="INSERT INTO history_information(date,json_info) VALUES(?,?)";
			   PreparedStatement ps=conn.prepareStatement(sql);
			   ps.setLong(1, epoch);
			   ps.setString(2, new AesEnc().encrypt(events.toString()));
			   //ps.setString(2, events.toString());
			   ps.executeUpdate();
			   conn.close();
		}
	       else {
	         // Table does not exist
		         String create="CREATE TABLE history_information(date NUMERIC,json_info TEXT)";
		         String sql="INSERT INTO history_information(date,json_info) VALUES(?,?)";
		         PreparedStatement ps1=conn.prepareStatement(create);
		         ps1.executeUpdate();
		         PreparedStatement ps=conn.prepareStatement(sql);
		         ps.setLong(1, epoch);
				 ps.setString(2, new AesEnc().encrypt(events.toString()));
		         ps.executeUpdate();
		         conn.close();
	        }
		}
		catch(Exception e){
		   System.out.print(e.getMessage());
		}
		
		return history;
	}
}
	