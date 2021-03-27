package com.sixthank.gogo.src.post.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.sixthank.gogo.R;
import com.sixthank.gogo.src.common.OnItemClickListener;
import com.sixthank.gogo.src.common.models.AnswerResultDtoList;
import com.sixthank.gogo.src.detail.models.BoardDetailResponse;

import java.util.ArrayList;
import java.util.List;

public class AnswerListAdapter extends RecyclerView.Adapter<AnswerListAdapter.AnswerHolder> {

    private List<AnswerResultDtoList> list;
    private OnItemClickListener listener;
    private Context context;
    private int selected = -1;

    public AnswerListAdapter(List<AnswerResultDtoList> list) {
        this.list = list;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setSelected(int contentId) {
        this.selected = contentId;
    }

    public void setAnswerList(List<AnswerResultDtoList> list) {
        this.list = list;
    }

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
        holder.percent.setText(list.get(position).getPercentage()+"%");
        // 변경
        if (selected == -1) {
            holder.order.setImageResource(R.drawable.ic_select_order);
            holder.contentBg.setBackgroundResource(R.drawable.bg_circle_5_click);
        } else if (selected == list.get(position).getContentId()) {
            holder.percent.setVisibility(View.VISIBLE);
            holder.order.setImageResource(R.drawable.ic_select_order_on);
            holder.contentBg.setBackgroundResource(R.drawable.bg_circle_5);
        } else {
            holder.percent.setVisibility(View.VISIBLE);
            holder.order.setImageResource(R.drawable.ic_select_order);
            holder.contentBg.setBackgroundResource(R.drawable.bg_circle_5_click);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AnswerHolder extends RecyclerView.ViewHolder {
        TextView content, percent;
        View contentBg;
        ImageView order;

        public AnswerHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.item_answer_tv_content);
            order = itemView.findViewById(R.id.item_answer_iv_order);
            contentBg = itemView.findViewById(R.id.item_answer_bg);
            percent = itemView.findViewById(R.id.item_answer_tv_percent);
            content.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(v, list.get(getAdapterPosition()));
                }
            });
        }
    }

}
