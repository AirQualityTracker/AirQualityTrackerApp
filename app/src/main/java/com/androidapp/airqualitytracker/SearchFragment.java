package com.androidapp.airqualitytracker;

import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

/**
 * This class is used for searching locations
 * */
//returns lang and lot for a place user enters
public class SearchFragment extends Fragment {
    protected LayoutInflater layoutInflater;
    protected ViewGroup viewGroupContainer;
    protected View view;

    private Button bt;
    private EditText editText;
    public static TextView textView; //this will deleted
    public static String data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layoutInflater = inflater;
        viewGroupContainer = container;

        view = layoutInflater.inflate(R.layout.search_fragment, viewGroupContainer, false);

        editText = view.findViewById(R.id.et_place);
        bt = view.findViewById(R.id.bt);
        textView = view.findViewById(R.id.textview);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = editText.getText().toString();
                CityLocation cityLocation = new CityLocation();
                cityLocation.getAddress(address, SearchFragment.this.getActivity().getApplicationContext(), new GeoHandler());

            }
        });

        return view;
    }

    public class GeoHandler extends android.os.Handler {
        @Override
        public void handleMessage(Message msg) {
            String latitude, longitude, address = "";

            if (msg.what == 1) {
                Bundle bundle = msg.getData();
                latitude = bundle.getString("lat");
                longitude = bundle.getString("long");
                address = "Lat= " + latitude + "\nLong= " + longitude;
                TakeData process = new TakeData(latitude, longitude);
                process.execute();
            } else {
                address = null;
            }
            //textView.setText(address);

        }


    }

    /**
     * This class is used to get the data of every card from the airvisual api
     * */
    public class TakeData extends AsyncTask<Void, Void, Card> {
        private String latitude = "", longitude = "", data = "", datareturned = "";
        private String city, state, country, ws, hu, tp, aqius, aqicn;

        public TakeData(String latitude, String longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        @Override
        protected Card doInBackground(Void... voids) {
            try {
                URL url = new URL("https://api.airvisual.com/v2/nearest_city?lat=" + latitude + "&lon=" + longitude + "&key=aaa37172-9f2d-43bc-aca0-a2edc3417e9a");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line = "d";
                while (line != null) {
                    line = bufferedReader.readLine();
                    data = data + line + 'd';
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
                datareturned = "error2";
            } catch (JSONException e) {
                e.printStackTrace();
                datareturned = "error3";
            }

            return new Card(city, state, country, Integer.parseInt(aqius), Integer.parseInt(aqicn), Double.parseDouble(tp), Double.parseDouble(ws), Double.parseDouble(hu), Double.parseDouble(latitude), Double.parseDouble(longitude));
        }

        @Override
        protected void onPostExecute(Card card) {
            super.onPostExecute(card);

            TypedArray faceImages;
            int[] severityColors;
            String[] qualityAssessments;

            final CardView card_view;
            final ImageView face_image;
            final TextView city, state_country;
            final TextView quality_indicator, quality_assessment, degrees, windspeed, humidity;

            qualityAssessments = requireContext().getResources().getStringArray(R.array.quality_assessments);
            severityColors = requireContext().getResources().getIntArray(R.array.card_colors);
            faceImages = requireContext().getResources().obtainTypedArray(R.array.card_face_images);

            card_view = view.findViewById(R.id.card_view);
            face_image = view.findViewById(R.id.face_image);

            city = view.findViewById(R.id.location_city);
            state_country = view.findViewById(R.id.location_state_country);

            quality_indicator = view.findViewById(R.id.quality_indicator);
            quality_assessment = view.findViewById(R.id.quality_assessment);
            degrees = view.findViewById(R.id.degrees);
            windspeed = view.findViewById(R.id.windspeed);
            humidity = view.findViewById(R.id.humidity);

            card_view.setCardBackgroundColor(severityColors[card.getSeverityCategory()]);
            face_image.setImageResource(faceImages.getResourceId(card.getSeverityCategory(), 0));

            city.setText(card.getCity());
            state_country.setText(String.format("%1$s, %2$s", card.getState(), card.getCountry()));

            // TODO: Display AQI US or CN, depending on user settings
            quality_indicator.setText(String.valueOf(card.getAqius()));
            quality_assessment.setText(qualityAssessments[card.getSeverityCategory()]);

            degrees.setText(String.format(Locale.ENGLISH, "%1$s %2$s", card.getDegrees(), requireContext().getResources().getString(R.string.celsius_symbol)));
            windspeed.setText(String.format(Locale.ENGLISH, "%1$s\n%2$s", card.getWindspeed(), requireContext().getResources().getString(R.string.airspeed_ms)));
            humidity.setText(String.format(Locale.ENGLISH, "%1$s %2$s", card.getHumidity(), requireContext().getResources().getString(R.string.humidity)));


            card_view.setVisibility(View.VISIBLE);
        }
    }


}