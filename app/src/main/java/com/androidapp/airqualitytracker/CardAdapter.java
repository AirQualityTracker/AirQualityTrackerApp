package com.androidapp.airqualitytracker;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

public class CardAdapter extends ListAdapter<Card, CardAdapter.CardHolder> {
    private Context context;

    private TypedArray faceImages;
    private int[] severityColors;
    private String[] qualityAssessments;

    public CardAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Card> DIFF_CALLBACK = new DiffUtil.ItemCallback<Card>() {
        @Override
        public boolean areItemsTheSame(@NonNull Card oldItem, @NonNull Card newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Card oldItem, @NonNull Card newItem) {
            return oldItem.getCity().equals(newItem.getCity()) &&
                    oldItem.getState().equals(newItem.getState()) &&
                    oldItem.getCountry().equals(newItem.getCountry());
        }
    };

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
        Card currentCard = getItem(position);

        holder.card_view.setCardBackgroundColor(severityColors[currentCard.getSeverityCategory()]);
        holder.face_image.setImageResource(faceImages.getResourceId(currentCard.getSeverityCategory(), 0));

        holder.city.setText(currentCard.getCity());
        holder.state_country.setText(String.format("%1$s, %2$s", currentCard.getState(), currentCard.getCountry()));

        // TODO: Display AQI US or CN, depending on user settings
        holder.quality_indicator.setText(String.valueOf(currentCard.getAqius()));
        holder.quality_assessment.setText(qualityAssessments[currentCard.getSeverityCategory()]);

        holder.degrees.setText(String.format(Locale.ENGLISH, "%1$s %2$s", currentCard.getDegrees(), context.getResources().getString(R.string.celsius_symbol)));
        holder.windspeed.setText(String.format(Locale.ENGLISH, "%1$s %2$s", currentCard.getWindspeed(), context.getResources().getString(R.string.airspeed_ms)));
        holder.humidity.setText(String.format(Locale.ENGLISH, "%1$s %2$s", currentCard.getHumidity(), context.getResources().getString(R.string.humidity)));
    }

    public Card getCardAt(int position) {
        return getItem(position);
    }

    class CardHolder extends RecyclerView.ViewHolder {
        final CardView card_view;
        final ImageView face_image;
        final TextView city, state_country;
        final TextView quality_indicator, quality_assessment, degrees, windspeed, humidity;

        public CardHolder(@NonNull View itemView) {
            super(itemView);

            qualityAssessments = context.getResources().getStringArray(R.array.quality_assessments);
            severityColors = context.getResources().getIntArray(R.array.card_colors);
            faceImages = context.getResources().obtainTypedArray(R.array.card_face_images);

            card_view = itemView.findViewById(R.id.card_view);

            face_image = itemView.findViewById(R.id.face_image);

            city = itemView.findViewById(R.id.location_city);
            state_country = itemView.findViewById(R.id.location_state_country);

            quality_indicator = itemView.findViewById(R.id.quality_indicator);
            quality_assessment = itemView.findViewById(R.id.quality_assessment);
            degrees = itemView.findViewById(R.id.degrees);
            windspeed = itemView.findViewById(R.id.windspeed);
            humidity = itemView.findViewById(R.id.humidity);

            itemView.setOnClickListener(v -> {
                int position = getAbsoluteAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Snackbar.make(v, "CLICK ON " + getCardAt(position).getId(), Snackbar.LENGTH_LONG).show();
                }
            });
        }

    }
}
