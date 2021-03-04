package com.sixthank.gogo.src.post.fragment.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.sixthank.gogo.R;

public class TextListAdapter extends RecyclerView.Adapter<TextListAdapter.TxtHolder> {


    int[] list = new int[]{
            R.drawable.bg_circle_white_op,R.drawable.bg_circle_white,
            R.drawable.bg_circle_black_op,R.drawable.bg_circle_black
    };

    int[] txtList = new int[]{
            R.color.black, R.color.white, R.color.black, R.color.white
    };

    @NonNull
    @Override
    public TxtHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_txt, null);
        TxtHolder holder = new TxtHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TxtHolder holder, int position) {
        holder.txtBg.setBackgroundResource(list[position]);
        holder.txt.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), txtList[position]));
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    public class TxtHolder extends RecyclerView.ViewHolder {
        RelativeLayout txtBg;
        TextView txt;

        public TxtHolder(@NonNull View itemView) {
            super(itemView);
            txtBg = itemView.findViewById(R.id.item_txt_bg);
            txt = itemView.findViewById(R.id.item_txt);
        }
    }
}
