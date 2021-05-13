package com.androidapp.airqualitytracker;

import android.app.Activity;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class FavouritesAdp extends RecyclerView.Adapter<FavouritesAdp.ViewHolder> {
    private Activity activity;
    ArrayList<AQT_CARD_DATA> arrayListFavourites;

    public FavouritesAdp (ArrayList<AQT_CARD_DATA> arrayListFavourites){
       // this.activity = activity;
        this.arrayListFavourites =arrayListFavourites;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.aqt_card_view, parent , false);
        return new FavouritesAdp.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Initialize data
        AQT_CARD_DATA data = arrayListFavourites.get(position);

    }

    @Override
    public int getItemCount() {
        return arrayListFavourites.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView face_image;
        ImageView degrees_celsius_image;
        ImageView air_km_h_image;
        ImageView atmosphere_humidity_image;
        TextView quality_indicator;
        TextView quality_assessment;
        TextView degrees_celsius;
        TextView air_km_h;
        TextView atmosphere_humidity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            face_image = itemView.findViewById(R.id.face_image);
            degrees_celsius_image = itemView.findViewById(R.id.degrees_celsius_image);
            air_km_h_image = itemView.findViewById(R.id.air_km_h_image);
            atmosphere_humidity_image = itemView.findViewById(R.id.atmosphere_humidity_image);
            quality_indicator = itemView.findViewById(R.id.quality_indicator);
            quality_assessment = itemView.findViewById(R.id.quality_assessment);
            degrees_celsius = itemView.findViewById(R.id.degrees_celsius);
            air_km_h = itemView.findViewById(R.id.air_km_h);
            atmosphere_humidity = itemView.findViewById(R.id.atmosphere_humidity);

        }
    }
}
