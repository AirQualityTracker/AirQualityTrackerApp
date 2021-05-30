package com.androidapp.airqualitytracker;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * This class represents a card whch holds data for the city that is displayed
 */
@Entity(tableName = "card_table")
public class Card {
    //data of the city
    @PrimaryKey(autoGenerate = true)
    private int id;
    private final String city, state, country;

    private final int aqius;
    private final int aqicn;

    private final double degrees;

    private final double windspeed;

    private final double humidity;
    private int severityCategory;

    private final double latitude;
    private final double longitude;

    public Card(String city, String state, String country, int aqius, int aqicn, double degrees, double windspeed, double humidity, double latitude, double longitude) {
        this.city = city;
        this.state = state;
        this.country = country;

        this.aqius = aqius;
        this.aqicn = aqicn;

        this.degrees = degrees;
        this.windspeed = windspeed;
        this.humidity = humidity;

        this.latitude = latitude;
        this.longitude = longitude;

        this.severityCategory = calcSeverity(aqius);
    }


    //calculates the severity of the air quality
    private int calcSeverity(int aqi) {
        int severity;

        if (aqi > 250) severity = 5;
        else if (aqi > 200) severity = 4;
        else if (aqi > 150) severity = 3;
        else if (aqi > 100) severity = 2;
        else if (aqi > 50) severity = 1;
        else severity = 0;

        return severity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public int getAqius() {
        return aqius;
    }

    public int getAqicn() {
        return aqicn;
    }

    public double getDegrees() {
        return degrees;
    }

    public double getWindspeed() {
        return windspeed;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getSeverityCategory() {
        return severityCategory;
    }

    public void setSeverityCategory(int severityCategory) {
        this.severityCategory = severityCategory;
    }

}
