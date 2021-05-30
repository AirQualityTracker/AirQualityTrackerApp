package com.androidapp.airqualitytracker.submenuFragments.settingsFragments.managePlaces;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidapp.airqualitytracker.databinding.LocationFragmentBinding;
import com.androidapp.airqualitytracker.submenuFragments.settingsFragments.managePlaces.placeholder.PlaceholderContent.PlaceholderItem;
//import com.androidapp.airqualitytracker.submenuFragments.settingsFragments.managePlaces.databinding.LocationFragmentBinding;

import java.util.List;

/**
 *
 * This class represents a recycler adapter that  contains the places that the user can manage from the
 * settings
 *
 */
public class MyLocationRecyclerViewAdapter extends RecyclerView.Adapter<MyLocationRecyclerViewAdapter.ViewHolder> {
     //users places
    private final List<PlaceholderItem> mValues;

    public MyLocationRecyclerViewAdapter(List<PlaceholderItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LocationFragmentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //get the items from the holder
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
    //class that holds each place data
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public PlaceholderItem mItem;

        public ViewHolder(LocationFragmentBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}