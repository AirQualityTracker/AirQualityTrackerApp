package com.androidapp.airqualitytracker;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/* Room generates all the necessary code to create an SQLite table
 for this object using appropriate annotations.*/
@Entity(tableName = "card_table")
public class Card {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private final String city, county, country;

    private int aqiUS, aqiCN;
    private int degrees, airspeed, humidity;
    private int severityCategory;

    public Card(String city, String county, String country, int aqiUS, int aqiCN, int degrees, int airspeed, int humidity) {
        this.city = city;
        this.county = county;
        this.country = country;

        this.aqiUS = aqiUS;
        this.aqiCN = aqiCN;

        this.degrees = degrees;
        this.airspeed = airspeed;
        this.humidity = humidity;

        this.severityCategory = calcSeverity(aqiUS);
    }

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

    public String getCounty() {
        return county;
    }

    public String getCountry() {
        return country;
    }

    public int getAqiUS() {
        return aqiUS;
    }

    public int getAqiCN() {
        return aqiCN;
    }

    public int getDegrees() {
        return degrees;
    }

    public int getAirspeed() {
        return airspeed;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getSeverityCategory() {
        return severityCategory;
    }

    public void setSeverityCategory(int severityCategory) {
        this.severityCategory = severityCategory;
    }
}
