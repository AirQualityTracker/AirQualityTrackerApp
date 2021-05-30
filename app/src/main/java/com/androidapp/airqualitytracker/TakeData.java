package com.androidapp.airqualitytracker;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TakeData extends AsyncTask<Void, Void, Void> {

    String latitude = "" , longitude = "" , data="" , datareturned= "";

    public TakeData(String latitude , String longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }


    @Override
    protected Void doInBackground(Void... voids) {
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
            data = "error";
        } catch (IOException e) {
            e.printStackTrace();
            data="error2";
        } /*catch (JSONException e) {
            e.printStackTrace();
        }

       */
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid){
        super.onPostExecute(aVoid);

        SearchFragment.textView.setText(this.data);
    }
}
