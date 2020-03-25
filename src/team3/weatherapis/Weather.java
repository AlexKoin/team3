package team3.weatherapis;

public class Weather {
	// TODO: add implementation for source, location and timestamp
	// TODO: fix field types as agreed
	private String source;
	private String location;
	private String temperature;
	private String humidity;
	private String windSpeed;
	private CardinalDirection windDirection; // TODO: should it be stored as String?
	private String timestamp; // TODO: should it be 'date'?

	private static final String suffixCelsius = "°C";

	public Weather() {
		this("", "", "", "", "", CardinalDirection.NONE, "");
	}

	public Weather(String source, String location, String temperature, String humidity, String windSpeed,
			CardinalDirection windDirection, String timeStamp) {
		this.source = source;
		this.location = location;
		this.temperature = temperature;
		this.humidity = humidity;
		this.windSpeed = windSpeed;
		this.windDirection = windDirection;
		this.timestamp = timeStamp;
	}
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.source = location;
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

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String toString() {
		return "source: " + this.source + "\nlocation: " + this.location + "\ntemperature: " + this.temperature
				+ suffixCelsius + ",\nhumidity: " + this.humidity + ",\nwind speed: " + this.windSpeed
				+ ",\nwind direction: " + this.windDirection + "\ntime stamp: " + this.timestamp;
	}
}
