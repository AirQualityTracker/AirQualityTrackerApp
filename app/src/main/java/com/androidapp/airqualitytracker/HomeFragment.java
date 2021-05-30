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

/**
 * This class is used to show to all the cards in the home page through a recycler viewer
 * */
public class HomeFragment extends Fragment {
    private CardViewModel sharedCardViewModel;

    private View view;
    private RecyclerView recyclerView;
    private CardAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        //get the views from the xml an add a recycler adapter to the page
        view = inflater.inflate(R.layout.home_fragment, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new CardAdapter();
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //get all the cards from the card model and display them on screen
        sharedCardViewModel = new ViewModelProvider(requireActivity()).get(CardViewModel.class);
        sharedCardViewModel.getAllCards().observe(getViewLifecycleOwner(), adapter::submitList);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAbsoluteAdapterPosition();
                Card cardToDelete = adapter.getCardAt(position);

                sharedCardViewModel.delete(cardToDelete);

                Snackbar snackbar = Snackbar.make(view, R.string.snackbar_card_deleted, Snackbar.LENGTH_LONG);
                snackbar.setAction(R.string.snackbar_card_undo, v -> sharedCardViewModel.insert(cardToDelete));
                snackbar.show();
            }

        }).attachToRecyclerView(recyclerView);
    }
}
