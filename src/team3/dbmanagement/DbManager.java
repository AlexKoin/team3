package team3.dbmanagement;

import java.sql.*;

public class DbManager {

	public static void main(String[] args) {
		DbManager result = new DbManager();
		result.findResult("Riga");
	}

	protected Connection conn;
	Statement stmt = null;

	public DbManager() {

		Object obj = null;

		String dbName = "weatherApis";
		String user = "root";
		String pass = "coolTeam";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/weatherApis?autoReconnect=true&useSSL=false",
					user, pass);
		} catch (Exception e) {
			System.err.println(e);
		}
//        try {
//            conn.setAutoCommit(false);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
	}

	public Result findResult(String cityName) {

		ResultSet rs = null;

		String apiAris = null;
		String apiCc = null;
		String apiDarkSky = null;
		String apiOwm = null;
		String apiWapi = null;
		String apiWb = null;
		String apiWs = null;

		try {
			stmt = conn.createStatement();
		} catch (Exception e) {
		}

		try {
			rs = stmt.executeQuery("SELECT * FROM cities WHERE CityName = '" + cityName + "'");
			while (rs.next()) {
				apiAris = rs.getString("WeatherAPIARIS");
				System.out.println(apiAris);
				apiCc = rs.getString("WeatherAPICC");
				System.out.println(apiCc);
				apiDarkSky = rs.getString("WeatherAPIDarkSky");
				System.out.println(apiDarkSky);
				apiOwm = rs.getString("WeatherAPIOWM");
				System.out.println(apiOwm);
				apiWapi = rs.getString("WeatherAPIWAPI");
				System.out.println(apiWapi);
				apiWb = rs.getString("WeatherAPIWB");
				System.out.println(apiWb);
				apiWs = rs.getString("WeatherAPIWS");
				System.out.println(apiWs);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Result obj = new Result(apiAris, apiCc, apiDarkSky, apiOwm, apiWapi, apiWb, apiWs);
		return obj;
	}
}
