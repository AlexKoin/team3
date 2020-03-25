package team3.weatherapis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public interface WeatherAPI {

	/**
	 * @return object of type Weather
	 */
	public Weather getWeather();

	public static Map<String, Object> jsonToMap(String str) {
		Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {
		}.getType());

		return map;
	}

	public static float kelvinToCelsius(float kelvin) {
		return kelvin - 273.15f;
	}

	public static float kelvinToCelsius(String kelvin) {
		return Float.parseFloat(kelvin) - 273.15f;
	}

	public static String readFromUrl(String urlString) {
		String response = null;

		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			

			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder stringBuilder = new StringBuilder();

			String line;
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
			}

			reader.close();

			response = stringBuilder.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return response;
	}
	
//	public static String cityToCoord(String city)
//	{
//		// World Cities by andruxnet
//		String apiKey = "c37d4f31b0mshfed9db028d5666fp146dc1jsn9e7edef082e3";
//		
//		String urlString = "https://andruxnet-world-cities-v1.p.rapidapi.com/?query=" + city + "&searchby=city";
//
//		StringBuilder stringBuilder = new StringBuilder();
//		
//		try {			
//			URL url = new URL(urlString);
//			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//
//			conn.setRequestMethod("GET");
//			conn.setRequestProperty("RapidAPI Project", "default-application_4288908");
//			conn.setRequestProperty("x-rapidapi-host", "andruxnet-world-cities-v1.p.rapidapi.com");
//			conn.setRequestProperty("x-rapidapi-key", apiKey);
//			conn.setDoInput(true);
//			conn.setDoOutput(true);
//
//			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//
//			String line;
//			while ((line = reader.readLine()) != null) {
//				stringBuilder.append(line);
//			}
//
//			reader.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		
//		return stringBuilder.toString();
//	}
}
