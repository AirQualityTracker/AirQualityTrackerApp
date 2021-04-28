package com.androidapp.airqualitytracker;

public class AQT_CARD_DATA {
    private  int face_image, degrees_celsius_image, air_km_h_image, atmosphere_humidity_image;
    private  String quality_indicator, quality_assessment, degrees_celsius, air_km_h, atmosphere_humidity;


    public AQT_CARD_DATA(){
        //
    }


    public int getFace_image() {
        return face_image;
    }

    public void setFace_image(int face_image) {
        this.face_image = face_image;
    }

    public int getDegrees_celsius_image() {
        return degrees_celsius_image;
    }

    public void setDegrees_celsius_image(int degrees_celsius_image) {
        this.degrees_celsius_image = degrees_celsius_image;
    }

    public int getAir_km_h_image() {
        return air_km_h_image;
    }

    public void setAir_km_h_image(int air_km_h_image) {
        this.air_km_h_image = air_km_h_image;
    }

    public int getAtmosphere_humidity_image() {
        return atmosphere_humidity_image;
    }

    public void setAtmosphere_humidity_image(int atmosphere_humidity_image) {
        this.atmosphere_humidity_image = atmosphere_humidity_image;
    }

    public String getQuality_indicator() {
        return quality_indicator;
    }

    public void setQuality_indicator(String quality_indicator) {
        this.quality_indicator = quality_indicator;
    }

    public String getQuality_assessment() {
        return quality_assessment;
    }

    public void setQuality_assessment(String quality_assessment) {
        this.quality_assessment = quality_assessment;
    }

    public String getDegrees_celsius() {
        return degrees_celsius;
    }

    public void setDegrees_celsius(String degrees_celsius) {
        this.degrees_celsius = degrees_celsius;
    }

    public String getAir_km_h() {
        return air_km_h;
    }

    public void setAir_km_h(String air_km_h) {
        this.air_km_h = air_km_h;
    }

    public String getAtmosphere_humidity() {
        return atmosphere_humidity;
    }

    public void setAtmosphere_humidity(String atmosphere_humidity) {
        this.atmosphere_humidity = atmosphere_humidity;
    }
}

