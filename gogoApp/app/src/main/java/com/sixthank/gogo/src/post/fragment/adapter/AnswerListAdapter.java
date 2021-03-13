package com.sixthank.gogo.src.post.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sixthank.gogo.R;

import java.util.ArrayList;

public class AnswerListAdapter extends RecyclerView.Adapter<AnswerListAdapter.AnswerHolder> {

    Context context;
    ArrayList<String> list;

    public AnswerListAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
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

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AnswerHolder extends RecyclerView.ViewHolder {
        EditText content;
        ImageView removeChoice;
        public AnswerHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.item_answer_et_content);
            removeChoice = itemView.findViewById(R.id.item_answer_iv_remove);

            removeChoice.setOnClickListener(v->{
                removeItem(this.getAdapterPosition());
            });
        }
    }

    public void addItem() {
        String item = "";
        list.add(item);
        notifyItemInserted(getItemCount()-1);
    }

    public void removeItem(int position) {
        if(getItemCount() == 2) {
            Toast.makeText(context, "최소 2개의 선택지가 필요합니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        list.remove(position);
        notifyItemRemoved(position);
    }
}
