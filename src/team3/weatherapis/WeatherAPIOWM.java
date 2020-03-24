package team3.weatherapis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class WeatherAPIOWM implements WeatherAPI {

//	public WeatherAPIOWM()
//	{
//		
//	}

	@Override
	public Weather getWeather() {
		Weather weather = null;
		String api = "542d30a2a9b0fee31f38da18ad05ba41";
		String location = "Riga,lv";

		// http://api.openweathermap.org./data/2.5/weather?g=" + location + "&appid=" +
		// api

		String urlStr = "https://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + api;

		try {
			URL url = new URL(urlStr);
			URLConnection conn = url.openConnection();

			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder stringBuilder = new StringBuilder();

			while (reader.ready()) {
				stringBuilder.append(reader.readLine());
			}
			reader.close();

			// Now parse data into new Weather object

			weather = new Weather();

			Map<String, Object> respMap = WeatherAPI.jsonToMap(stringBuilder.toString());

			Map<String, Object> mainMap = WeatherAPI.jsonToMap(respMap.get("main").toString());
			Map<String, Object> windMap = WeatherAPI.jsonToMap(respMap.get("wind").toString());

			float celsiusTemp = WeatherAPI.kelvinToCelsius(mainMap.get("temp").toString());
			weather.setTemperature(celsiusTemp + "");
			weather.setHumidity(mainMap.get("humidity").toString());
			weather.setWindSpeed(windMap.get("speed").toString());
			float windDirectionInDegrees = Float.parseFloat(windMap.get("deg").toString());
			weather.setWindDirection(CardinalDirection.fromDegree(windDirectionInDegrees));
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return weather;
	}
}
