package com.sixthank.gogo.src.post.fragment.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.sixthank.gogo.R;

import java.util.ArrayList;

public class ColorListAdapter extends RecyclerView.Adapter<ColorListAdapter.ColorHolder> {

    Context context;

    public ColorListAdapter(Context context) {
        this.context = context;
    }

    int[] list = new int[]{
            R.drawable.bg_circle_red,R.drawable.bg_circle_orange,R.drawable.bg_circle_yellow,
            R.drawable.bg_circle_green,R.drawable.bg_circle_sky,R.drawable.bg_circle_blue,
            R.drawable.bg_circle_black,R.drawable.bg_circle_white
    };

    @NonNull
    @Override
    public ColorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_color, null);
        ColorHolder holder = new ColorHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ColorHolder holder, int position) {
        View view = holder.itemView.findViewById(R.id.view_color);
        view.setBackgroundResource(list[position]);

    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    public class ColorHolder extends RecyclerView.ViewHolder {

        public ColorHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(v->{
                Toast.makeText(context, "준비 중입니다.", Toast.LENGTH_SHORT).show();
            });
        }
    }
}
