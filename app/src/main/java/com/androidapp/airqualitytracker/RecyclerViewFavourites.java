/*
package com.androidapp.airqualitytracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class RecyclerViewFavourites extends AppCompatActivity {

    RecyclerView recyclerViewFavourites;
    NestedScrollView nestedScrollView;
    ArrayList<AQT_CARD_DATA> arrayListFavourites = new ArrayList<AQT_CARD_DATA>();
    LinearLayoutManager linearLayoutManagerFavourites;
    FavouritesAdp adapterFavourites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_favourites);

        recyclerViewFavourites = findViewById(R.id.rv_favourites);

        //Set the layout
        linearLayoutManagerFavourites = new LinearLayoutManager(this);
        recyclerViewFavourites.setLayoutManager(linearLayoutManagerFavourites);

        //Set the adapter
        adapterFavourites = new FavouritesAdp(arrayListFavourites);
        recyclerViewFavourites.setAdapter(adapterFavourites);

    }
}*/
