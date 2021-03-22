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
import com.sixthank.gogo.src.detail.BoardDetailActivity;
import com.sixthank.gogo.src.main.home.models.HomeResponse;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class WorryListAdapter extends RecyclerView.Adapter<WorryListAdapter.WorryHolder> {
    private Context context;
    private ArrayList<HomeResponse.Data> list;

    public WorryListAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    @NonNull
    @Override
    public WorryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_worry, null);
        WorryHolder holder = new WorryHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WorryHolder holder, int position) {
        HomeResponse.Data item = list.get(position);
        HomeResponse.Board board = item.getBoard();
        HomeResponse.Creator creator = item.getCreator();

        holder.tvContent.setText(board.getDescription());
        if(!board.getPictureUrl().isEmpty())
            Glide.with(context).load(Uri.parse(board.getPictureUrl())).into(holder.ivImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class WorryHolder extends RecyclerView.ViewHolder {
        TextView tvContent;
        ImageView ivImage;
        public WorryHolder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.item_worry_txt_content);
            ivImage = itemView.findViewById(R.id.item_worry_img);

            itemView.setOnClickListener(v->{
                int boardId = list.get(getAdapterPosition()).getBoard().getId();
                Intent intent = new Intent(context, BoardDetailActivity.class);
                intent.putExtra("boardId", boardId);
                context.startActivity(intent);
            });
        }
    }

    public void clearItems() { list = new ArrayList<>(); }
    public void addItems(ArrayList<HomeResponse.Data> items) {
        list.addAll(items);
    }
    public int getLastId() {
        if(getItemCount() == 0) return 0;
        return this.list.get(getItemCount() - 1).getBoard().getId();
    }
}
