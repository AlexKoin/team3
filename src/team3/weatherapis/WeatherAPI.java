package team3.weatherapis;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public interface WeatherAPI {
	 public Weather getWeather();
	 
	 public static Map<String, Object> jsonToMap(String str) {
			Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {
			}.getType());

			return map;
		}
}
