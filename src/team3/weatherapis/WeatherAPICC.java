package team3.weatherapis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class WeatherAPICC implements WeatherAPI {

	private static final String source = "climacell.co";
	private static final String apiKey = "dCj790S0DL1qEx8DmUdV9JDyY0aT0E4x";
    String location = null;


    @SuppressWarnings("unchecked")
	@Override
    public Weather getWeather(String location) {
        this.location = location;
		Weather weather = null;
		//String location = "riga";

		String urlString = "https://api.climacell.co/v3/weather/realtime?" + this.location + "&fields=temp,humidity,wind_speed,wind_direction";

		StringBuilder stringBuilder = new StringBuilder();

		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();

			conn.setRequestProperty("apikey", apiKey);
			conn.setRequestProperty("Content-Type", "application/JSON");
			conn.setDoInput(true);
			conn.setDoOutput(true);

			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line;
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String response = stringBuilder.toString();

		if (response != null) {
			// Now parse data into new Weather object
			weather = new Weather();

			// Convert JSON string into object
			Map<String, Object> map = (Map<String, Object>) WeatherAPI.jsonToMap(response);

			// Assign values from API response to instance of Weather
			try {
				weather.setSource(source);
				// weather.setLocation(currentMap.get(""));
				weather.setTemperature(((Map<String, Object>)map.get("temp")).get("value").toString());
				weather.setHumidity(((Map<String, Object>)map.get("humidity")).get("value").toString());
				weather.setWindSpeed(((Map<String, Object>)map.get("wind_speed")).get("value").toString());
				float windDirectionInDegrees = Float.parseFloat(((Map<String, Object>)map.get("wind_direction")).get("value").toString());
				weather.setWindDirection(CardinalDirection.fromDegree(windDirectionInDegrees));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return weather;
	}

}
