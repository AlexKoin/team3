package team3.weatherapis;

import java.util.Map;

public class WeatherAPIOWM implements WeatherAPI {

	private static final String source = "openweathermap.org";
	private static final String apiKey = "542d30a2a9b0fee31f38da18ad05ba41";

	@Override
	public Weather getWeather() {
		Weather weather = null;
		String location = "Riga,lv";

		String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + apiKey;

		String response = WeatherAPI.readFromUrl(urlString);

		if (response != null) {
			// Now parse data into new Weather object
			weather = new Weather();

			// Convert JSON string into object
			@SuppressWarnings("unchecked")
			Map<String, Object> mainMap = (Map<String, Object>) WeatherAPI.jsonToMap(response).get("main");
			@SuppressWarnings("unchecked")
			Map<String, Object> windMap = (Map<String, Object>) WeatherAPI.jsonToMap(response).get("wind");

			// Assign values from API response to instance of Weather
			try {
				weather.setSource(source);
				// weather.setLocation(currentMap.get(""));
				float celsiusTemp = WeatherAPI.kelvinToCelsius(mainMap.get("temp").toString());
				weather.setTemperature(celsiusTemp + "");
				weather.setHumidity(mainMap.get("humidity").toString());
				weather.setWindSpeed(windMap.get("speed").toString());
				float windDirectionInDegrees = Float.parseFloat(windMap.get("deg").toString());
				weather.setWindDirection(CardinalDirection.fromDegree(windDirectionInDegrees));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return weather;
	}
}
