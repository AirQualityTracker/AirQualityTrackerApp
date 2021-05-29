package com.androidapp.airqualitytracker;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;



//returns lang and lot for a place user enters
public class SearchFragment extends Fragment {
    private Button bt;
    private EditText editText;
    private TextView textView; //this will deleted



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.search_fragment, container, false);
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

    private class GeoHandler extends android.os.Handler {
        @Override
        public void handleMessage(Message msg){
            String address;
            switch(msg.what){
                case 1:
                    Bundle bundle= msg.getData();
                    address = bundle.getString("address");
                    break;
                default:
                    address=null;

            }
            textView.setText(address);
        }


    }










}