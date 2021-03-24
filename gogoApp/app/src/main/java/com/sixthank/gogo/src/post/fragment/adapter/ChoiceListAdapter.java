package com.sixthank.gogo.src.post.fragment.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
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

public class ChoiceListAdapter extends RecyclerView.Adapter<ChoiceListAdapter.ChoiceHolder> {

    private final Context context;
    private ArrayList<String> list;

    public ChoiceListAdapter(Context context) {
        this.context = context;
        setList();
    }

    @NonNull
    @Override
    public ChoiceListAdapter.ChoiceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_choice, null);
        ChoiceHolder holder = new ChoiceHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChoiceListAdapter.ChoiceHolder holder, int position) {
        holder.etContent.setText("");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ChoiceHolder extends RecyclerView.ViewHolder {
        EditText etContent;
        ImageView ivRemoveChoice;
        public ChoiceHolder(@NonNull View itemView) {
            super(itemView);

            etContent = itemView.findViewById(R.id.item_choice_et_content);
            ivRemoveChoice = itemView.findViewById(R.id.item_choice_iv_remove);

            etContent.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }

                @Override
                public void afterTextChanged(Editable s) {
                    addContent(getAdapterPosition(), s.toString());
                }
            });

            ivRemoveChoice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeItem(getAdapterPosition());
                }
            });
        }
    }

    private void setList() {
        list = new ArrayList<>();
        for(int i = 0; i < 2; i++) {
            this.list.add("");
        }
    }

    public void addItem() {
        if(!validation(true)) return;
        list.add("");
        notifyItemInserted(getItemCount());
    }

    public void removeItem(int position) {
        if(!validation(false)) return;
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void addContent(int position, String content) {
        list.set(position, content);
    }

    public ArrayList<String> getItems() {
        return list;
    }

    public boolean validation(boolean add) {
        if(!add && getItemCount() == 2) {
            Toast.makeText(context, "최소 2개 이상 가능합니다.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(add && getItemCount() == 5 && add) {
            Toast.makeText(context, "최대 5개까지 가능합니다.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
