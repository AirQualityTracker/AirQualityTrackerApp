package com.androidapp.airqualitytracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class Aqt_Card_Recycler_Adapter extends RecyclerView.Adapter<Aqt_Card_Recycler_Adapter.ViewHolder>{
  private ArrayList<AQT_CARD_DATA> aqt_card_data = getAqtCardData();

    //Class that holds the items to be displayed (Views in card_layout)
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView face_image, degrees_celsius_image, air_km_h_image, atmosphere_humidity_image;
        TextView quality_indicator, quality_assessment, degrees_celsius, air_km_h, atmosphere_humidity;

        public ViewHolder(View itemView) {
            super(itemView);
            //get the images to be displayed on card
            face_image = itemView.findViewById(R.id.face_image);
            degrees_celsius_image = itemView.findViewById(R.id.degrees_celsius_image);
            air_km_h_image = itemView.findViewById(R.id.air_km_h_image);
            atmosphere_humidity_image = itemView.findViewById(R.id.atmosphere_humidity_image);

            //get the text to be displayed on cards
            quality_indicator = itemView.findViewById(R.id.quality_indicator);
            quality_assessment = itemView.findViewById(R.id.quality_assessment);
            degrees_celsius = itemView.findViewById(R.id.degrees_celsius);
            air_km_h = itemView.findViewById(R.id.air_km_h);
            atmosphere_humidity = itemView.findViewById(R.id.atmosphere_humidity);




            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();

                    Snackbar.make(v, "Click detected on item " + position,
                            Snackbar.LENGTH_LONG).show();
                }
            });
        }
    }

    //Methods that must be implemented for a RecyclerView.Adapter
    @Override
    public Aqt_Card_Recycler_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.aqt_card_view, parent, false);
        Aqt_Card_Recycler_Adapter.ViewHolder viewHolder = new Aqt_Card_Recycler_Adapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Aqt_Card_Recycler_Adapter.ViewHolder holder, int position) {

        holder.face_image.setImageResource(aqt_card_data.get(position).getFace_image());
        holder.degrees_celsius_image.setImageResource(aqt_card_data.get(position).getDegrees_celsius_image());
        holder.air_km_h_image.setImageResource(aqt_card_data.get(position).getAir_km_h_image());
        holder.atmosphere_humidity_image.setImageResource(aqt_card_data.get(position).getAtmosphere_humidity_image());
        holder.quality_indicator.setText(aqt_card_data.get(position).getQuality_indicator());
        holder.quality_assessment.setText(aqt_card_data.get(position).getQuality_assessment());
        holder.degrees_celsius.setText(aqt_card_data.get(position).getDegrees_celsius());
        holder.air_km_h.setText(aqt_card_data.get(position).getAir_km_h());
        holder.atmosphere_humidity.setText(aqt_card_data.get(position).getAtmosphere_humidity());

    }

    @Override
    public int getItemCount() {
        return aqt_card_data.size();
    }



    public ArrayList<AQT_CARD_DATA> getAqtCardData(){
        int image = R.drawable.ic_baseline_home_24;
        String quality_indicator = "150",
                quality_assessment = "quality assessment",
                degrees_celsius = "40",
                air_km_h = "20 km/h",
                atmosphere_humidity = "50%";
        //get the data from a database and set them as objects
        ArrayList<AQT_CARD_DATA> aqt_card_data_array = new ArrayList<>();
        AQT_CARD_DATA aqt_card_data1 = new AQT_CARD_DATA();
        AQT_CARD_DATA aqt_card_data2 = new AQT_CARD_DATA();
        AQT_CARD_DATA aqt_card_data3 = new AQT_CARD_DATA();
        AQT_CARD_DATA aqt_card_data4 = new AQT_CARD_DATA();
        AQT_CARD_DATA aqt_card_data5 = new AQT_CARD_DATA();
        AQT_CARD_DATA aqt_card_data6 = new AQT_CARD_DATA();
        aqt_card_data1.setFace_image(image);
        aqt_card_data1.setDegrees_celsius_image(image);
        aqt_card_data1.setAir_km_h_image(image);
        aqt_card_data1.setAtmosphere_humidity_image(image);
        aqt_card_data1.setQuality_indicator(quality_indicator);
        aqt_card_data1.setQuality_assessment(quality_assessment);
        aqt_card_data1.setDegrees_celsius(degrees_celsius);
        aqt_card_data1.setAir_km_h(air_km_h);
        aqt_card_data1.setAtmosphere_humidity(atmosphere_humidity);


        ////


        aqt_card_data2.setFace_image(image);
        aqt_card_data2.setDegrees_celsius_image(image);
        aqt_card_data2.setAir_km_h_image(image);
        aqt_card_data2.setAtmosphere_humidity_image(image);
        aqt_card_data2.setQuality_indicator(quality_indicator);
        aqt_card_data2.setQuality_assessment(quality_assessment);
        aqt_card_data2.setDegrees_celsius(degrees_celsius);
        aqt_card_data2.setAir_km_h(air_km_h);
        aqt_card_data2.setAtmosphere_humidity(atmosphere_humidity);


        aqt_card_data3.setFace_image(image);
        aqt_card_data3.setDegrees_celsius_image(image);
        aqt_card_data3.setAir_km_h_image(image);
        aqt_card_data3.setAtmosphere_humidity_image(image);
        aqt_card_data3.setQuality_indicator(quality_indicator);
        aqt_card_data3.setQuality_assessment(quality_assessment);
        aqt_card_data3.setDegrees_celsius(degrees_celsius);
        aqt_card_data3.setAir_km_h(air_km_h);
        aqt_card_data3.setAtmosphere_humidity(atmosphere_humidity);


        aqt_card_data4.setFace_image(image);
        aqt_card_data4.setDegrees_celsius_image(image);
        aqt_card_data4.setAir_km_h_image(image);
        aqt_card_data4.setAtmosphere_humidity_image(image);
        aqt_card_data4.setQuality_indicator(quality_indicator);
        aqt_card_data4.setQuality_assessment(quality_assessment);
        aqt_card_data4.setDegrees_celsius(degrees_celsius);
        aqt_card_data4.setAir_km_h(air_km_h);
        aqt_card_data4.setAtmosphere_humidity(atmosphere_humidity);


        aqt_card_data5.setFace_image(image);
        aqt_card_data5.setDegrees_celsius_image(image);
        aqt_card_data5.setAir_km_h_image(image);
        aqt_card_data5.setAtmosphere_humidity_image(image);
        aqt_card_data5.setQuality_indicator(quality_indicator);
        aqt_card_data5.setQuality_assessment(quality_assessment);
        aqt_card_data5.setDegrees_celsius(degrees_celsius);
        aqt_card_data5.setAir_km_h(air_km_h);
        aqt_card_data5.setAtmosphere_humidity(atmosphere_humidity);


        aqt_card_data6.setFace_image(image);
        aqt_card_data6.setDegrees_celsius_image(image);
        aqt_card_data6.setAir_km_h_image(image);
        aqt_card_data6.setAtmosphere_humidity_image(image);
        aqt_card_data6.setQuality_indicator(quality_indicator);
        aqt_card_data6.setQuality_assessment(quality_assessment);
        aqt_card_data6.setDegrees_celsius(degrees_celsius);
        aqt_card_data6.setAir_km_h(air_km_h);
        aqt_card_data6.setAtmosphere_humidity(atmosphere_humidity);


        aqt_card_data_array.add(aqt_card_data1);
        aqt_card_data_array.add(aqt_card_data2);
        aqt_card_data_array.add(aqt_card_data3);
        aqt_card_data_array.add(aqt_card_data4);
        aqt_card_data_array.add(aqt_card_data5);
        aqt_card_data_array.add(aqt_card_data6);

        return aqt_card_data_array;




     }
}
