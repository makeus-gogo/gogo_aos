package com.sixthank.gogo.src.main.explore.adpater;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sixthank.gogo.R;
import com.sixthank.gogo.src.detail.BoardDetailActivity;
import com.sixthank.gogo.src.detail.models.BoardDetailResponse;
import com.sixthank.gogo.src.main.explore.models.ExploreResponse;

import java.util.ArrayList;
import java.util.List;


public class ExploreWorryListAdapter extends RecyclerView.Adapter<ExploreWorryListAdapter.WorryHolder> {

    private ArrayList<ExploreResponse.Data> list;
    private final Context context;

    public ExploreWorryListAdapter(Context context) {
        this.list = new ArrayList<>();
        this.context = context;
    }

    public void addItems(List<ExploreResponse.Data> items) {
        this.list.addAll(items);
    }

    @NonNull
    @Override
    public ExploreWorryListAdapter.WorryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_worry_search, null);
        WorryHolder holder = new WorryHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExploreWorryListAdapter.WorryHolder holder, int position) {
        ExploreResponse.Board board = list.get(position).getBoard();
        ExploreResponse.Creator creator = list.get(position).getCreator();
        holder.tvDescription.setText(board.getDescription());
        holder.tvNickname.setText(creator.getName());

        if(board.getPictureUrl() != null && !board.getPictureUrl().isEmpty()) {
            Glide.with(context)
                    .load(Uri.parse(board.getPictureUrl()))
                    .into(holder.ivExploreImage);
        } else {
            Glide.with(context)
                    .load(ContextCompat.getDrawable(context, R.drawable.bg_glide_load_default))
                    .into(holder.ivExploreImage);
        }
        // profile add
        if(creator.getProfileUrl() != null && !creator.getProfileUrl().isEmpty()) {
            Glide.with(context)
                    .load(Uri.parse(creator.getProfileUrl()))
                    .circleCrop()
                    .into(holder.ivProfile);
        } else {
            Glide.with(context)
                    .load(ContextCompat.getDrawable(context, R.drawable.ic_profile))
                    .circleCrop()
                    .into(holder.ivProfile);
        }
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    class WorryHolder extends RecyclerView.ViewHolder{
        ImageView ivExploreImage, ivProfile;
        TextView tvDescription, tvNickname;
        public WorryHolder(@NonNull View itemView) {
            super(itemView);
            ivExploreImage = itemView.findViewById(R.id.item_search_iv_worry);
            ivProfile = itemView.findViewById(R.id.item_search_iv_profile);
            tvDescription = itemView.findViewById(R.id.item_search_tv_content);
            tvNickname = itemView.findViewById(R.id.item_search_tv_nickname);

            itemView.setOnClickListener(v->{
                Intent intent = new Intent(context, BoardDetailActivity.class);
                intent.putExtra("boardId", list.get(getAdapterPosition()).getBoard().getId());
                intent.putExtra("nickname", list.get(getAdapterPosition()).getCreator().getName());
                intent.putExtra("profileUrl", list.get(getAdapterPosition()).getCreator().getProfileUrl());
                context.startActivity(intent);
            });
        }
    }

    public int getLastId() {
        if(getItemCount() == 0) return 0;
        return this.list.get(getItemCount() - 1).getBoard().getId();
    }

    public void setList(List<ExploreResponse.Data> items) {
        this.list = (ArrayList<ExploreResponse.Data>) items;
    }
}
