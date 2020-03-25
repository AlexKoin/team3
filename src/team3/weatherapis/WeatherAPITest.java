package team3.weatherapis;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WeatherAPITest {
	
	public static void main(String[] args)
	{
		ArrayList<WeatherAPI> weatherApis = new ArrayList<WeatherAPI>();
		
		// openweathermap.org
		weatherApis.add(new WeatherAPIOWM());
		// weatherstack.com
		weatherApis.add(new WeatherAPIWS());
		// weatherapi.com
		weatherApis.add(new WeatherAPIWAPI());
		
		for (WeatherAPI api : weatherApis)
		{
			System.out.println(api.getWeather().toString());
			System.out.println("***");
		}
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		CardinalDirection actual, expected;
		
		actual = CardinalDirection.fromDegree(180.0f);
		expected = CardinalDirection.SOUTH;
		assertEquals("Cardinal Direction incorrect", expected, actual);
	}

}
