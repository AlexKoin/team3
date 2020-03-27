package team3.weatherapis;

import java.util.Map;

public class WeatherAPIWAPI implements WeatherAPI {

	private static final String source = "weatherapi.com";
	private static final String apiKey = "719433f15a3249d2b79154934202403";

	@Override
	public Weather getWeather() {
		Weather weather = null;
		String location = "Riga";

		String urlString = "http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + location;

		String response = WeatherAPI.readFromUrl(urlString);

		if (response != null) {
			// Now parse data into new Weather object
			weather = new Weather();

			// Convert JSON string into object
			@SuppressWarnings("unchecked")
			Map<String, Object> currentMap = (Map<String, Object>) WeatherAPI.jsonToMap(response).get("current");

			// Assign values from API response to instance of Weather
			try {
				weather.setSource(source);
				// weather.setLocation(currentMap.get(""));
				weather.setTemperature(currentMap.get("temp_c").toString());
				weather.setHumidity(currentMap.get("humidity").toString());
				weather.setWindSpeed(currentMap.get("wind_kph").toString());
				float windDirectionInDegrees = Float.parseFloat(currentMap.get("wind_degree").toString());
				weather.setWindDirection(CardinalDirection.fromDegree(windDirectionInDegrees));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return weather;
	}

}
