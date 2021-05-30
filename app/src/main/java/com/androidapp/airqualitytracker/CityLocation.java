package com.androidapp.airqualitytracker;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Message;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class CityLocation {
    public static void getAddress  (String locationAddress , Context context, android.os.Handler handler) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                String result = null, longitude="" ,latitude="";
                Bundle bundle = new Bundle();
                try {
                    List addresslist = geocoder.getFromLocationName(locationAddress, 1);
                    if (addresslist != null && addresslist.size() > 0) {
                        Address address = (Address) addresslist.get(0);
                        StringBuilder stringBuilder = new StringBuilder();
                        latitude = String.valueOf(address.getLatitude());
                        longitude= String.valueOf(address.getLongitude());
                        result = latitude + "__" + longitude;



                        //stringBuilder.append(address.getLatitude() + '-');
                        //stringBuilder.append(address.getLongitude());
                        //stringBuilder.append(address.getCountryName() + '\n');
                        //stringBuilder.append(address.getLocality() + '\n');
                       // result = stringBuilder.toString();
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    Message message = Message.obtain();
                    message.setTarget(handler);
                    if (result!=null){
                        message.what=1;
                        //result = "Location " + locationAddress  + '\n' + result;
                        bundle.putString("lat" , latitude);
                        bundle.putString("long", longitude);
                        message.setData(bundle);

                    }
                    message.sendToTarget();
                }


            }
        };
        thread.start();

    }
}
