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
		
		
		weatherApis.add(new WeatherAPIOWM());
		
		
		for (WeatherAPI api : weatherApis)
		{
			System.out.println(api.getWeather().toString());
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
		fail("Not yet implemented");
	}

}
