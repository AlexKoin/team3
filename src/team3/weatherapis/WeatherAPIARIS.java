package team3.weatherapis;

import java.util.ArrayList;
import java.util.Map;

public class WeatherAPIARIS implements WeatherAPI {

    private static final String source = "aerisapi.com";


    @Override
    public Weather getWeather() {
        Weather weather = null;
        String location = "riga,lv";
        String client_id = "6Fq0AwC3h41DSlMbyOjBG";
        String client_secret = "Ili6IaCULOoz1llsE4iNIcODvG2TGbnYdKMAuaWq";



        String urlStr = "https://api.aerisapi.com/forecasts/" + location + "?&format=json&filter=day&limit=7&client_id=" + client_id + "&client_secret=" + client_secret;

        String response = WeatherAPI.readFromUrl(urlStr);

        if (response != null) {
            weather = new Weather();

            @SuppressWarnings("unchecked")
            Map<String, Object> currentMap = (Map<String, Object>) WeatherAPI.jsonToMap(response);
            ArrayList<Map<String, Object>> temp1 = (ArrayList<Map<String, Object>>) currentMap.get("response");
            Map<String, Object> details = temp1.get(0);
            ArrayList<Map<String, Object>> details2 = (ArrayList<Map<String, Object>>) details.get("periods");
            Map<String, Object> details3 = details2.get(0);


            // Assign values from API response to instance of Weather
            try {
                weather.setSource(source);
                // weather.setLocation(currentMap.get(""));
                weather.setTemperature(details3.get("avgTempC").toString());
                weather.setHumidity(details3.get("humidity").toString());
                weather.setWindSpeed(details3.get("windSpeedKPH").toString());
                float windDirectionInDegrees = Float.parseFloat(details3.get("windDir80mDEG").toString());
                weather.setWindDirection(CardinalDirection.fromDegree(windDirectionInDegrees));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return weather;
    }

}
