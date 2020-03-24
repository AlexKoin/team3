package weather_APIs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;


public class getDataMethods {
	
	

	//import com.google.json.reflect*;

		
		
		//GET TEMPERATURE FROM ACCUWEATHER (works only with riga/ test method)
		static public ArrayList<String> getTemperetureAccuweater(String town) throws IOException {
			String url= "https://www.accuweather.com/en/lv/" +town.toLowerCase() +"/225780/weather-forecast/225780";  //my url
			Document page = Jsoup.connect(url)																		  //connection + browser + get method		
					.userAgent("Mozilla/5.0")							
					.get();
			//Select elements i need CSS class = "high"
			Elements mytemp = page.getElementsByClass("high");	
			//new array
			ArrayList<String> mytemps = new ArrayList<>();
			//for each loop temperature into ArrayList
			for  (Element e: mytemp) {
				mytemps.add(e.text());
				}
			return mytemps;
		}
		
		
		//GET TEMPERATURE FROM GISMETEO (works only with riga/ test method)
		static public ArrayList<String> getTemperetureGisMeteo(String town) throws IOException {
			String url= "https://www.gismeteo.lv/ru/weather-"+town.toLowerCase()+"-4136/";
			Document page = Jsoup.connect(url)
					.userAgent("Mozilla/5.0")
					.get();
			Elements mytemp = page.getElementsByClass("js_meas_container temperature tab-weather__value");
			ArrayList<String> mytemps = new ArrayList<>();
			
			for  (Element e: mytemp) {
				mytemps.add(e.text());
				}
			return mytemps;
		}
		
		//
		public static Map<String,Object> jsonToMap(String str) {
			Map<String,Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {}.getType() );
			return map;
		}
		
		
		//GET TEMPERATURE FROM WORLDMAP
		static public void getTemperatureOWM () { 
			String api = "542d30a2a9b0fee31f38da18ad05ba41";
			String location = "London,uk";
			
			//http://api.openweathermap.org./data/2.5/weather?g=" + location + "&appid=" + api
			
			
			String urlStr = "https://api.openweathermap.org/data/2.5/weather?q="+location + "&appid=" + api;
			try {
				StringBuilder res = new StringBuilder();
				URL url = new URL(urlStr);
				URLConnection conn = url.openConnection();
				BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line;
				while ((line = rd.readLine()) != null) {
					res.append(line);
				}
				rd.close();
				System.out.println(res);
				
				Map<String, Object> resMap = jsonToMap(res.toString());
				Map<String, Object> mainMap = jsonToMap(resMap.get("main").toString());
				Map<String, Object> windMap = jsonToMap(resMap.get("wind").toString());
				
				System.out.println("Temperature : " + mainMap.get("temp"));
				
			}
			catch (IOException e) {
				System.out.println(e.getMessage());
			}
			
			
			
		}
		
		
		
		public static void main (String[] args) throws IOException {
			//System.out.println("Riga temperatures from GisMEteo  :" + getTemperetureGisMeteo("London"));
			//System.out.println("Riga temperatures from AccuWeather  :" + getTemperetureAccuweater("Riga"));
			getTemperatureOWM();
		}
		
	





	
	

}
