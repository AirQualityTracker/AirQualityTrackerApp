package com.androidapp.airqualitytracker;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import java.util.List;
import java.util.Locale;

/**
 * This class is used to show the map of the app
 */
//return current location of the user

public class MapFragment extends Fragment {
    //textView1 for lat long
    public static TextView textView1; //textviews will deleted, they are used just to confirm is working
    Button button;
    FusedLocationProviderClient fusedLocationProviderClient;
    protected LayoutInflater layoutInflater;
    protected ViewGroup viewGroupContainer;
    protected View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //get the view from the xml
        View view = inflater.inflate(R.layout.map_fragment, container, false);
        layoutInflater = inflater;
        viewGroupContainer = container;

        textView1 = view.findViewById(R.id.textview1);

        button = view.findViewById(R.id.button_location);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.getActivity());
        //add listener to the button that asks for the user location
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getlocation();
                } else {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });
        return view;
    }


    //This method is used to get the locations of the user
    private void getlocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                //Initialize location
                Location location = task.getResult();
                if (location != null) {

                    try {
                        //Initialize Geocoder
                        Geocoder geocoder = new Geocoder(getActivity().getApplicationContext(), Locale.getDefault());
                        //Initialize Address List
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLatitude(), 1);

                        //textView1.setText(location.getLatitude() +"\n"+ location.getLongitude());

                        TakeData process = new TakeData(String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()));
                        process.execute();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                // else textView1.setText("Null");

            }
        });
    }
/*
    public class TakeData extends AsyncTask<Void, Void, Void> {
        private String latitude = "", longitude = "", data = "", datareturned = "";
        private String city, state, country, ws, hu, tp, aqius, aqicn;

        public TakeData(String latitude, String longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("https://api.airvisual.com/v2/nearest_city?lat=" + latitude + "&lon=" + longitude + "&key=aaa37172-9f2d-43bc-aca0-a2edc3417e9a");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line = "";
                while (line != null) {
                    line = bufferedReader.readLine();
                    data = data + line;
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

                datareturned = "City: " + city + "\n" + "State: " + state + "\n" + "Country: " + country + "\n" + "Wind Speed:" + ws + "\n" + "Humidity: " + hu + "\n" + "Temperature" + tp + "\n" + "Aqi" + aqius;

                textView1.setText(datareturned);

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

            //return new Card(city, state, country, Integer.parseInt(aqius), Integer.parseInt(aqicn), Double.parseDouble(tp), Double.parseDouble(ws), Double.parseDouble(hu), Double.parseDouble(latitude), Double.parseDouble(longitude));
        }
/*
        @Override
        protected void onPostExecute(Void card) {
            super.onPostExecute(card);

            textView1.setText(datareturned);

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

 */

}



