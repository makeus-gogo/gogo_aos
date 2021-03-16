package com.sixthank.gogo.src.main.home.adapter;

import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sixthank.gogo.R;
import com.sixthank.gogo.src.main.home.models.HomeResponse;
import com.sixthank.gogo.src.main.home.models.WorryResponse;

import java.util.ArrayList;
import java.util.List;

public class WorryRankAdapter extends RecyclerView.Adapter<WorryRankAdapter.WorryHolder> {
    List<HomeResponse.Data> list;

    public WorryRankAdapter(List<HomeResponse.Data> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public WorryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_worry_rank, null);
        WorryHolder holder = new WorryHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WorryHolder holder, int position) {
        HomeResponse.Data item = list.get(position);
        holder.worryContent.setText(item.getDescription());
        //holder.nickName.setText()) 닉네임 줘야함
//        holder.profile.setImageURI(Uri.parse()); 프로필 사진 넘겨줘야함
        holder.worryImage.setImageURI(Uri.parse(item.getPictureUrl()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class WorryHolder extends RecyclerView.ViewHolder {
        TextView worryContent;
        TextView nickName;
        ImageView worryImage;
        ImageView profile;
        public WorryHolder(@NonNull View itemView) {
            super(itemView);
            worryContent = itemView.findViewById(R.id.item_worry_rank_content);
            nickName = itemView.findViewById(R.id.item_worry_rank_nickname);
            worryImage = itemView.findViewById(R.id.item_worry_rank_img);
            profile = itemView.findViewById(R.id.item_worry_rank_profile);
        }
    }
}
