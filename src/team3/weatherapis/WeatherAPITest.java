package team3.weatherapis;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WeatherAPITest {

	public static void main(String[] args) {
		ArrayList<WeatherAPI> weatherApis = new ArrayList<WeatherAPI>();

		// openweathermap.org
		weatherApis.add(new WeatherAPIOWM());
		// weatherstack.com
		weatherApis.add(new WeatherAPIWS());
		// weatherapi.com
		weatherApis.add(new WeatherAPIWAPI());
		// climacell.co
		weatherApis.add(new WeatherAPICC());

		for (WeatherAPI api : weatherApis) {
			System.out.println(api.getWeather().toString());
			System.out.println("***");
		}

		System.out.println(WeatherAPI.cityToCoord("riga"));
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("Starting test..");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Finishing test..");
	}

	@Test
	public void test01CardinalDirections() {
		CardinalDirection actual, expected;

		actual = CardinalDirection.fromDegree(180.0f + 22.4f);
		expected = CardinalDirection.SOUTH;
		assertEquals("Cardinal Direction incorrect", expected, actual);
	}

}
