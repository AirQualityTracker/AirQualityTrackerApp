package com.androidapp.airqualitytracker;

import android.os.AsyncTask;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TakeData extends AsyncTask<Void, Void, Card> {
    String latitude = "" , longitude = "" , data="" , datareturned= "";
    String city, state , country , ws, hu, tp, aqius, aqicn;

    public TakeData(String latitude , String longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    protected Card doInBackground(Void... voids) {
        try {
            URL url = new URL("https://api.airvisual.com/v2/nearest_city?lat="+latitude+"&lon="+longitude+"&key=aaa37172-9f2d-43bc-aca0-a2edc3417e9a");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "d";
            while (line!=null){
                line= bufferedReader.readLine();
                data = data + line+'d';
            }


            JSONObject jsonObject = new JSONObject(data);
            JSONObject jsonObject1 = (JSONObject) jsonObject.get("data");
            JSONObject jsonObject2 = (JSONObject) jsonObject1.get("current");
            JSONObject jsonObject3 = (JSONObject) jsonObject2.get("weather");

            //Save wind speed
            ws = jsonObject3.get("ws").toString();
            //Save humidily
            hu = jsonObject3.get("hu").toString();
            //Save temperature
            tp = jsonObject3.get("tp").toString();

            JSONObject jsonObject4 = (JSONObject) jsonObject2.get("pollution");

            //Save aqi score (Usa)
            aqius = jsonObject4.get("aqius").toString();
            //Save aqi score (China)
            aqicn = jsonObject4.get("aqicn").toString();

            //Save city
            city = jsonObject1.get("city").toString();
            //Save state
            state = jsonObject1.get("state").toString();
            //Save country
            country = jsonObject1.get("country").toString();


            /*
            JSONArray jsonArray = new JSONArray(data);
            for (int i=0;i <jsonArray.length(); i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                datareturned= jsonObject.get("current") + "\n"+
                              jsonObject.get("pollution") +  "\n" +
                              jsonObject.get("wd");

            }


             */


        } catch (MalformedURLException e) {
            e.printStackTrace();
            datareturned = "error";
        } catch (IOException e) {
            e.printStackTrace();
            datareturned="error2";
        } catch (JSONException e) {
            e.printStackTrace();
            datareturned = "error3";
        }

        return new Card(city, state, country, Integer.parseInt(aqius), Integer.parseInt(aqicn), Double.parseDouble(tp), Double.parseDouble(ws) , Double.parseDouble(hu) , Double.parseDouble(latitude), Double.parseDouble(longitude));
    }

    @Override
    protected void onPostExecute(Card card){
        super.onPostExecute(card);



        SearchFragment.textView.setText(datareturned);
    }
}
