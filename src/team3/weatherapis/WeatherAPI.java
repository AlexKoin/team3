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
}
