package com.sixthank.gogo.src.comment;

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
import com.sixthank.gogo.src.comment.models.Data;

import java.util.List;

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.CommentHolder> {

    private List<Data> list;

    public void setComments(List<Data> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CommentListAdapter.CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, null);
        CommentHolder holder = new CommentHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentListAdapter.CommentHolder holder, int position) {
        Data data = list.get(position);
        Glide.with(holder.itemView.getContext())
                .load(Uri.parse(data.getMemberProfileUrl()))
                .into(holder.ivProfile)
                .onLoadFailed(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.ic_profile_70));

        holder.tvNickname.setText(data.getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CommentHolder extends RecyclerView.ViewHolder {
        ImageView ivProfile;
        TextView tvNickname;

        public CommentHolder(@NonNull View itemView) {
            super(itemView);
            ivProfile = itemView.findViewById(R.id.item_comment_iv_profile);
            tvNickname = itemView.findViewById(R.id.item_comment_tv_content);
        }
    }

    public void addItem(Data comment) {
        list.add(comment);
        notifyItemInserted(getItemCount() - 1);
    }
}
