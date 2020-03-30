package team3.dbmanagement;

import team3.weatherapis.*;

import java.sql.*;
import java.util.ArrayList;


public class DbManager {
	

    public Weather apiAris = null;
    public Weather apiCc = null;
    public Weather apiDs = null;
    public Weather apiOwm = null;
    public Weather apiWapi = null;
    public Weather apiWb = null;
    public Weather apiWs = null;

    protected  Connection conn;
    Statement stmt = null;
    public ArrayList<String> cityList = new ArrayList<String>();

    public DbManager() {
        String user = "bouncyie_root";
        String pass = "coolTeam123";
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://bouncy.ie:3306/bouncyie_weatherApis?useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", user, pass);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        
        try {
            stmt = conn.createStatement();
        } catch (Exception e) {
        }
        try {
            rs = stmt.executeQuery("SELECT * FROM cities ORDER BY CityName");
            while (rs.next()) {
            	cityList.add(rs.getString("CityName"));
 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }       
    }


    public void setCityToApis(String city)
    {
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
        } catch (Exception e) {
        }
        try {
            rs = stmt.executeQuery("SELECT * FROM cities WHERE CityName = '" + city + "'");
            while (rs.next()) {
                this.apiAris = new WeatherAPIARIS().getWeather(rs.getString("WeatherAPIARIS")) ;
                this.apiCc = new WeatherAPICC().getWeather(rs.getString("WeatherAPICC"));
                this.apiDs = new WeatherAPIDarkSky().getWeather(rs.getString("WeatherAPIDarkSky"));
                this.apiOwm = new WeatherAPIOWM().getWeather(rs.getString("WeatherAPIOWM"));
                this.apiWapi = new WeatherAPIWAPI().getWeather(rs.getString("WeatherAPIWAPI"));
                this.apiWb = new WeatherAPIWB().getWeather(rs.getString("WeatherAPIWB"));
                this.apiWs = new WeatherAPIWS().getWeather(rs.getString("WeatherAPIWS"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Weather getApiAris() {
        return this.apiAris;
    }

    public Weather getApiCc() {
        return this.apiCc;
    }

    public Weather getApiDs() {
        return this.apiDs;
    }

    public Weather getApiWapi() {
        return this.apiWapi;
    }

    public Weather getApiOwm() {
        return this.apiOwm;
    }

    public Weather getApiWb() {
        return this.apiWb;
    }

    public Weather getApiWs() {
        return this.apiWs;
    }


}


