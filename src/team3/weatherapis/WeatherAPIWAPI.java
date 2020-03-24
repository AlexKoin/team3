package team3.weatherapis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class WeatherAPIWAPI implements WeatherAPI {
	
	// https://www.weatherapi.com/
	@Override
	public Weather getWeather() {
		Weather weather = null;
		String api = "719433f15a3249d2b79154934202403";
		String location = "Riga";

		String urlStr = "http://api.weatherapi.com/v1/current.json?key="+api+"&q=" + location;

		try {
			URL url = new URL(urlStr);
			URLConnection conn = url.openConnection();

			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder stringBuilder = new StringBuilder();

			String line;
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
			}
			reader.close();
			
			// Now parse data into new Weather object
			weather = new Weather();
			
			String response = stringBuilder.toString();
			
			@SuppressWarnings("unchecked")
			Map<String, Object> currentMap = (Map<String, Object>)WeatherAPI.jsonToMap(response).get("current");
			
			weather.setTemperature(currentMap.get("temp_c").toString());
			weather.setHumidity(currentMap.get("humidity").toString());
			weather.setWindSpeed(currentMap.get("wind_kph").toString());
			float windDirectionInDegrees = Float.parseFloat(currentMap.get("wind_degree").toString());
			weather.setWindDirection(CardinalDirection.fromDegree(windDirectionInDegrees));
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return weather;
	}

}
