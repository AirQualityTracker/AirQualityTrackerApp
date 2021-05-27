package com.androidapp.airqualitytracker;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/* Room generates all the necessary code to create an SQLite table
 for this object using appropriate annotations.*/
@Entity(tableName = "card_table")
public class Card implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private final String city, state, country;

    private int aqius;
    private int aqicn;

    @SerializedName("tp")
    private int degrees;

    @SerializedName("ws")
    private int windspeed;

    @SerializedName("hu")
    private int humidity;
    private int severityCategory;

    public Card(String city, String state, String country, int aqius, int aqicn, int degrees, int windspeed, int humidity) {
        this.city = city;
        this.state = state;
        this.country = country;

        this.aqius = aqius;
        this.aqicn = aqicn;

        this.degrees = degrees;
        this.windspeed = windspeed;
        this.humidity = humidity;

        this.severityCategory = calcSeverity(aqius);
    }

    protected Card(Parcel in) {
        id = in.readInt();
        city = in.readString();
        state = in.readString();
        country = in.readString();
        aqius = in.readInt();
        aqicn = in.readInt();
        degrees = in.readInt();
        windspeed = in.readInt();
        humidity = in.readInt();
        severityCategory = in.readInt();
    }

    public static final Creator<Card> CREATOR = new Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };

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

    public int getDegrees() {
        return degrees;
    }

    public int getWindspeed() {
        return windspeed;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(country);
        dest.writeInt(aqius);
        dest.writeInt(aqicn);
        dest.writeInt(degrees);
        dest.writeInt(windspeed);
        dest.writeInt(humidity);
        dest.writeInt(severityCategory);
    }
}
