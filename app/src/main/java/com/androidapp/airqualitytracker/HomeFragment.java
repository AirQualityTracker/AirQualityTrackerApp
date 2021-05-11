package com.androidapp.airqualitytracker;

import android.app.AlertDialog;
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
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;


public class HomeFragment extends Fragment {
    private CardViewModel cardViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //recyclerView.setHasFixedSize(true);

        CardAdapter adapter = new CardAdapter();
        recyclerView.setAdapter(adapter);

        cardViewModel = new ViewModelProvider(requireActivity()).get(CardViewModel.class);
        cardViewModel.getAllCards().observe(getViewLifecycleOwner(), adapter::setCards);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
                //cardViewModel.delete(adapter.getCardAt(viewHolder.getAbsoluteAdapterPosition()));
                AlertDialog.Builder builder = new AlertDialog.Builder(container.getContext()).setTitle("Remove Location").setMessage("Are you sure you want to remove this location?");
                builder.setPositiveButton("OK", (dialog, arg) -> {
                    dialog.cancel();
                    cardViewModel.delete(adapter.getCardAt(viewHolder.getAbsoluteAdapterPosition()));
                    Toast.makeText(container.getContext(), "Location Deleted", Toast.LENGTH_SHORT).show();
                });
                builder.setNegativeButton("CANCEL", (dialog, arg) -> {
                    dialog.cancel();
                    adapter.notifyDataSetChanged();
                });
                builder.create().show();
            }
        }).attachToRecyclerView(recyclerView);

        return view;
    }

}
