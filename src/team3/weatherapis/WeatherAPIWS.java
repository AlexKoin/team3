package team3.weatherapis;

import java.util.Map;

public class WeatherAPIWS implements WeatherAPI {

	private static final String source = "weatherstack.com";
	private static final String apiKey = "321c152f35c73d014a4b237d574a84e7";
    String location = null;


    @Override
    public Weather getWeather(String location) {
        this.location = location;
		Weather weather = null;
//		String location = "Riga";

		String urlString = "http://api.weatherstack.com/current?access_key=" + apiKey + "&query=" + location;

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
				//weather.setLocation(currentMap.get(""));
				weather.setTemperature(currentMap.get("temperature").toString());
				weather.setHumidity(currentMap.get("humidity").toString());
				weather.setWindSpeed(currentMap.get("wind_speed").toString());
				float windDirectionInDegrees = Float.parseFloat(currentMap.get("wind_degree").toString());
				weather.setWindDirection(CardinalDirection.fromDegree(windDirectionInDegrees));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return weather;
	}
}
