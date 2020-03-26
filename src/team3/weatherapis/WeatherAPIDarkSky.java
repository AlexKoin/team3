package team3.weatherapis;

import java.util.Map;

public class WeatherAPIDarkSky implements WeatherAPI {
	
	private static final String source = "Dark Sky API";
	private static final String apiKey = "a6c84f43a34c0ad01c730dba9c5e1f27";

	@Override
	public Weather getWeather() {

		Weather weather = null;
		String latitude = "42.3601"; // testing
		String longitude = "-71.0589"; // testing
		// NB! These values are NOT from Riga!
		// They're just random values suggested by Dark Sky's sample call

		String urlString = "https://api.darksky.net/forecast/" + apiKey + "/" + latitude + "," + longitude;

		String response = WeatherAPI.readFromUrl(urlString);

		if (response != null) {
			// Now parse data into new Weather object
			weather = new Weather();

			// Convert JSON string into object
			@SuppressWarnings("unchecked")
			Map<String, Object> currentMap = (Map<String, Object>) WeatherAPI.jsonToMap(response).get("currently");

			// Assign values from API response to instance of Weather
			try {
				weather.setSource(source);
				weather.setLocation(latitude + "/" + longitude + " (TEST)"); // TODO: needs conversion
				float celsiusTemp = Float.parseFloat(currentMap.get("temperature").toString());
				weather.setTemperature(Float.toString(celsiusTemp));
				weather.setHumidity(currentMap.get("humidity").toString());
				weather.setWindSpeed(currentMap.get("windSpeed").toString());
				float windDirectionInDegrees = Float.parseFloat(currentMap.get("windBearing").toString());
				weather.setWindDirection(CardinalDirection.fromDegree(windDirectionInDegrees));
				weather.setTimestamp("NULL TIMESTAMP FOR TESTING");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return weather;
	}

	/*public static void main(String[] args) {
		WeatherAPIDarkSky test = new WeatherAPIDarkSky();
		System.out.println(test.getWeather());
	}*/
	
	// Uncomment to run for manual testing

}
