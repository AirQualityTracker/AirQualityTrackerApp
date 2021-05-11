package com.androidapp.airqualitytracker;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardHolder> {
    private Context context;
    private List<Card> cards = new ArrayList<>();
    private final int[] faceImages = {
            R.drawable.card_ic_face_0,
            R.drawable.card_ic_face_0,
            R.drawable.card_ic_face_1,
            R.drawable.card_ic_face_2,
            R.drawable.card_ic_face_3,
            R.drawable.card_ic_face_4
    };
    private final int[] severityColors = {
            R.color.card1,
            R.color.card2,
            R.color.card3,
            R.color.card4,
            R.color.card5,
            R.color.card6
    };
    private String[] qualityAssessments;

    @NonNull
    @Override
    public CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        assert parent.getResources() != null;
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.aqt_card_view, parent, false);
        return new CardHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardHolder holder, int position) {
        Card currentCard = cards.get(position);

        holder.card_view.setCardBackgroundColor(ContextCompat.getColor(
                context,
                severityColors[currentCard.getSeverityCategory()]
        ));

        holder.face_image.setImageResource(faceImages[currentCard.getSeverityCategory()]);

        holder.city.setText(currentCard.getCity());
        holder.county_country.setText(String.format("%1$s, %2$s", currentCard.getCounty(), currentCard.getCountry()));

        // TODO: Display AQI US or CN, depending on user settings
        holder.quality_indicator.setText(String.valueOf(currentCard.getAqiUS()));
        holder.quality_assessment.setText(qualityAssessments[currentCard.getSeverityCategory()]);

        holder.degrees.setText(String.format("%1$d %2$s", currentCard.getDegrees(), context.getResources().getString(R.string.celsius_symbol)));
        holder.airspeed.setText(String.format("%1$d %2$s", currentCard.getAirspeed(), context.getResources().getString(R.string.airspeed_ms)));
        holder.humidity.setText(String.format("%1$d %2$s", currentCard.getHumidity(), context.getResources().getString(R.string.humidity)));
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
        // FIXME: Not optimized yet
        notifyDataSetChanged();
    }

    public Card getCardAt(int position) {
        return cards.get(position);
    }

    class CardHolder extends RecyclerView.ViewHolder {
        private CardView card_view;
        private ImageView face_image;
        private TextView city, county_country;
        private TextView quality_indicator, quality_assessment, degrees, airspeed, humidity;

        public CardHolder(@NonNull View itemView) {
            super(itemView);

            qualityAssessments = context.getResources().getStringArray(R.array.quality_assessments);

            card_view = itemView.findViewById(R.id.card_view);

            face_image = itemView.findViewById(R.id.face_image);

            city = itemView.findViewById(R.id.location_city);
            county_country = itemView.findViewById(R.id.location_county_country);

            quality_indicator = itemView.findViewById(R.id.quality_indicator);
            quality_assessment = itemView.findViewById(R.id.quality_assessment);
            degrees = itemView.findViewById(R.id.degrees);
            airspeed = itemView.findViewById(R.id.airspeed);
            humidity = itemView.findViewById(R.id.humidity);
        }
    }
}
