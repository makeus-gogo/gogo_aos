package com.sixthank.gogo.src.main.explore.adpater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sixthank.gogo.R;

import java.util.ArrayList;


public class ExploreWorryListAdapter extends RecyclerView.Adapter<ExploreWorryListAdapter.WorryHolder> {

    ArrayList<String> list;

    public ExploreWorryListAdapter(ArrayList<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ExploreWorryListAdapter.WorryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_worry_search, null);
        WorryHolder holder = new WorryHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExploreWorryListAdapter.WorryHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class WorryHolder extends RecyclerView.ViewHolder {

        public WorryHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
