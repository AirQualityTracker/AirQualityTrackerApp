package com.androidapp.airqualitytracker;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;


public class HomeFragment extends Fragment {
    private CardViewModel cardViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        CardAdapter adapter = new CardAdapter();
        recyclerView.setAdapter(adapter);

        cardViewModel = new ViewModelProvider(requireActivity()).get(CardViewModel.class);
        //networkRequest();
        cardViewModel.getAllCards().observe(getViewLifecycleOwner(), adapter::submitList);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAbsoluteAdapterPosition();
                Card cardToDelete = adapter.getCardAt(position);

                cardViewModel.delete(cardToDelete);

                Snackbar snackbar = Snackbar.make(view, R.string.snackbar_card_deleted, Snackbar.LENGTH_LONG);
                snackbar.setAction(R.string.snackbar_card_undo, v -> cardViewModel.insert(cardToDelete));
                snackbar.show();
            }

        }).attachToRecyclerView(recyclerView);

        return view;
    }

    /*private void networkRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.airvisual.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AirVisualApi api = retrofit.create(AirVisualApi.class);

        Call<List<Card>> call = api.getCards();

        call.enqueue(new Callback<List<Card>>() {
            @Override
            public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                if (!response.isSuccessful()) {
                    Log.println(Log.ERROR, "Response failure: ", String.valueOf(response.code()));
                    return;
                }

                List<Card> cards = response.body();

                for (Card card : cards) {
                    cardViewModel.insert(card);
                }
            }

            @Override
            public void onFailure(Call<List<Card>> call, Throwable t) {
                Log.println(Log.ERROR, "Call failure: ", t.getMessage());
            }
        });
    }*/
}
