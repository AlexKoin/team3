package team3.weatherapis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class WeatherAPIOWM implements WeatherAPI {

	@Override
	public Weather getWeather() {
		Weather weather = null;
		String api = "542d30a2a9b0fee31f38da18ad05ba41";
		String location = "London,uk";

		// http://api.openweathermap.org./data/2.5/weather?g=" + location + "&appid=" +
		// api

		String urlStr = "https://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + api;
		try {
			URL url = new URL(urlStr);
			URLConnection conn = url.openConnection();

			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();

			while (rd.ready()) {
				sb.append(rd.readLine());
			}
			rd.close();

			// Now parse data into new Weather object

			weather = new Weather();

			Map<String, Object> respMap = WeatherAPI.jsonToMap(sb.toString());

			Map<String, Object> mainMap = WeatherAPI.jsonToMap(respMap.get("main").toString());
			Map<String, Object> windMap = WeatherAPI.jsonToMap(respMap.get("wind").toString());

			weather.setTemperature(mainMap.get("temp").toString());
			weather.setHumidity(mainMap.get("humidity").toString());
			weather.setWindSpeed(windMap.get("speed").toString());
						
			weather.setWindDirection(CardinalDirection.fromDegree(Float.parseFloat(windMap.get("deg").toString())));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return weather;
	}
}
