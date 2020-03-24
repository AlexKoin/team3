package team3.weatherapis;

public class Weather {
	// TODO: add implementation for location and timestamp
	// TODO: fix field types as agreed
	//private String location;
	private String temperature;
	private String humidity;
	private String windSpeed;
	private CardinalDirection windDirection;
	private String timestamp;
	
	private static final String suffixCelsius = "°C";
	//private static final String suffixKelvin = "°K";

	public Weather() {
		this("", "", "", CardinalDirection.NONE, "");
	}

	public Weather(String temperature, String humidity, String windSpeed, CardinalDirection windDirection,
			String timeStamp) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.windSpeed = windSpeed;
		this.windDirection = windDirection;
		this.timestamp = timeStamp;
	}

	public String getTemperature() {
		return temperature;
	}
	
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getHumidity() {
		return humidity;
	}
	
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getWindSpeed() {
		return windSpeed;
	}
	
	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}

	public CardinalDirection getWindDirection() {
		return windDirection;
	}
	
	public void setWindDirection(CardinalDirection windDirection) {
		this.windDirection = windDirection;
	}

	public String getTimeStamp() {
		return timestamp;
	}

	public String toString() {
		return "temperature: " + this.temperature + suffixCelsius + ",\nhumidity: " + this.humidity + ",\nwind speed: "
				+ this.windSpeed + ",\nwind direction: " + this.windDirection;
	}
}
