package team3.weatherapp;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team3.dbmanagement.DbManager;
//import team3.weatherapis.Weather;
//import team3.weatherapis.WeatherAPI;
//import team3.weatherapis.WeatherAPICC;
//import team3.weatherapis.WeatherAPIDarkSky;
//import team3.weatherapis.WeatherAPIOWM;
//import team3.weatherapis.WeatherAPIWAPI;
//import team3.weatherapis.WeatherAPIWB;
//import team3.weatherapis.WeatherAPIWS;



public class WeatherAppController implements Serializable {

	private static final long serialVersionUID = -8408135492483896421L;
	static DbManager city = new DbManager();
	
	public WeatherAppController() {

	}
	
//	private static ArrayList<WeatherAPI> makeWeatherApiList()
//	{
//	
//		ArrayList<WeatherAPI> weatherApis = new ArrayList<WeatherAPI>();
//		
//		// openweathermap.org
//		weatherApis.add(city.getApiAris());
//		// weatherstack.com
//		weatherApis.add(new WeatherAPIWS());
//		// weatherapi.com
//		weatherApis.add(new WeatherAPIWAPI());
//		// climacell.co
//		weatherApis.add(new WeatherAPICC());
//		// weatherbit.io
//		weatherApis.add(new WeatherAPIWB());
//		// darksky.net
//		weatherApis.add(new WeatherAPIDarkSky());
//		
//		return weatherApis;
//	}
	public static ArrayList<String> cityList()
	{
		DbManager list = new DbManager();
		return list.cityList;
	}

	public static String formatInputGroup(HttpServletRequest request)
	{
		StringBuilder htmlBuilder = new StringBuilder();
		
		String location = request.getParameter("location");
		//String display = request.getParameter("display");
		
		if ((location != null) && (!location.isBlank())) {
		}
		
		return htmlBuilder.toString();
	}
	
	public static String formatWeatherResults(HttpServletRequest request, HttpServletResponse response) {
		StringBuilder htmlBuilder = new StringBuilder();
		
		String location = request.getParameter("location");
		String display = request.getParameter("display");
		
		if ((location != null) && (!location.isBlank())) {
			city.setCityToApis(location);

//			ArrayList<WeatherAPI> weatherApis = makeWeatherApiList();
//			ArrayList<Weather> weatherResults = new ArrayList<Weather>();

			/* Use location parameter here to retrieve weather data: */
//			for (WeatherAPI api : weatherApis) {
//				weatherResults.add(api.getWeather("Riga"));
//			}
			/* Use display parameter to format data for display: */
		
			if ((display != null) && (!display.isBlank()))
			{
				htmlBuilder.append("<div class='container py-3'>");
				
				switch (display)
				{
					case "table" :
					{
						htmlBuilder.append("<h1>Weather forecast for " + location +"</h1>");
						htmlBuilder.append("<div class='container'>");
						htmlBuilder.append("<div class='row'>");
						htmlBuilder.append("<div class='col-sm'>");
						htmlBuilder.append(city.getApiAris().toString());
						htmlBuilder.append("</div>");
						htmlBuilder.append("<div class='col-sm'>");
						htmlBuilder.append(city.getApiCc().toString());
						htmlBuilder.append("</div>");
						htmlBuilder.append("</div>");
						htmlBuilder.append("<div class='row'>");
						htmlBuilder.append("<div class='col-sm'>");
						htmlBuilder.append(city.getApiDs().toString());
						htmlBuilder.append("</div>");
						htmlBuilder.append("<div class='col-sm'>");
						htmlBuilder.append(city.getApiOwm().toString());
						htmlBuilder.append("</div>");
						htmlBuilder.append("</div>");
						htmlBuilder.append("<div class='row'>");
						htmlBuilder.append("<div class='col-sm'>");
						htmlBuilder.append(city.getApiWapi().toString());
						htmlBuilder.append("</div>");
						htmlBuilder.append("<div class='col-sm'>");
						htmlBuilder.append(city.getApiWb().toString());
						htmlBuilder.append("</div>");
						htmlBuilder.append("</div>");
						htmlBuilder.append("<div class='row'>");
						htmlBuilder.append("<div class='col-sm'>");
						htmlBuilder.append(city.getApiWs().toString());
						htmlBuilder.append("</div>");
						htmlBuilder.append("</div>");
						htmlBuilder.append("</div>");		
//						for (Weather weather : weatherResults) {
//							htmlBuilder.append("<li class='list-group-item'>");
//							htmlBuilder.append(weather.toString());
//							htmlBuilder.append("</li>");
//						}
						htmlBuilder.append("</ul>");
						
						response.setStatus(HttpServletResponse.SC_OK);
						
						break;
					}
				
					case "average" :
					{
						float aris = Float.parseFloat(city.getApiAris().getTemperature());
						float cc = Float.parseFloat(city.getApiCc().getTemperature());
						
						float average = (aris + cc) / 2;
						htmlBuilder.append("<p> Average Temperature for "+ location +" is " + average + " &#8451</p>");
						// TODO: need to implement
//						htmlBuilder.append("<div class='badge badge-warning'>Not implemented</div>");
						
						response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
						break;
					}
					
					default :
					{
						// Invalid display parameter
						response.setStatus(HttpServletResponse.SC_ACCEPTED);
					}
				}
				
				htmlBuilder.append("</div>");
			}	
		}
		else if((location == null))
		{
			if((display != null) && (!display.isBlank())) 
			{
			htmlBuilder.append("<div class='container py-3'>");
			htmlBuilder.append("<div class='badge badge-warning'>Please choose city!</div>");
			htmlBuilder.append("</div>");
			}
		}

		return htmlBuilder.toString();
	}
}
