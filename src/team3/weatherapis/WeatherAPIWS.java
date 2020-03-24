package team3.weatherapis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class WeatherAPIWS implements WeatherAPI {

	@Override
	public Weather getWeather() {
		// Weatherstack - https://weatherstack.com/quickstart
		Weather weather = null;
		String api = "321c152f35c73d014a4b237d574a84e7";
		String location = "Riga";

		String urlStr = "http://api.weatherstack.com/current?access_key=" + api + "&query=" + location;

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
			
			String response = stringBuilder.toString();
			
			@SuppressWarnings("unchecked")
			Map<String, Object> currentMap = (Map<String, Object>)WeatherAPI.jsonToMap(response).get("current");
			
			weather.setTemperature(currentMap.get("temperature").toString());
			weather.setHumidity(currentMap.get("humidity").toString());
			weather.setWindSpeed(currentMap.get("wind_speed").toString());
			float windDirectionInDegrees = Float.parseFloat(currentMap.get("wind_degree").toString());
			weather.setWindDirection(CardinalDirection.fromDegree(windDirectionInDegrees));
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return weather;
	}

}
