package com.sixthank.gogo.src.post.fragment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sixthank.gogo.R;
import com.sixthank.gogo.src.main.models.WorryResponse;

import java.util.ArrayList;

public class WorryListAdapter2 extends RecyclerView.Adapter<WorryListAdapter2.WorryHolder> {
    ArrayList<WorryResponse> list;

    public WorryListAdapter2(ArrayList<WorryResponse> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public WorryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_worry_long, null);
        WorryHolder holder = new WorryHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WorryHolder holder, int position) {
        WorryResponse worry = list.get(position);
        TextView worryContent = holder.itemView.findViewById(R.id.worry_item_txt_content);
        worryContent.setText(worry.getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class WorryHolder extends RecyclerView.ViewHolder {

        public WorryHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
