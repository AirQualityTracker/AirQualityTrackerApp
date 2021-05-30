package com.androidapp.airqualitytracker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



//returns lang and lot for a place user enters
public class SearchFragment extends Fragment {
    LayoutInflater layoutInflater;
    ViewGroup viewGroupContainer;

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

        View view = layoutInflater.inflate(R.layout.search_fragment, viewGroupContainer, false);

        editText = view.findViewById(R.id.et_place);
        bt = view.findViewById(R.id.bt);
        textView = view.findViewById(R.id.textview);

        bt.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                String address = editText.getText().toString();
                CityLocation cityLocation = new CityLocation();
                cityLocation.getAddress(address , getActivity().getApplicationContext(), new GeoHandler());

            }
        });

        return view;
    }

    public class GeoHandler extends android.os.Handler {
        @Override
        public void handleMessage(Message msg){
            String latitude , longitude, address="";

            switch(msg.what){
                case 1:
                    Bundle bundle= msg.getData();
                    latitude = bundle.getString("lat");
                    longitude = bundle.getString("long");
                    address = "Lat= "+ latitude + "\nLong= " + longitude;
                    TakeData process = new TakeData(latitude, longitude);
                    process.execute();
                    break;
                default:
                    address=null;


            }
            //textView.setText(address);

        }


    }










}