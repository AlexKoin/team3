package team3.weatherapis;

import java.util.ArrayList;
import java.util.Map;

public class WeatherAPIWB implements WeatherAPI {
    private static final String source = "weatherbit.io";
    private static final String apiKey = "54e6200b9c554b03b0c77a61c48bb576";

    @Override
    public Weather getWeather() {
        Weather weather = null;
        String location = "Riga,lv";

        String urlString ="https://api.weatherbit.io/v2.0/current?city=" + location + "&key=" + apiKey;
        String response = WeatherAPI.readFromUrl(urlString);

        if (response != null) {
            // Now parse data into new Weather object
            weather = new Weather();
            Map<String, Object> map = (Map<String, Object>) WeatherAPI.jsonToMap(response);

            ArrayList<Map<String, Object>> weathers = (ArrayList<Map<String, Object>>) map.get("data");
            Map<String, Object> details = weathers.get(0);

            try {
                weather.setSource(source);
                weather.setTemperature(details.get("temp").toString());
                weather.setHumidity(details.get("rh").toString());
                weather.setWindSpeed(details.get("wind_spd").toString());
                float windDirectionInDegrees = Float.parseFloat(details.get("wind_dir").toString());
                weather.setWindDirection(CardinalDirection.fromDegree(windDirectionInDegrees));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return weather;
    }
}
