package com.sixthank.gogo.src.main.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sixthank.gogo.R;
import com.sixthank.gogo.src.common.OnItemClickListener;
import com.sixthank.gogo.src.detail.BoardDetailActivity;
import com.sixthank.gogo.src.main.home.models.BoardTopNResponse;
import com.sixthank.gogo.src.main.home.models.HomeResponse;

import java.util.List;

public class WorryRankAdapter extends RecyclerView.Adapter<WorryRankAdapter.WorryHolder> {
    private List<BoardTopNResponse.Data> list;
    private final Context context;

    public WorryRankAdapter(Context context, List<BoardTopNResponse.Data> list) {
        this.context = context;
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
        BoardTopNResponse.Data item = list.get(position);

        holder.worryContent.setText(item.getDescription());
        holder.worryImage.setImageURI(Uri.parse(item.getBoardImageUrl()));
        holder.nickName.setText(item.getNickname());
        if(!item.getBoardImageUrl().isEmpty())
            Glide.with(context).load(Uri.parse(item.getBoardImageUrl())).into(holder.worryImage);
        if(item.getProfileImageUrl() != null) {
            Glide.with(context).load(Uri.parse(item.getProfileImageUrl())).circleCrop().into(holder.profile);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class WorryHolder extends RecyclerView.ViewHolder {
        TextView worryContent, nickName;
        ImageView worryImage, profile;
        public WorryHolder(@NonNull View itemView) {
            super(itemView);
            worryContent = itemView.findViewById(R.id.item_worry_rank_content);
            nickName = itemView.findViewById(R.id.item_worry_rank_nickname);
            worryImage = itemView.findViewById(R.id.item_worry_rank_img);
            profile = itemView.findViewById(R.id.item_worry_rank_profile);

            itemView.setOnClickListener(v->{
                int boardId = list.get(getAdapterPosition()).getBoardId();
                Intent intent = new Intent(context, BoardDetailActivity.class);
                intent.putExtra("boardId", boardId);
                context.startActivity(intent);
            });
        }
    }


}
