package com.sixthank.gogo.src.post.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sixthank.gogo.R;
import com.sixthank.gogo.src.common.OnItemClickListener;
import com.sixthank.gogo.src.detail.models.BoardDetailResponse;

import java.util.ArrayList;

public class AnswerListAdapter extends RecyclerView.Adapter<AnswerListAdapter.AnswerHolder> {

    private ArrayList<BoardDetailResponse.Content> list;
    private OnItemClickListener listener;

    public AnswerListAdapter(ArrayList<BoardDetailResponse.Content> list) {
        this.list = list;
    }

    public AnswerListAdapter() { }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public AnswerListAdapter.AnswerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_answer, null);
        AnswerHolder holder = new AnswerHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerListAdapter.AnswerHolder holder, int position) {
        holder.content.setText(list.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AnswerHolder extends RecyclerView.ViewHolder {
        TextView content;

        public AnswerHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.item_answer_tv_content);
            content.setOnClickListener(v -> {
                if(listener != null) {
                    listener.onItemClick(v, list.get(getAdapterPosition()));
                }
            });
        }

    }

}
